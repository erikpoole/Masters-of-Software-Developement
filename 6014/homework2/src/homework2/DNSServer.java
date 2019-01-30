package homework2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

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

		// TODO recieve message method changed
		byte[] inputBuffer = new byte[1024];
		DatagramPacket inPacket = new DatagramPacket(inputBuffer, inputBuffer.length);
		clientSocket.receive(inPacket);

		byte[] clientBytes = new byte[inPacket.getLength()];
		for (int i = 0; i < clientBytes.length; i++) {
			clientBytes[i] = inputBuffer[i];
		}

		DNSMessage clientMessage = DNSMessage.decodeMessage(clientBytes);
		ArrayList<DNSRecord> outputAnswers = new ArrayList<>();

		for (DNSQuestion question : clientMessage.getQuestions()) {
			System.out.println("Requesting " + DNSMessage.octetsToString(question.getqName()));
			DNSRecord answer = cache.searchFor(question);
			if (answer != null) {
				outputAnswers.add(answer);
			} else {
				System.out.println("Not In Cache!");
				SendRequestToGoogle(clientMessage);

				System.out.println("Waiting for Google Response...");
				byte[] googleBytes = RecieveMessage(googleSocket);
				DNSMessage googleMessage = DNSMessage.decodeMessage(googleBytes);
				System.out.println("Caching Google Response...");
				if (googleMessage.getAnswers().length != 0) {
					cache.addRecord(question, googleMessage.getAnswers()[0]);
					outputAnswers.add(googleMessage.getAnswers()[0]);
				}


			}
		}

		DNSMessage response = DNSMessage.buildResponse(clientMessage, outputAnswers.toArray(new DNSRecord[outputAnswers.size()]));
		byte outputBytes[] = response.toBytes();
		System.out.println("Sending Response To Client...");
		SendResponseToClient(outputBytes, inPacket);
		System.out.println();
	}

	private void SendResponseToClient(byte[] outputBytes, DatagramPacket inPacket) throws IOException {
		DatagramPacket outpacket = new DatagramPacket(outputBytes, outputBytes.length, inPacket.getAddress(),
				inPacket.getPort());
		clientSocket.send(outpacket);
	}

	private void SendRequestToGoogle(DNSMessage message) throws UnknownHostException, IOException {
		InetAddress googleIP = InetAddress.getByName("8.8.8.8");
		DatagramPacket outpacket = new DatagramPacket(message.getByteMessage(), message.getByteMessage().length,
				googleIP, 53);
		googleSocket.send(outpacket);
	}

	private static byte[] RecieveMessage(DatagramSocket inSocket) throws IOException {
		byte[] inputBuffer = new byte[1024];
		DatagramPacket inPacket = new DatagramPacket(inputBuffer, inputBuffer.length);
		inSocket.receive(inPacket);

		byte[] packetBytes = new byte[inPacket.getLength()];
		for (int i = 0; i < packetBytes.length; i++) {
			packetBytes[i] = inputBuffer[i];
		}
		return packetBytes;
	}
}
