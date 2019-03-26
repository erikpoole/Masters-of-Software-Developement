package tslLite;

import java.security.SecureRandom;

public class Client extends User {

	public Client(DiffieHelmanHandler diffieHelmanHandler, String rsaKeyFileName, String certificateFileName) throws Exception {
		super(diffieHelmanHandler, rsaKeyFileName, certificateFileName);
	}
	
	public byte[] sendNonce() {
		System.out.println("Client: Sending Nonce...");
		
		SecureRandom secureRandom = new SecureRandom();
		byte nonce[] = new byte[32];
		secureRandom.nextBytes(nonce);
		
		return nonce;
	}

}
