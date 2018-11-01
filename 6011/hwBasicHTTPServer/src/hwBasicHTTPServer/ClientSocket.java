package hwBasicHTTPServer;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class ClientSocket {

	public Socket socket;
	public Scanner input;

	public String clientKey;
	public boolean isWebSocketRequest;
	public String roomName;

	public ClientSocket(Socket inputSocket) {
		socket = inputSocket;
	}

//*************************************************************************************	
//*************************************************************************************

	// Called from Main when ServerSocket accepts this new Socket connection
	public void httpRequest() throws IOException {
		File file = getRequestedFile();
		handleRequest(file);
	}

	// Reads header from client HTTP request and returns requested file
	// If the HTTP request is invalid will respond400()
	// If file is outside current directory will respond404();
	private File getRequestedFile() throws IOException {
		input = new Scanner(socket.getInputStream());
		File file = null;

		if (!input.next().equals("GET")) {
			respond400();
		}
		String filename = "Resources" + input.next();
		if (filename.equals("Resources/")) {
			filename = "Resources/index.html";
		}
		file = new File(filename);
		if (!file.getAbsolutePath().equals(file.getCanonicalPath())) {
			respond404();
		}

		if (!input.next().equals("HTTP/1.1")) {
			respond400();
		}

		input.nextLine();
		return file;
	}

	// Determines if client HTTP request is for WebSocket upgrade or single webpage
	// Handles request appropriately
	public void handleRequest(File requestedFile) throws IOException {
		HashMap<String, String> requestMap = new HashMap<String, String>();
		while (true) {
			String line = input.nextLine();
			if (line.isEmpty()) {
				break;
			}

			String splitline[] = line.split("\\s+");
			String tag = splitline[0];
			String value = splitline[1];
			requestMap.put(tag, value);
		}

		if (requestMap.containsKey("Sec-WebSocket-Key:")) {
			clientKey = requestMap.get("Sec-WebSocket-Key:");
			System.out.println("Websocket Request");
			returnHandshake();
			listenWebSocket();
		} else {
			System.out.println("Webpage Request");
			System.out.println(requestedFile.getCanonicalPath());
			httpResponse(requestedFile);
		}

	}

//*************************************************************************************	
//*************************************************************************************

	// returns HTTP handshake to client if WebSocket upgrade requested
	public void returnHandshake() throws IOException {
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		writer.println("HTTP/1.1 101 Switching Protocols");
		writer.println("Upgrade: websocket");
		writer.println("Connection: Upgrade");
		writer.println("Sec-WebSocket-Accept: " + Server.calculateHash(clientKey));
		writer.println();
		writer.flush();
	}

	// if inputStream.read() is closed exception will be thrown - catch to handle
	// browser refresh problems
	public void listenWebSocket() throws IOException {
		System.out.println("Web Socket - Listening");
		System.out.println();
		DataInputStream inputStream = new DataInputStream(socket.getInputStream());

		while (true) {
			String wholeMessage = readMessage(inputStream);
			String[] parsedMessage = splitMessage(wholeMessage);
			handleMessage(parsedMessage);

		}
	}

	private String readMessage(DataInputStream inputStream) throws IOException {
		byte[] headerBytes = new byte[6];
		byte[] bodyBytes = null;
		String bodyString = null;

		try {
			inputStream.readFully(headerBytes, 0, 6);

			byte secondByte = headerBytes[1];
			byte mask = 0x7F;
			byte payloadLength = (byte) (secondByte & mask);

			bodyBytes = new byte[payloadLength];
			inputStream.readFully(bodyBytes, 0, payloadLength);
			for (int i = 0; i < payloadLength; i++) {
				bodyBytes[i] = (byte) (bodyBytes[i] ^ headerBytes[2 + (i % 4)]);
			}

			bodyString = new String(bodyBytes);
			System.out.println("Decoded Message: " + bodyString);

		} catch (EOFException e) {
			System.out.println("!!! EOF Found !!!");
			Server.removeClient(this);
		}

		return bodyString;
	}

	private String[] splitMessage(String inputMessage) {
		String[] splitMessage = inputMessage.split("\\s+");
		String username = splitMessage[0];
		String message = new String();
		for (int i = 1; i < splitMessage.length; i++) {
			message += " ";
			message += splitMessage[i];
		}
		String[] parsedMessage = new String[2];
		parsedMessage[0] = username;
		parsedMessage[1] = message;
		return parsedMessage;
	}

	private void handleMessage(String[] parsedMessage) throws IOException {
		String username = parsedMessage[0];
		String message = parsedMessage[1];

		if (username.equals("serverJoin")) {
			roomName = message;
			Server.addClient(this);

//		} else if (username.equals("serverExit")) {
//			Server.removeClient(this);

		} else if (!message.isEmpty()) {
			String jsonString = "{ \"username\":\"" + username + "\" , \"message\":\"" + message + "\" }";
			Server.broadcastMessage(jsonString, roomName);
		}
	}

//*************************************************************************************	
//*************************************************************************************	

	public void sendMessage(String input) throws IOException {
		OutputStream outputStream = socket.getOutputStream();
		byte[] inputBytes = input.getBytes();
		byte[] outputBytes = new byte[2 + inputBytes.length];

		outputBytes[0] = (byte) 0x81;
		outputBytes[1] = (byte) inputBytes.length;
		for (int i = 0; i < inputBytes.length; i++) {
			outputBytes[i + 2] = inputBytes[i];
		}
		outputStream.write(outputBytes);
		outputStream.flush();
	}

//*************************************************************************************	
//*************************************************************************************

	// reads bytes from file and sends them to client
	// if IOexception will close socket
	public void httpResponse(File file) throws IOException {
		byte[] fileBytes = {};
		PrintWriter outputHeader = new PrintWriter(socket.getOutputStream(), true);

		FileInputStream fileStream = new FileInputStream(file.getCanonicalPath());
		fileBytes = fileStream.readAllBytes();
		fileStream.close();

		outputHeader.println("HTTP/1.1 200 OK");
		outputHeader.println("Content-Length: " + fileBytes.length);
		outputHeader.println();

		OutputStream outputBody = socket.getOutputStream();
		outputBody.write(fileBytes);
		outputBody.flush();

		socket.close();
	}

//*************************************************************************************	
//*************************************************************************************

	// catches 404 - fileNotFound Exceptions
	public void respond404() throws IOException {
		PrintWriter outputHeader = new PrintWriter(socket.getOutputStream(), true);
		outputHeader.println("HTTP/1.1 404 FILE NOT FOUND");
		outputHeader.println("Content-Length: 0");
		outputHeader.println();
		outputHeader.close();
		System.out.println("Client Received 404");
		socket.close();
	}

	public void respond400() throws IOException {
		PrintWriter outputHeader = new PrintWriter(socket.getOutputStream(), true);
		outputHeader.println("HTTP/1.1 400 BAD REQUEST");
		outputHeader.println("Content-Length: 0");
		outputHeader.println();
		outputHeader.close();
		System.out.println("Client Received 400 BAD REQUEST");
		socket.close();
	}

}
