package hwBasicHTTPServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

	public ServerSocket serverSocket;

	// Constructor - takes socket number as argument
	// exception not caught - will crash if something goes wrong
	public Server(int socketNum) throws IOException {
		serverSocket = new ServerSocket(socketNum);
	}
}
