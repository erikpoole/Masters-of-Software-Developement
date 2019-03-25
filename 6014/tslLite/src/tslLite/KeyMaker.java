package tslLite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

public class KeyMaker {
	private KeyFactory keyFactory;
	//TODO "X.509"???
	private CertificateFactory certificateFactory;
	
	public KeyMaker() throws NoSuchAlgorithmException, CertificateException {
		keyFactory = KeyFactory.getInstance("RSA");
		certificateFactory = CertificateFactory.getInstance("X.509");
	}
	
	//TODO change read all bytes
	public PrivateKey makeKey(String fileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		File key = new File(fileName);
		FileInputStream stream = new FileInputStream(key);
		byte keyBytes[] = stream.readAllBytes();
		stream.close();
		
		KeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		
		return keyFactory.generatePrivate(keySpec);
	}
	
	public Certificate makeCertificate(String fileName) throws IOException, CertificateException {
		File key = new File(fileName);
		FileInputStream stream = new FileInputStream(key);

		return certificateFactory.generateCertificate(stream);		
	}
	
	
	

}
