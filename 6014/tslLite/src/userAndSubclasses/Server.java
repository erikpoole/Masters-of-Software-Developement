package userAndSubclasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;

public class Server extends User {
	
	private ServerSocket serverSocket;

	
	public Server(String rsaKeyFileName, String certificateFileName) throws Exception {
		super(rsaKeyFileName, certificateFileName);
		serverSocket = new ServerSocket(8080);
	}
	
	public void listen() throws IOException {
		System.out.println("Listening...");
		socket = serverSocket.accept();
		portOutStream = new ObjectOutputStream(socket.getOutputStream());
		portInStream = new ObjectInputStream(socket.getInputStream());
		System.out.println("Connection Established");
	}
	
	public void receiveNonce() throws ClassNotFoundException, IOException {
		BigInteger bigNonce = (BigInteger) portInStream.readObject();
		nonce = bigNonce.toByteArray();
		messagesObjectStream.writeObject(bigNonce);
		
		System.out.println("Nonce Received");
	}

	public void sendHandshakeRecord() {
		serverMAC.doFinal(messagesByteStream.toByteArray());
	}
	
	/*
	 	 * Server: MAC(all handshake messages so far, Server's MAC key)
		 * Client: MAC(all handshake messages so far including the previous step, Client's MAC key).
	 */
	
	
}
