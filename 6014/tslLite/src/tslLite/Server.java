package tslLite;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends User {
	
	ServerSocket serverSocket;
	Socket socket;

	public Server(DiffieHelmanHandler diffieHelmanHandler, String rsaKeyFileName, String certificateFileName) throws Exception {
		super(diffieHelmanHandler, rsaKeyFileName, certificateFileName);
		serverSocket = new ServerSocket(8080);
	}
	
	public void listen() throws IOException {
		System.out.println("Listening...");
		socket = serverSocket.accept();
		System.out.println("Connection Established");
	}
	
	public void receiveNonce(byte[] inputNonce) {
		nonce = inputNonce;
	}


}
