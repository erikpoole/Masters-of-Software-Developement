package tslLite;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class User {
	public PrivateKey rsaKey;
	public Certificate certificate;
	public Certificate caCertificate;
	public Signature signature;

	private DiffieHelmanHandler dhHandler;
	public BigInteger dhPrivateKey;
	public BigInteger dhPublicKey;
	public BigInteger dhSecret;
	
	public byte[] nonce;
	
	public Socket socket;
	public ObjectOutputStream objectOutStream;
	public ObjectInputStream objectInStream;
	
	public SecretKeySpec serverEncrypt;
	public SecretKeySpec clientEncrypt;
	public Mac serverMAC;
	public Mac clientMAC;
	public IvParameterSpec serverIV;
	public IvParameterSpec clientIV;

	public User(DiffieHelmanHandler diffieHelmanHandler, String rsaKeyFileName, String certificateFileName) throws Exception {
		rsaKey = KeyMaker.makeKey(rsaKeyFileName);
		certificate = KeyMaker.makeCertificate(certificateFileName);
		caCertificate = KeyMaker.makeCertificate("CAcertificate.pem");
		signature = Signature.getInstance("SHA256withRSA");
		
		dhHandler = diffieHelmanHandler;
		dhPrivateKey = new BigInteger(32, new Random());
		dhPublicKey = dhHandler.generateDHKey(dhPrivateKey);
	}	
	
	public BigInteger makeSignedDiffieHelman() throws InvalidKeyException, SignatureException {
		signature.initSign(rsaKey);
		signature.update(dhPublicKey.toByteArray());
		return new BigInteger(signature.sign());
	}
	
	public void sendDHCredentials() throws IOException, InvalidKeyException, SignatureException {
		objectOutStream.writeObject(certificate);
		objectOutStream.writeObject(dhPublicKey);
		objectOutStream.writeObject(makeSignedDiffieHelman());
		System.out.println("Diffie Helman Credentials Sent");
	}
	
	public boolean verifyDHCredentials() throws Exception {
		Certificate receivedCert = (Certificate) objectInStream.readObject();
		BigInteger receievedDHKey = (BigInteger) objectInStream.readObject();
		BigInteger receivedDHSigned = (BigInteger) objectInStream.readObject();
		
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
	
	public void generateSecretKeys() throws NoSuchAlgorithmException, InvalidKeyException {
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
		Mac clientMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec clientMACSecretKeySpec = new SecretKeySpec(clientMACBytes, "HmacSHA256");
		clientMAC.init(clientMACSecretKeySpec);
		
		byte[] serverIVBytes = hkdfExpand(clientMACBytes, "server IV");
		serverIV = new IvParameterSpec(serverIVBytes);
		byte[] clientIVBytes = hkdfExpand(serverIVBytes, "client IV");
		clientIV = new IvParameterSpec(clientIVBytes);
		
		System.out.println("Secret Keys Generated");
	}
}
