package hwBasicHTTPServer;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
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

	public void httpRequest() throws IOException, NoSuchAlgorithmException {
		File file = getRequestedFile();
		handleRequest(file);
	}

	// reads header from client HTTP request and returns requested file
	// if the HTTP request is invalid will respond400()
	// if file is outside current directory will respond404();
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
		System.out.println(file.getCanonicalPath());
		if (!file.getAbsolutePath().equals(file.getCanonicalPath())) {
			respond404();
		}

		if (!input.next().equals("HTTP/1.1")) {
			respond400();
		}

		input.nextLine();
		return file;
	}

	public void handleRequest(File requestedFile) throws IOException, NoSuchAlgorithmException {
		HashMap<String, String> httpMap = new HashMap<String, String>();
		while (true) {

			String line = input.nextLine();
			if (line.isEmpty()) {
				break;
			}

			String splitline[] = line.split("\\s+");
			String tag = splitline[0];
			String value = splitline[1];
			httpMap.put(tag, value);
		}

		if (httpMap.containsKey("Sec-WebSocket-Key:")) {
			clientKey = httpMap.get("Sec-WebSocket-Key:");
			System.out.println("Websocket Request");
			returnHandshake();
			listenWebSocket();
		}
		System.out.println("Webpage Request");
		httpResponse(requestedFile);

	}

//*************************************************************************************	
//*************************************************************************************

	public void returnHandshake() throws IOException, NoSuchAlgorithmException {
		PrintWriter outputHeader = new PrintWriter(socket.getOutputStream(), true);
		outputHeader.println("HTTP/1.1 101 Switching Protocols");
		outputHeader.println("Upgrade: websocket");
		outputHeader.println("Connection: Upgrade");
		outputHeader.println("Sec-WebSocket-Accept: " + Server.calculateHash(clientKey));
		outputHeader.println();
	}

	// if inputStream.read() is closed exception will be thrown - catch to handle
	// browser refresh problems
	public void listenWebSocket() throws IOException {
		try {
			System.out.println("Web Socket - Listening");
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());

			while (true) {
				String bodyString = interpretHTTP(inputStream);

				String[] splitBody = bodyString.split("\\s+");
				String username = splitBody[0];
				String message = new String();
				for (int i = 1; i < splitBody.length; i++) {
					message += " ";
					message += splitBody[i];
				}

				if (username.equals("serverJoin")) {
					roomName = message;
					Server.addClient(this);

				} else if (username.equals("serverExit")) {
					Server.removeClient(this);

				} else if (!message.isEmpty()) {
					bodyString = "{ \"username\":\"" + username + "\" , \"message\":\"" + message + "\" }";
					Server.broadcastMessage(bodyString, roomName);
				}

			}

		} catch (Exception e) {
			System.out.println("Bad Client Request: ");
			e.printStackTrace();
			Server.removeClient(this);
		}
	}

//*************************************************************************************	
//*************************************************************************************

	private String interpretHTTP(DataInputStream inputStream) throws IOException {
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
			System.out.println(bodyString);

		} catch (EOFException e) {
			Server.removeClient(this);
		}

		return bodyString;
	}

	public void sendMessage(String message) throws IOException {
		OutputStream outputBody = socket.getOutputStream();
		byte[] messageBytes = message.getBytes();
		byte[] outputBytes = new byte[2 + messageBytes.length];

		outputBytes[0] = (byte) 0x81;
		outputBytes[1] = (byte) messageBytes.length;
		for (int i = 0; i < messageBytes.length; i++) {
			outputBytes[i + 2] = messageBytes[i];
		}
		outputBody.write(outputBytes);
		outputBody.flush();
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
