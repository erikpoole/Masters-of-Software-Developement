package tslLite;

import java.math.BigInteger;
import java.util.Random;

public class User {
	public BigInteger rsaKey;
	
	public BigInteger dhPrivateKey;
	public BigInteger dhPublicKey;
	public BigInteger dhSecret;
	
	public User() {
		rsaKey = null;
		
		dhPrivateKey = new BigInteger(32, new Random());
		dhPublicKey = null;
		dhSecret = null;
	}
}
