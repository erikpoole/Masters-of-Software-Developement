package tslLite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class DiffieHelmanHandler {
	private BigInteger dhPrime;
	private BigInteger dhGenerator;

	
	public DiffieHelmanHandler() throws IOException {
		dhPrime = readDHFromFile("diffieHelmanVariable.txt");
		dhGenerator = BigInteger.valueOf(2);
	}
	
	private static BigInteger readDHFromFile(String fileName) throws IOException {
		File dhFile = new File(fileName);
		FileInputStream dhStream = new FileInputStream(dhFile);
		byte dhbytes[] = dhStream.readAllBytes();
		dhStream.close();
		
		String dhString = "";
		for (byte dhByte : dhbytes) {
			if (dhByte != ' ' && dhByte != '\n') {
				dhString += (char) dhByte;
			}
		}
		return new BigInteger(dhString, 16);
	}
	
	public BigInteger generateDHKey(BigInteger privateKey) {
		return dhGenerator.modPow(privateKey, dhPrime);
	}
	
	public BigInteger generateDHSecret(BigInteger myPrivateKey, BigInteger theirHDKey) {
		return theirHDKey.modPow(myPrivateKey, dhPrime);
	}
	

}
