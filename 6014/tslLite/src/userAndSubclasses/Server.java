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
	
	public void sendMessageRecord() throws IOException {
		byte[] recordMAC = serverMAC.doFinal(messagesByteStream.toByteArray());
		BigInteger bigRecordMAC = new BigInteger(recordMAC);
		messagesObjectStream.writeObject(bigRecordMAC);
		portOutStream.writeObject(bigRecordMAC);
		System.out.println("Message Record Sent");
	}
	
	public boolean verifyMessageRecord() throws ClassNotFoundException, IOException {
		BigInteger bigInputMAC = (BigInteger) portInStream.readObject();
		byte[] thisMACBytes = clientMAC.doFinal(messagesByteStream.toByteArray());
		
		messagesObjectStream.writeObject(bigInputMAC);
		BigInteger bigThisMAC = new BigInteger(thisMACBytes);
		
		System.out.println("Message Record Received");
		
		return (bigInputMAC.equals(bigThisMAC));
	}
}
