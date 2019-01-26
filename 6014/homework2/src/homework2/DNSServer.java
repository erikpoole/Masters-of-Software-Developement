package homework2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/*
 * 
This class should open up a UDP socket (DatagramSocket class in Java), and listen for requests. 
When it gets one, it should look at all the questions in the request. 
If there is a valid answer in cache, add that the response, 
otherwise create another UDP socket to forward the request Google (8.8.8.8) and then await their response. 
Once you've dealt with all the questions, send the response back to the client.

Note: dig sends an additional record in the "additionalRecord" fields with a type of 41. 
You're supposed to send this record back in the additional record part of your response as well.
 */
public class DNSServer {
	DatagramSocket mySocket;
	DatagramSocket googleSocket;

	public DNSServer() throws SocketException {
		mySocket = new DatagramSocket(8053);
	}

	public byte[] Listen() throws IOException {
		System.out.println("Listening...");
		
		byte[] inputBuffer = new byte[1024];
		DatagramPacket inPacket = new DatagramPacket(inputBuffer, inputBuffer.length);
		mySocket.receive(inPacket);
		
		byte [] packetBytes = new byte[inPacket.getLength()];
		for (int i = 0; i < packetBytes.length; i++) {
			packetBytes[i] = inputBuffer[i]; 
		}

		return packetBytes;
	}
}
