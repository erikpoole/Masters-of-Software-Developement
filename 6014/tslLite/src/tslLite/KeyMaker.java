package tslLite;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;


public class KeyMaker {
	public static PrivateKey makeKey(String fileName) throws Exception {
		File key = new File(fileName);
		FileInputStream stream = new FileInputStream(key);
		byte keyBytes[] = stream.readAllBytes();
		stream.close();
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		KeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		return keyFactory.generatePrivate(keySpec);
	}
	
	public static Certificate makeCertificate(String fileName) throws Exception {
		File key = new File(fileName);
		FileInputStream stream = new FileInputStream(key);

		CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
		return certificateFactory.generateCertificate(stream);		
	}
}
