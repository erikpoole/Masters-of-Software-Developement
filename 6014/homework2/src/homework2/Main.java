package homework2;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		DNSServer server = new DNSServer();
		
		while (true) {
			ByteArrayInputStream packetStream = server.Listen();
			
			DNSHeader.decodeHeader(packetStream);
			DNSQuestion.decodeQuestion(packetStream);
			DNSRecord.decodeRecord(packetStream);
			

		}
	}
}
