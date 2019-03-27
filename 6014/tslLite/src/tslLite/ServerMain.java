package tslLite;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;


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

public class ServerMain {

	public static void main(String[] args) throws Exception {
		/* 
		 * Client: Nonce1 (32 bytes from a SecureRandom object)
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
		Server server = new Server(diffieHelmanHandler, "serverPrivateKey.der", "CASignedServerCertificate.pem");
		
		server.listen();
		
		
//			
//		byte[] nonce = client.sendNonce();
//		server.receiveNonce(nonce);
//		
//		System.out.println("Sending Server DH");
//		ByteArrayOutputStream serverOutStream = server.sendDHCredentials();
//		byte[] serverOutArray = serverOutStream.toByteArray();
//		ByteArrayInputStream clientInStream = new ByteArrayInputStream(serverOutArray);
//		boolean serverVerified = client.verifyDHCredentials(new ObjectInputStream(clientInStream));
//		
//		if (!serverVerified) {
//			System.out.println("Server Not Verified");
//			System.exit(0);
//		}
//		
//		System.out.println("Sending Client DH");
//		ByteArrayOutputStream clientOutStream = client.sendDHCredentials();
//		byte[] clientOutArray = clientOutStream.toByteArray();
//		ByteArrayInputStream serverInStream = new ByteArrayInputStream(clientOutArray);
//		boolean clientVerified = server.verifyDHCredentials(new ObjectInputStream(serverInStream));
//		
//		if (!serverVerified) {
//			System.out.println("Client Not Verified");
//			System.exit(0);
//		}
//		
//		server.generateSecretKeys();
//		client.generateSecretKeys();
		
	}

}
