package userAndSubclasses;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import tslLite.DiffieHelmanHandler;
import tslLite.KeyMaker;

public abstract class User {
	protected PrivateKey rsaKey;
	protected Certificate certificate;
	protected Certificate caCertificate;
	protected Signature signature;

	private DiffieHelmanHandler dhHandler;
	protected BigInteger dhPrivateKey;
	protected BigInteger dhPublicKey;
	protected BigInteger dhSecret;
	
	protected byte[] nonce;
	public ObjectOutputStream messagesObjectStream;
	public ByteArrayOutputStream messagesByteStream;
	
	protected Socket socket;
	protected ObjectOutputStream portOutStream;
	protected ObjectInputStream portInStream;
	
	protected SecretKeySpec serverEncrypt;
	protected SecretKeySpec clientEncrypt;
	protected Mac serverMAC;
	protected Mac clientMAC;
	protected IvParameterSpec serverIV;
	protected IvParameterSpec clientIV;
	
	protected Cipher encryptCipher;
	protected Cipher decryptCipher;

	public User(String rsaKeyFileName, String certificateFileName) throws Exception {
		rsaKey = KeyMaker.makeKey(rsaKeyFileName);
		certificate = KeyMaker.makeCertificate(certificateFileName);
		caCertificate = KeyMaker.makeCertificate("CAcertificate.pem");
		signature = Signature.getInstance("SHA256withRSA");
		
		messagesByteStream = new ByteArrayOutputStream();
		messagesObjectStream = new ObjectOutputStream(messagesByteStream);
		
		dhHandler = new DiffieHelmanHandler();
		dhPrivateKey = new BigInteger(32, new Random());
		dhPublicKey = dhHandler.generateDHKey(dhPrivateKey);
		
		encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	}	
	
	private BigInteger makeSignedDiffieHelman() throws InvalidKeyException, SignatureException {
		signature.initSign(rsaKey);
		signature.update(dhPublicKey.toByteArray());
		return new BigInteger(signature.sign());
	}
	
	public void sendDHCredentials() throws IOException, InvalidKeyException, SignatureException {
		BigInteger signedDH = makeSignedDiffieHelman();
		
		portOutStream.writeObject(certificate);
		portOutStream.writeObject(dhPublicKey);
		portOutStream.writeObject(signedDH);
		
		messagesObjectStream.writeObject(certificate);
		messagesObjectStream.writeObject(dhPublicKey);
		messagesObjectStream.writeObject(signedDH);
		
		System.out.println("Diffie Helman Credentials Sent");
	}
	
	public boolean verifyDHCredentials() throws Exception {
		Certificate receivedCert = (Certificate) portInStream.readObject();
		BigInteger receievedDHKey = (BigInteger) portInStream.readObject();
		BigInteger receivedDHSigned = (BigInteger) portInStream.readObject();
		
		messagesObjectStream.writeObject(receivedCert);
		messagesObjectStream.writeObject(receievedDHKey);
		messagesObjectStream.writeObject(receivedDHSigned);
		
		System.out.println("Diffie Helman Credentials Received");
		
		receivedCert.verify(caCertificate.getPublicKey());
		
		signature.initVerify(receivedCert);
		signature.update(receievedDHKey.toByteArray());
		if (signature.verify(receivedDHSigned.toByteArray())) {
			dhSecret = dhHandler.generateDHSecret(dhPrivateKey,receievedDHKey);
			return true;
		}
		return false;
	}
	
	private byte[] hkdfExpand(byte key[], String tag) throws NoSuchAlgorithmException, InvalidKeyException {
		String appendedTag = tag + 1;
		byte tagBytes[] = appendedTag.getBytes();
		
		Mac mac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
		mac.init(secretKeySpec);
		return Arrays.copyOfRange(mac.doFinal(tagBytes), 0 , 16);
	}
	
	public void generateSecretKeys() throws NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
		Mac mac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKeySpec = new SecretKeySpec(nonce, "HmacSHA2356");
		mac.init(secretKeySpec);
		byte[] prk = mac.doFinal(dhSecret.toByteArray());
		
		byte[] serverEncryptBytes = hkdfExpand(prk, "server encrypt");
		serverEncrypt = new SecretKeySpec(serverEncryptBytes, "AES");
		byte[] clientEncryptBytes = hkdfExpand(serverEncryptBytes, "client encrypt");
		clientEncrypt = new SecretKeySpec(clientEncryptBytes, "AES");
		
		byte[] serverMACBytes = hkdfExpand(clientEncryptBytes, "server MAC");
		serverMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec serverMACSecretKeySpec = new SecretKeySpec(serverMACBytes, "HmacSHA256");
		serverMAC.init(serverMACSecretKeySpec);
		
		byte[] clientMACBytes = hkdfExpand(serverMACBytes, "client MAC");
		clientMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec clientMACSecretKeySpec = new SecretKeySpec(clientMACBytes, "HmacSHA256");
		clientMAC.init(clientMACSecretKeySpec);
		
		byte[] serverIVBytes = hkdfExpand(clientMACBytes, "server IV");
		serverIV = new IvParameterSpec(serverIVBytes);
		byte[] clientIVBytes = hkdfExpand(serverIVBytes, "client IV");
		clientIV = new IvParameterSpec(clientIVBytes);
		
		System.out.println("Secret Keys Generated");
	}
	
	public abstract void sendMessage(byte[] message) throws Exception;
	public abstract byte[] receiveMessage() throws Exception;
	
	public void sendFile(String filename) throws Exception {
		File file = new File(filename);
		FileInputStream inputStream = new FileInputStream(file);
		int fileLength = (int) file.length();
//		BigInteger bigFileLength = new BigInteger(String.valueOf(fileLength));
		
		System.out.println("Sending File");
		portOutStream.writeObject(fileLength);
		
		int messageSize = 12000;
		byte buffer[] = new byte[messageSize];
		int fullMessagesToSend = fileLength / messageSize;
		int lastMessageSize = fileLength % messageSize;
		assert((fullMessagesToSend * messageSize) + lastMessageSize) == fileLength;
		
		for (int i = 0; i < fullMessagesToSend; i++) {
			inputStream.read(buffer);
			sendMessage(buffer);
		}
		buffer = new byte[lastMessageSize];
		inputStream.read(buffer);
		sendMessage(buffer);
		
		System.out.println("File Sent");
		inputStream.close();
	}
	
	public void receiveFile() throws Exception {
		FileOutputStream outStream = new FileOutputStream(new File("received_output"));
		int incomingLength = (int) portInStream.readObject();
		
		int messageSize = 12000;
		int messagesToRead;
		if (incomingLength % messageSize != 0) {
			messagesToRead = (incomingLength / messageSize) + 1;
		} else {
			messagesToRead = incomingLength / messageSize;
		}
		System.out.println("Messages To Read: " + messagesToRead);
		
		for (int i = 0; i < messagesToRead; i++) {
			outStream.write(receiveMessage());
		}
		System.out.println("File Received");
		sendMessage("File Received".getBytes());
		outStream.close();
		
	}
	
}
