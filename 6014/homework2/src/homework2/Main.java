package homework2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Listening...");

		while (true) {
			
			byte[] inputBuffer = new byte[1024];
			
			DatagramSocket server = new DatagramSocket(8053);
			DatagramPacket inPacket = new DatagramPacket(inputBuffer, inputBuffer.length);
			server.receive(inPacket);
			String inPacketString = new String(inPacket.getData());
			System.out.println("Connection!");
			System.out.println(inPacketString);
			server.close();
		}
	}

}
