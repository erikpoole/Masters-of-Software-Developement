package tslLite;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		
		DiffieHelmanHandler diffieHelmanHandler = new DiffieHelmanHandler();
		Client client = new Client(diffieHelmanHandler, "clientPrivateKey.der", "CASignedClientCertificate.pem");

		client.connect();
		client.sendNonce();
		if (!client.verifyDHCredentials()) {
			System.err.println("Diffie Helman Credentials unable to be Verified");
			System.exit(0);
		};
		client.sendDHCredentials();
		client.generateSecretKeys();
	}

}
