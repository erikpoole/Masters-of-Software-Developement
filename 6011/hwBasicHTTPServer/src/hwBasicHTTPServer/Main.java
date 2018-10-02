package hwBasicHTTPServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {

	public static void main(String[] args) throws Exception {

		Server myserver = new Server(8080);
		while (true) {
			myserver.socket = myserver.listen();
			try {
				File file = myserver.httpRequest();
				myserver.httpResponse(file);
				myserver.socket.close();

			} catch (FileNotFoundException e) {
				myserver.respond404();
			} catch (BadRequestException | IOException | NoSuchElementException e) {
				myserver.respond400();
			}
			System.out.println();
		}

	}

}
