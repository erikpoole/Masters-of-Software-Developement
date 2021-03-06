package userAndSubclasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Arrays;

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

		portOutStream.writeObject(nonce);
		messagesObjectStream.writeObject(nonce);

		System.out.println("Nonce Sent");
	}

	public void sendMessageRecord() throws IOException {
		byte[] recordMAC = clientMAC.doFinal(messagesByteStream.toByteArray());
		messagesObjectStream.writeObject(recordMAC);
		portOutStream.writeObject(recordMAC);
		System.out.println("Message Record Sent");
	}

	public boolean verifyMessageRecord() throws Exception {
		byte[] inputMAC = (byte[]) portInStream.readObject();
		byte[] thisMACBytes = serverMAC.doFinal(messagesByteStream.toByteArray());

		messagesObjectStream.writeObject(inputMAC);
		encryptCipher.init(Cipher.ENCRYPT_MODE, clientEncrypt, clientIV);
		decryptCipher.init(Cipher.DECRYPT_MODE, serverEncrypt, serverIV);

		System.out.println("Message Record Received");

		return (Arrays.equals(inputMAC, thisMACBytes));
	}

	@Override
	public void sendMessage(byte[] message) throws Exception {
		byte[] messageMAC = clientMAC.doFinal(message);
		byte[] encryped = encryptCipher.doFinal(message);
		portOutStream.writeObject(messageMAC);
		portOutStream.writeObject(encryped);
		System.out.println("Message Sent");
	}

	@Override
	public byte[] receiveMessage() throws Exception {
		byte[] receivedMAC = (byte[]) portInStream.readObject();
		byte[] receivedMessage = (byte[]) portInStream.readObject();

		byte[] decyrpted = decryptCipher.doFinal(receivedMessage);

		byte[] generatedMAC = serverMAC.doFinal(decyrpted);
		if (!Arrays.equals(generatedMAC, receivedMAC)) {
			System.err.println("Message MAC does not match Message");
			System.exit(0);
		}

		System.out.println("Message Received");

		return decyrpted;
	}
}
