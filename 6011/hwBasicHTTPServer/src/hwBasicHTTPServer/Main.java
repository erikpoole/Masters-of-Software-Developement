package hwBasicHTTPServer;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws Exception {

		try {
			Server server = new Server();
			server.openServer(8080);

		} catch (FileNotFoundException e) {
//			outputHeader.println("HTTP/1.1 404 FILE NOT FOUND");
//			outputHeader.println("Content-Length: 0");
//			outputHeader.println();
//			outputHeader.flush();
//			input.close();

		}

	}

}
