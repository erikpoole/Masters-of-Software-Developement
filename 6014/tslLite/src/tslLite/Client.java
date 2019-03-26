package tslLite;

public class Client extends User {

	public Client(DiffieHelmanHandler diffieHelmanHandler, String rsaKeyFileName, String certificateFileName) throws Exception {
		super(diffieHelmanHandler, rsaKeyFileName, certificateFileName);
	}
	
}
