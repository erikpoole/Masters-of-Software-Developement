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
	
	public void sendMessageRecord() throws IOException {
		byte[] recordMAC = clientMAC.doFinal(messagesByteStream.toByteArray());
		BigInteger bigRecordMAC = new BigInteger(recordMAC);
		messagesObjectStream.writeObject(bigRecordMAC);
		portOutStream.writeObject(bigRecordMAC);
		System.out.println("Message Record Sent");
	}
	
	public boolean verifyMessageRecord() throws ClassNotFoundException, IOException {
		BigInteger bigInputMAC = (BigInteger) portInStream.readObject();
		byte[] thisMACBytes = serverMAC.doFinal(messagesByteStream.toByteArray());
		
		messagesObjectStream.writeObject(bigInputMAC);
		BigInteger bigThisMAC = new BigInteger(thisMACBytes);
		
		System.out.println("Message Record Received");
		
		return (bigInputMAC.equals(bigThisMAC));
	}
}
