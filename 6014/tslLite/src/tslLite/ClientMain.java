package tslLite;

import userAndSubclasses.Client;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		
		Client client = new Client("clientPrivateKey.der", "CASignedClientCertificate.pem");

		client.connect();
		client.sendNonce();
		
		if (!client.verifyDHCredentials()) {
			System.err.println("Diffie Helman Credentials unable to be Verified");
			System.exit(0);
		};
		client.sendDHCredentials();
		client.generateSecretKeys();
		
		if (!client.verifyMessageRecord()) {
			System.err.println("Handshake Message unable to be Verified");
			System.exit(0);
		};
		client.sendMessageRecord();
		
		System.out.println("Handshake Completed");
		System.out.println();
		
		byte[] received = client.receiveMessage();
		client.sendMessage(received);
		
		client.sendFile("sampleFile.pdf");
		client.receiveMessage();
	}
}
