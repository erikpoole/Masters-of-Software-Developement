package userAndSubclasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;

import javax.crypto.Cipher;

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
	
	public boolean verifyMessageRecord() throws Exception {
		BigInteger bigInputMAC = (BigInteger) portInStream.readObject();
		byte[] thisMACBytes = clientMAC.doFinal(messagesByteStream.toByteArray());
		
		messagesObjectStream.writeObject(bigInputMAC);
		BigInteger bigThisMAC = new BigInteger(thisMACBytes);
		encryptCipher.init(Cipher.ENCRYPT_MODE, serverEncrypt, serverIV);
		decryptCipher.init(Cipher.DECRYPT_MODE, clientEncrypt, clientIV);
		
		System.out.println("Message Record Received");
		
		return (bigInputMAC.equals(bigThisMAC));
	}
	
	public void sendMessage(String message) throws Exception {
		byte[] messageMAC = serverMAC.doFinal(message.getBytes());
		byte[] encryped = encryptCipher.doFinal(message.getBytes());
		portOutStream.writeObject(new BigInteger(messageMAC));
		portOutStream.writeObject(new BigInteger(encryped));
		System.out.println("Message Sent");
	}
	
	public String receiveMessage() throws Exception {
		BigInteger bigReceivedMAC = (BigInteger) portInStream.readObject();
		BigInteger bigReceivedMessage = (BigInteger) portInStream.readObject();
		
		byte[] decyrpted = decryptCipher.doFinal(bigReceivedMessage.toByteArray());
		String decryptedString = new String(decyrpted);
		
		byte[] generatedMAC = clientMAC.doFinal(decryptedString.getBytes());
		if (!new BigInteger(generatedMAC).equals(bigReceivedMAC)) {
			System.err.println("Message MAC does not match Message");
			System.exit(0);
		}
		
		System.out.println("Message Received");
		
		return decryptedString;
	}
}
