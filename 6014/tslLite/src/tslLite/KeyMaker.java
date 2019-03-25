package tslLite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
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
	
	private static BigInteger readDHFromFile(String fileName) throws IOException {
		File dhFile = new File(fileName);
		FileInputStream dhStream = new FileInputStream(dhFile);
		byte dhbytes[] = dhStream.readAllBytes();
		dhStream.close();
		
		String dhString = "";
		for (byte dhByte : dhbytes) {
			if (dhByte != ' ' && dhByte != '\n') {
				dhString += (char) dhByte;
			}
		}
		return new BigInteger(dhString, 16);
	}
	
	
	private BigInteger dhPrime;
	private BigInteger dhGenerator;
	

	
	private KeyFactory keyFactory;
	//TODO "X.509"???
	private CertificateFactory certificateFactory;

	
	public KeyMaker() throws NoSuchAlgorithmException, CertificateException, IOException {
		dhPrime = readDHFromFile("diffieHelmanVariable.txt");
		dhGenerator = BigInteger.valueOf(2);
		
		keyFactory = KeyFactory.getInstance("RSA");
		certificateFactory = CertificateFactory.getInstance("X.509");
		
	}
	
	//TODO change read all bytes
	public BigInteger makeKey(String fileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		File key = new File(fileName);
		FileInputStream stream = new FileInputStream(key);
		byte keyBytes[] = stream.readAllBytes();
		stream.close();
		
		KeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		PrivateKey keyObj = keyFactory.generatePrivate(keySpec);
		
		return new BigInteger(keyObj.getEncoded());
	}
	
	public Certificate makeCertificate(String fileName) throws IOException, CertificateException {
		File key = new File(fileName);
		FileInputStream stream = new FileInputStream(key);

		return certificateFactory.generateCertificate(stream);		
	}
	
	
	public BigInteger generateDHKey(BigInteger privateKey) {
		return dhGenerator.modPow(privateKey, dhPrime);
	}
	
	public BigInteger generateDHSecret(BigInteger myPrivateKey, BigInteger theirHDKey) {
		return theirHDKey.modPow(myPrivateKey, dhPrime);
	}
	
	

	
	

}
