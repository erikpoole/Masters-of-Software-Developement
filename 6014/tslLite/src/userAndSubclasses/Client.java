package userAndSubclasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.SecureRandom;

public class Client extends User {
	
	
	public Client(String rsaKeyFileName, String certificateFileName) throws Exception {
		super(rsaKeyFileName, certificateFileName);
		socket = new Socket();
	}
	
	public void connect() throws IOException {
		System.out.println("Connecting...");
		socket.connect(new InetSocketAddress("localhost", 8080));
		portOutStream = new ObjectOutputStream(socket.getOutputStream());
		portInStream = new ObjectInputStream(socket.getInputStream());
		System.out.println("Connection Established");
	}
	
	public void sendNonce() throws IOException {
		
		SecureRandom secureRandom = new SecureRandom();
		nonce = new byte[32];
		secureRandom.nextBytes(nonce);
		
		portOutStream.writeObject(new BigInteger(nonce));
		messagesObjectStream.writeObject(new BigInteger(nonce));
		
		System.out.println("Nonce Sent");
	}

}
