package tslLite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;

/*
 * Authority certificate
Client certificate
Server certificate

Generate certificates
Do certificates by Wednesday

use readobject and writeobject to streams
Read in two certificates in the same program then later try and split it out

Nonces aren’t used until MAC at the end

Private Key only signs Diffie Helman 

RSA key factory - give private key to it and it produces a private key object



Save handshake messages - 
	use objectOutputStream(Byte Array Output Stream)
	objectOutputStream.getBytes

	Write at every send and receive

PKCS - for private key

Certificate Factory - Send your certificate, and Authority, read just one

Certificate.verify

Diffie Helman Keys - Bigintegers

Keep diffie helman and rsa keys separate

init.sign & init.verify for keys
 */

public class main {

	public static void main(String[] args) throws NoSuchAlgorithmException, CertificateException, IOException, InvalidKeySpecException {
		System.out.println("Hello World!");
		

		//Client: Nonce1 (32 bytes from a SecureRandom object)
		SecureRandom secureRandom = new SecureRandom();
		
		byte nonce1[] = new byte[32];
		secureRandom.nextBytes(nonce1);
		
		 /* 
		 * Server: Server Certificate, DiffieHellman public key, Signed DiffieHellman public key (Sign[g^ks % N, Spub])
		 * Client: Client Certificate, DiffieHellman public key, Signed DiffieHellman public key (Sign[g^kc % N, Cpub])
		 * //Client and server compute the shared secret here using DH
		 * //client and server derive 6 session keys from the shared secret. 2 each of bulk encryption keys, MAC keys, IVs for CBC using HKDF (below)
		 * Server: MAC(all handshake messages so far, Server's MAC key)
		 * Client: MAC(all handshake messages so far including the previous step, Client's MAC key).
		 */
		
		
		KeyMaker keyMaker = new KeyMaker();
		PrivateKey clientKey = keyMaker.makeKey("clientPrivateKey.der");
		PrivateKey serverKey = keyMaker.makeKey("serverPrivateKey.der");
		
		Certificate clientCert = keyMaker.makeCertificate("CASignedClientCertificate.pem");
		Certificate serverCert = keyMaker.makeCertificate("CASignedClientCertificate.pem");
		
		
		File dhFile = new File("diffieHelmanVariable.txt");
		FileInputStream dhStream = new FileInputStream(dhFile);
		byte dhbytes[] = dhStream.readAllBytes();
		
		String dhString = "";
		for (byte dhByte : dhbytes) {
			if (dhByte != ' ' && dhByte != '\n') {
				dhString += (char) dhByte;
			}
		}
		
		BigInteger dhPrime = new BigInteger(dhString, 16);
		int dhGenerator = 2;
		
		
		
		
		
	}

}
