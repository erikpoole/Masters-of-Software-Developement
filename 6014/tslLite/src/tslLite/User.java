package tslLite;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.Signature;
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
		
}
