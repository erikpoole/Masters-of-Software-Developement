package tslLite;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.util.Random;

public abstract class User {
	public PrivateKey rsaKey;
	public Certificate certificate;
	public Certificate caCertificate;
	public Signature signature;

	private DiffieHelmanHandler dhHandler;
	public BigInteger dhPrivateKey;
	public BigInteger dhPublicKey;
	public BigInteger dhSecret;

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
}
