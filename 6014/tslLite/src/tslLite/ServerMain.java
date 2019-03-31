package tslLite;

import userAndSubclasses.Server;

public class ServerMain {

	public static void main(String[] args) throws Exception {
		
		Server server = new Server("serverPrivateKey.der", "CASignedServerCertificate.pem");
		
		server.listen();
		server.receiveNonce();
		
		server.sendDHCredentials();
		if (!server.verifyDHCredentials()) {
			System.err.println("Diffie Helman Credentials unable to be Verified");
			System.exit(0);
		};
		server.generateSecretKeys();
		
		server.sendMessageRecord();		
		if (!server.verifyMessageRecord()) {
			System.err.println("Handshake Message unable to be Verified");
			System.exit(0);
		};
		
		System.out.println("Handshake Completed");
		System.out.println();
		
		String toSend = "Hello There!";
		server.sendMessage(toSend.getBytes());
		byte[] received = server.receiveMessage();
		
		if (!toSend.equals(new String(received))) {
			System.err.println("Sent and Received Messages are not Identical");
			System.err.println("Sent: " + toSend);
			System.err.println("Received: " + received);
			System.exit(0);
		};
		
		System.out.println("Message: " + new String(received));
		
		server.receiveFile();	
	}
}
