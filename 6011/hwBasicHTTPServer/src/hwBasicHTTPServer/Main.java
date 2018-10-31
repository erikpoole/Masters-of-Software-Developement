package hwBasicHTTPServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Main {

	public static void main(String[] args) throws Exception {

		Server thisServer = new Server(8080);
		System.out.println("Server is Listening");
		System.out.println();
		while (true) {

			Socket connectingSocket = thisServer.serverSocket.accept();
			ClientSocket clientSocket = new ClientSocket(connectingSocket);
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						try {
							clientSocket.httpRequest();
							clientSocket.socket.close();

						} catch (FileNotFoundException e) {
							clientSocket.respond404();
						} catch (IOException | NoSuchElementException e) {
							clientSocket.respond400();

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					System.out.println();

				}
			});
			
			thread.start();

		}

	}

}
