package hwBasicHTTPServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	private ServerSocket serverSocket;
	public Socket socket;

	// Constructor - takes socket number as argument
	//exception not caught - will crash if something goes wrong
	public Server(int socketNum) throws IOException {
		serverSocket = new ServerSocket(socketNum);
	}

	// Hangs until connection is made and returns connected socket
	//exception not caught - will crash if something goes wrong
	public Socket listen() throws IOException {
		System.out.println("Server is Listening");
		socket = serverSocket.accept();
		return socket;
	}

	// reads in argument sent by client
	// if file not found will return 404
	// if attempts to find file out of current directory will return 404
	// if HTTP argument other than "GET" will close socket
	// if IOexception will close socket
	public File httpRequest() throws FileNotFoundException, IOException, IllegalArgumentException {
		Scanner input = new Scanner(socket.getInputStream());

		if (!input.next().equals("GET")) {
			throw new IllegalArgumentException();
		}

		String filename = "Resources" + input.next();
		if (filename.equals("Resources/")) {
			filename = "Resources/Index.html";
		}
		File file = new File(filename);

		System.out.println(file.getCanonicalPath());
		if (!file.getAbsolutePath().equals(file.getCanonicalPath())) {
			throw new FileNotFoundException();
		}

		return file;
	}

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
		socket.close();
	}

	//catches 404 - fileNotFound Exceptions
	public void respond404() throws IOException {
		PrintWriter outputHeader = new PrintWriter(socket.getOutputStream(), true);
		outputHeader.println("HTTP/1.1 404 FILE NOT FOUND");
		outputHeader.println("Content-Length: 0");
		outputHeader.println();
		outputHeader.close();
		System.out.println("Client Received 404");
		socket.close();
	}
}
