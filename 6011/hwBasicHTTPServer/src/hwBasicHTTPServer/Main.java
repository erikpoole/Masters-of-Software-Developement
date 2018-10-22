package hwBasicHTTPServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Main {

	public static void main(String[] args) throws Exception {

		Server myserver = new Server(8080);
		while (true) {

			System.out.println("Server is Listening");
			Socket mySocket = myserver.serverSocket.accept();
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						File file = myserver.httpRequest(mySocket);
						myserver.httpResponse(file, mySocket);
						mySocket.close();

					} catch (FileNotFoundException e) {
						try {
							myserver.respond404(mySocket);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
					} catch (BadRequestException | IOException | NoSuchElementException e) {
						try {
							myserver.respond400(mySocket);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					System.out.println();
					
				}
			});
			thread.start();
			
			
		}

	}

}
