package userAndSubclasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.util.Base64;

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
	
	@Override
	public void sendMessage(byte[] message) throws Exception {
		byte[] messageMAC = serverMAC.doFinal(message);
//		byte[] base64Message = Base64.getEncoder().encode(message);
//		byte[] encryped = encryptCipher.doFinal(base64Message);
		byte[] encryped = encryptCipher.doFinal(message);
		portOutStream.writeObject(new BigInteger(messageMAC));
		portOutStream.writeObject(new BigInteger(encryped));
		System.out.println("Message Sent");
	}
	
	public byte[] receiveMessage() throws Exception {
		BigInteger bigReceivedMAC = (BigInteger) portInStream.readObject();
		BigInteger bigReceivedMessage = (BigInteger) portInStream.readObject();
		
//		byte[] base64Message = Base64.getDecoder().decode(bigReceivedMessage.toByteArray());
//		byte[] decyrpted = decryptCipher.doFinal(base64Message);
		byte[] decyrpted = decryptCipher.doFinal(bigReceivedMessage.toByteArray());
		
		byte[] generatedMAC = clientMAC.doFinal(decyrpted);
		if (!new BigInteger(generatedMAC).equals(bigReceivedMAC)) {
			System.err.println("Message MAC does not match Message");
			System.exit(0);
		}
		
		System.out.println("Message Received");
		
		return decyrpted;
	}
}
