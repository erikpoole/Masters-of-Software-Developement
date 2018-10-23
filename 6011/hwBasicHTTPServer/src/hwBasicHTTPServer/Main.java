package hwBasicHTTPServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;

public class Main {

	public static void main(String[] args) throws Exception {

		Server thisServer = new Server(8080);
		while (true) {

			System.out.println("Server is Listening");
			Socket connectingSocket = thisServer.serverSocket.accept();
			ClientSocket clientSocket = new ClientSocket(connectingSocket);
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						File file = clientSocket.httpRequest();
						clientSocket.httpResponse(file);
						clientSocket.socket.close();

					} catch (FileNotFoundException e) {
						try {
							clientSocket.respond404();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					} catch (BadRequestException | IOException | NoSuchElementException e) {
						try {
							clientSocket.respond400();
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
