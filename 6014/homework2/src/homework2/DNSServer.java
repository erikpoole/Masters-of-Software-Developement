package homework2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
	DatagramSocket clientSocket;
	DatagramSocket googleSocket;
	DNSCache cache;

	public DNSServer() throws SocketException {
		clientSocket = new DatagramSocket(8053);
		googleSocket = new DatagramSocket(8153);
		cache = new DNSCache();
	}

	public void Listen() throws IOException {
		System.out.println("Listening...");
		
		byte[] clientBytes = RecieveMessage(clientSocket);
		DNSMessage clientMessage = DNSMessage.decodeMessage(clientBytes);
		
		
		//TODO shouldn't use default .containsKey
		for (DNSQuestion question : clientMessage.getQuestions()) {
			if (cache.searchFor(question) != null) {
				System.out.println("Question already asked!");
				//send record
			}
			else {
				System.out.println("Never asked before!");
				SendRequestToGoogle(clientMessage);
				
				System.out.println("Waiting for Google Response...");
				byte[] googleBytes = RecieveMessage(googleSocket);
				DNSMessage googleMessage = DNSMessage.decodeMessage(googleBytes);
				cache.addRecord(question, googleMessage.getAnswers()[0]);
			}
		}
	}
	
	private void SendRequestToGoogle(DNSMessage message) throws UnknownHostException, IOException {
		InetAddress googleIP = InetAddress.getByName("8.8.8.8");
		DatagramPacket outpacket = new DatagramPacket(message.getByteMessage(), message.getByteMessage().length, googleIP, 53);
		googleSocket.send(outpacket);
	}

	private static byte[] RecieveMessage(DatagramSocket inSocket) throws IOException {
		byte[] inputBuffer = new byte[1024];
		DatagramPacket inPacket = new DatagramPacket(inputBuffer, inputBuffer.length);
		inSocket.receive(inPacket);
		
		byte [] packetBytes = new byte[inPacket.getLength()];
		for (int i = 0; i < packetBytes.length; i++) {
			packetBytes[i] = inputBuffer[i]; 
		}
		return packetBytes;
	}
}
