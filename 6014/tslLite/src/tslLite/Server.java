package tslLite;

public class Server extends User {

	public Server(DiffieHelmanHandler diffieHelmanHandler, String rsaKeyFileName, String certificateFileName) throws Exception {
		super(diffieHelmanHandler, rsaKeyFileName, certificateFileName);
	}
	
}
