package homework2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Main {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Listening...");
		DatagramSocket server = new DatagramSocket(8053);
		
		while (true) {
			
			byte[] inputBuffer = new byte[1024];
			

			DatagramPacket inPacket = new DatagramPacket(inputBuffer, inputBuffer.length);
			server.receive(inPacket);
			
			ByteArrayInputStream inStream = new ByteArrayInputStream(inPacket.getData());
			DNSHeader.decodeHeader(inStream);
			

		}
	}
}
