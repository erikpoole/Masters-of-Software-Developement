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
	public Signature signature;

	private DiffieHelmanHandler dhHandler;
	public BigInteger dhPrivateKey;
	public BigInteger dhPublicKey;
	public BigInteger dhSecret;

	public User(DiffieHelmanHandler diffieHelmanHandler, String rsaKeyFileName, String certificateFileName) throws Exception {
		dhHandler = diffieHelmanHandler;
		dhPrivateKey = new BigInteger(32, new Random());
		dhPublicKey = dhHandler.generateDHKey(dhPrivateKey);

		rsaKey = KeyMaker.makeKey(rsaKeyFileName);
		certificate = KeyMaker.makeCertificate(certificateFileName);
		signature = Signature.getInstance("SHA256withRSA");
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
				
		signature.initVerify(receivedCert);
		signature.update(receievedDHKey.toByteArray());
		return signature.verify(receivedDHSigned.toByteArray());
	}
}
