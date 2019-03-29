package userAndSubclasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.SecureRandom;

import javax.crypto.Cipher;

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
	
	public boolean verifyMessageRecord() throws Exception {
		BigInteger bigInputMAC = (BigInteger) portInStream.readObject();
		byte[] thisMACBytes = serverMAC.doFinal(messagesByteStream.toByteArray());
		
		messagesObjectStream.writeObject(bigInputMAC);
		BigInteger bigThisMAC = new BigInteger(thisMACBytes);
		encryptCipher.init(Cipher.ENCRYPT_MODE, clientEncrypt, clientIV);
		decryptCipher.init(Cipher.DECRYPT_MODE, serverEncrypt, serverIV);
		
		System.out.println("Message Record Received");
		
		return (bigInputMAC.equals(bigThisMAC));
	}
	
	public void sendMessage(String message) throws Exception {
		byte[] messageMAC = clientMAC.doFinal(message.getBytes());
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
		
		byte[] generatedMAC = serverMAC.doFinal(decryptedString.getBytes());
		if (!new BigInteger(generatedMAC).equals(bigReceivedMAC)) {
			System.err.println("Message MAC does not match Message");
			System.exit(0);
		}
		
		System.out.println("Message Received");
		
		return decryptedString;
	}
}
