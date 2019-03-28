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
		
		byte bytes[] = client.messagesByteStream.toByteArray();
		System.out.println(bytes.length);
	}
}
