package homework2;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		DNSServer server = new DNSServer();
		
		while (true) {
			byte[] messageBytes = server.Listen();
			DNSMessage.decodeMessage(messageBytes);
			

			

		}
	}
}
