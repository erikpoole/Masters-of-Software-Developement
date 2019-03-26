package tslLite;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.interfaces.DHPrivateKey;

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

	public static void main(String[] args) throws Exception {
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
		
		//diffie helman key should be random number
		//RSA secret - certificate and private key
		
		DiffieHelmanHandler diffieHelmanHandler = new DiffieHelmanHandler();
		User client = new Client(diffieHelmanHandler, "clientPrivateKey.der", "CASignedClientCertificate.pem");
		User server = new Server(diffieHelmanHandler, "serverPrivateKey.der", "CASignedServerCertificate.pem");
				

//		Certificate authorityCert = keyMaker.makeCertificate("CAcertificate.pem");
		
		Signature clientSig = Signature.getInstance("SHA256withRSA");
		Signature serverSig = Signature.getInstance("SHA256withRSA");
	
		clientSig.initSign(client.rsaKey);
		serverSig.initSign(server.rsaKey);
		
		clientSig.update(client.dhPublicKey.toByteArray());
		serverSig.update(server.dhPublicKey.toByteArray());
		
		byte clientDHSigned[] = clientSig.sign();
		byte serverDHSigned[] = serverSig.sign();
		
		
		clientSig.initVerify(server.certificate);
		serverSig.initVerify(client.certificate);
		
		clientSig.update(server.dhPublicKey.toByteArray());
		serverSig.update(client.dhPublicKey.toByteArray());
		
		System.out.println(clientSig.verify(serverDHSigned));
		System.out.println(serverSig.verify(clientDHSigned));
		
		
//		client.dhSecret = keyMaker.generateDHSecret(client.dhPrivateKey, server.dhPublicKey);
//		server.dhSecret = keyMaker.generateDHSecret(server.dhPrivateKey, client.dhPublicKey);
		
		
//		System.out.println(client.dhSecret);
//		System.out.println(server.dhSecret);
//		
//		System.out.println(clientDHSigned);
//		System.out.println(serverDHSigned);
		
		
		
	}

}
