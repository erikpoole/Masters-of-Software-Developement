package tslLite;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;

public class Server extends User {
	
	ServerSocket serverSocket;
	

	public Server(DiffieHelmanHandler diffieHelmanHandler, String rsaKeyFileName, String certificateFileName) throws Exception {
		super(diffieHelmanHandler, rsaKeyFileName, certificateFileName);
		serverSocket = new ServerSocket(8080);
	}
	
	public void listen() throws IOException {
		System.out.println("Listening...");
		socket = serverSocket.accept();
		objectOutStream = new ObjectOutputStream(socket.getOutputStream());
		objectInStream = new ObjectInputStream(socket.getInputStream());
		System.out.println("Connection Established");
	}
	
	public void receiveNonce() throws ClassNotFoundException, IOException {
		BigInteger bigNonce = (BigInteger) objectInStream.readObject();
		nonce = bigNonce.toByteArray();
		
		System.out.println("Nonce Received");
	}


}
