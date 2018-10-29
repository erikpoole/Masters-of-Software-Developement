package hwBasicHTTPServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

public class ClientSocket {

	public Socket socket;
	public Scanner input;

	public HashMap<String, String> httpMap;
	public String clientKey;
	public boolean isWebSocketRequest;
	public String roomName;

	public File file;

	public ClientSocket(Socket inputSocket) {
		socket = inputSocket;
	}

	// reads in argument sent by client
	// if file not found will return 404
	// if attempts to find file out of current directory will return 404
	// if HTTP argument other than "GET" will close socket
	// if IOexception will close socket
	public void httpRequest() throws FileNotFoundException, IOException, BadRequestException {
		input = new Scanner(socket.getInputStream());

		if (!input.next().equals("GET")) {
			throw new BadRequestException();
		}
		String filename = "Resources" + input.next();
		if (filename.equals("Resources/")) {
			filename = "Resources/Index.html";
		}
		if (!input.next().equals("HTTP/1.1")) {
			throw new BadRequestException();
		}
		input.nextLine();
		setHTTPMap();

		if (httpMap.containsKey("Sec-WebSocket-Key:")) {
			clientKey = httpMap.get("Sec-WebSocket-Key:");
			isWebSocketRequest = true;
			System.out.println("Websocket Request");

		} else {
			isWebSocketRequest = false;
			file = new File(filename);
			System.out.println("Webpage Request");

			System.out.println(file.getCanonicalPath());
			if (!file.getAbsolutePath().equals(file.getCanonicalPath())) {
				throw new FileNotFoundException();
			}
		}

	}

	public void setHTTPMap() {
		httpMap = new HashMap<String, String>();
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
	public void listenWebSocket() {
		try {
			System.out.println("Web Socket - Listening");
			InputStream inputStream = socket.getInputStream();
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

				} else if (message.length() != 0){
					bodyString = "{ \"username\":\"" + username + "\" , \"message\":\"" + message + "\" }";
					Server.broadcastMessage(bodyString, roomName);
				}

			}

		} catch (Exception e) {
			System.out.println("Page Refreshed");
			Server.removeClient(this);
		}
	}

//*************************************************************************************	
//*************************************************************************************

	private String interpretHTTP(InputStream inputStream) throws IOException {
		byte[] headerBytes = new byte[6];

		for (int i = 0; i < 6; i++) {
			headerBytes[i] = (byte) (inputStream.read());
		}

		byte secondByte = headerBytes[1];
		byte mask = 0x7F;
		byte payloadLength = (byte) (secondByte & mask);

		byte[] bodyBytes = new byte[payloadLength];
		for (int i = 0; i < payloadLength; i++) {
			byte currentByte = (byte) inputStream.read();
			currentByte = (byte) (currentByte ^ headerBytes[2 + (i % 4)]);
			bodyBytes[i] = currentByte;
		}

		String bodyString = new String(bodyBytes);
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
		System.out.println(message);
	}

//*************************************************************************************	
//*************************************************************************************

	// reads bytes from file and sends them to client
	// if IOexception will close socket
	public void httpResponse() throws IOException {
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

	// catches 400 - fileNotFound Exceptions
	public void respond400() throws IOException {
		PrintWriter outputHeader = new PrintWriter(socket.getOutputStream(), true);
		outputHeader.println("HTTP/1.1 400 BAD REQUEST");
		outputHeader.println("Content-Length: 0");
		outputHeader.println();
		outputHeader.close();
		System.out.println("Client Received 400");
		socket.close();
	}

}
