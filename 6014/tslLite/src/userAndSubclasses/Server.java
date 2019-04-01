package userAndSubclasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Arrays;

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
		byte[] receivedNonce = (byte[]) portInStream.readObject();
		nonce = receivedNonce;
		messagesObjectStream.writeObject(receivedNonce);

		System.out.println("Nonce Received");
	}

	public void sendMessageRecord() throws IOException {
		byte[] recordMAC = serverMAC.doFinal(messagesByteStream.toByteArray());
		messagesObjectStream.writeObject(recordMAC);
		portOutStream.writeObject(recordMAC);
		System.out.println("Message Record Sent");
	}

	public boolean verifyMessageRecord() throws Exception {
		byte[] inputMAC = (byte[]) portInStream.readObject();
		byte[] thisMACBytes = clientMAC.doFinal(messagesByteStream.toByteArray());

		messagesObjectStream.writeObject(inputMAC);
		encryptCipher.init(Cipher.ENCRYPT_MODE, serverEncrypt, serverIV);
		decryptCipher.init(Cipher.DECRYPT_MODE, clientEncrypt, clientIV);

		System.out.println("Message Record Received");

		return (Arrays.equals(inputMAC, thisMACBytes));
	}

	@Override
	public void sendMessage(byte[] message) throws Exception {
		byte[] messageMAC = serverMAC.doFinal(message);
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

		byte[] generatedMAC = clientMAC.doFinal(decyrpted);
		if (!Arrays.equals(generatedMAC, receivedMAC)) {
			System.err.println("Message MAC does not match Message");
			System.exit(0);
		}

		System.out.println("Message Received");

		return decyrpted;
	}
}
