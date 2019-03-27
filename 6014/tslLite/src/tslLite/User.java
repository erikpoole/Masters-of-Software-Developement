package tslLite;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.Mac;
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
	
	public ByteArrayOutputStream sendDHCredentials() throws IOException, InvalidKeyException, SignatureException {
		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(oStream);
		objectStream.writeObject(certificate);
		objectStream.writeObject(dhPublicKey);
		objectStream.writeObject(makeSignedDiffieHelman());
		
		return oStream;
	}
	
	public boolean verifyDHCredentials(ObjectInputStream objectStream) throws Exception {
		Certificate receivedCert = (Certificate) objectStream.readObject();
		BigInteger receievedDHKey = (BigInteger) objectStream.readObject();
		BigInteger receivedDHSigned = (BigInteger) objectStream.readObject();
		
		receivedCert.verify(caCertificate.getPublicKey());
		
		signature.initVerify(receivedCert);
		signature.update(receievedDHKey.toByteArray());
		if (signature.verify(receivedDHSigned.toByteArray())) {
			dhSecret = dhHandler.generateDHSecret(dhPrivateKey,receievedDHKey);
			return true;
		}
		return false;
	}
	
	private byte[] hkdfSecretKeyExpand(byte key[], String tag) throws NoSuchAlgorithmException, InvalidKeyException {
		byte tagBytes[] = tag.getBytes();
		
		Mac mac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, tag + '1');
		mac.init(secretKeySpec);
		return Arrays.copyOfRange(mac.doFinal(), 0 , 16);
	}
	
	private byte[] hkdfIVParameterExpand(byte key[], String tag) throws NoSuchAlgorithmException, InvalidKeyException {
		byte tagBytes[] = tag.getBytes();
		
		Mac mac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, tag + '1');
		mac.init(secretKeySpec);
		return Arrays.copyOfRange(mac.doFinal(), 0 , 16);
	}
	
	public void generateSecretKeys() throws NoSuchAlgorithmException, InvalidKeyException {
		Mac mac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKeySpec = new SecretKeySpec(nonce, dhSecret.toString());
		mac.init(secretKeySpec);
		byte[] temp = mac.doFinal();
		
		byte[] serverEncrypt = hkdfSecretKeyExpand(temp, "server encrypt");
		byte[] clientEncrypt = hkdfSecretKeyExpand(serverEncrypt, "client encrypt");
		byte[] serverMAC = hkdfSecretKeyExpand(clientEncrypt, "server MAC");
		byte[] clientMAC = hkdfSecretKeyExpand(serverMAC, "client MAC");
		
		byte[] serverIV = hkdfIVParameterExpand(clientMAC, "server IV");
		byte[] clientIV = hkdfIVParameterExpand(serverIV, "client IV");
	}
	
	
//	def hdkfExpand(input, tag): //tag is a string, but probably convenient to take its contents as byte[]
//		okm = HMAC(key = input,  data = tag concatenated with a byte with value 1)
//		return first 16 bytes of okm
//
//
//	def makeSecretKeys(clientNonce, sharedSecretFromDiffieHellman):
//		prk = HMAC(key = clientNonce, data = sharedSecretFromDiffieHellman)
//		serverEncrypt = hkdfExpand(prk, "server encrypt")
//		clientEncrypt = hkdfExpand(serverEncrypt, "client encrypt")
//		serverMAC = hkdfExpand(clientEncrypt, "server MAC")
//		clientMAC = hkdfExpand(serverMAC, "client MAC")
//		serverIV = hkdfExpand(clientMAC, "server IV")
//		clientIV = hkdfExpand(serverIV, "client IV")
	
	
}
