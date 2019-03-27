package tslLite;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		
		DiffieHelmanHandler diffieHelmanHandler = new DiffieHelmanHandler();
		Client client = new Client(diffieHelmanHandler, "clientPrivateKey.der", "CASignedClientCertificate.pem");

		client.connect();

	}

}
