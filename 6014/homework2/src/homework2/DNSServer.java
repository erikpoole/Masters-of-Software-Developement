package homework2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class DNSServer {
	DatagramSocket clientSocket;
	DatagramSocket googleSocket;
	DNSCache cache;

	public DNSServer() throws SocketException {
		clientSocket = new DatagramSocket(8053);
		googleSocket = new DatagramSocket(8153);
		cache = new DNSCache();
	}
	
	
	// ****************************************************************************************************
	// ****************************************************************************************************

	public void Listen() throws IOException {
		System.out.println("Listening...");

		DatagramPacket clientPacket = receivePacket(clientSocket);
		byte[] clientBytes = getBytesFromPacket(clientPacket);

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
				DatagramPacket googlePacket = receivePacket(googleSocket);
				byte[] googleBytes = getBytesFromPacket(googlePacket);
				
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
		SendResponseToClient(outputBytes, clientPacket);
		System.out.println();
	}
	
	
	// ****************************************************************************************************
	// ****************************************************************************************************

	private DatagramPacket receivePacket(DatagramSocket inSocket) throws IOException {
		byte[] inputBuffer = new byte[1024];
		DatagramPacket inPacket = new DatagramPacket(inputBuffer, inputBuffer.length);
		inSocket.receive(inPacket);
		return inPacket;
	}
	
	private byte[] getBytesFromPacket(DatagramPacket inPacket) {
		byte[] outputBytes = new byte[inPacket.getLength()];
		for (int i = 0; i < outputBytes.length; i++) {
			outputBytes[i] = inPacket.getData()[i];
		}
		return outputBytes;
	}
	

	// ****************************************************************************************************
	// ****************************************************************************************************

	private void SendRequestToGoogle(DNSMessage message) throws UnknownHostException, IOException {
		InetAddress googleIP = InetAddress.getByName("8.8.8.8");
		DatagramPacket outpacket = new DatagramPacket(message.getByteMessage(), message.getByteMessage().length,
				googleIP, 53);
		googleSocket.send(outpacket);
	}
	
	private void SendResponseToClient(byte[] outputBytes, DatagramPacket inPacket) throws IOException {
		DatagramPacket outpacket = new DatagramPacket(outputBytes, outputBytes.length, inPacket.getAddress(),
				inPacket.getPort());
		clientSocket.send(outpacket);
	}


}
