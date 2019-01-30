package homework2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
//This corresponds to an entire DNS Message. It should contain:
//
//the DNS Header
//an array of questions
//an array of answers
//an array of "authority records" which we'll ignore
//an array of "additional records" which we'll almost ignore
//You should also store the the byte array containing the complete message in this class. 
//You'll need it to handle the compression technique described above
//
//It should have the following methods:
//
//static DNSMessage decodeMessage(byte[] bytes)

//String[] readDomainName(InputStream) --
//read the pieces of a domain name starting from the current position of the input stream
//
//String[] readDomainName(int firstByte) --
//same, but used when there's compression and we need to find the domain from earlier in the message. 
//This method should make a ByteArrayInputStream that starts at the specified byte and 
//call the other version of this method

//static DNSMessage buildResponse(DNSMessage request, DNSRecord[] answers) --
//build a response based on the request and the answers you intend to send back.

//byte[] toBytes() -- 
//get the bytes to put in a packet and send back

//static void writeDomainName(ByteArrayOutputStream, HashMap<String,Integer> domainLocations, String[] domainPieces) -- 
//If this is the first time we've seen this domain name in the packet, 
//write it using the DNS encoding (each segment of the domain prefixed with its length, 0 at the end), 
//and add it to the hash map. Otherwise, write a back pointer to where the domain has been seen previously.
//
//String octetsToString(String[] octets) -- 
//join the pieces of a domain name with dots ([ "utah", "edu"] -> "utah.edu" )
//
//String toString()
 */

public class DNSMessage {
	private byte byteMessage[];
	private DNSHeader header;
	private DNSQuestion questions[];
	private DNSRecord answers[];
	private DNSRecord authorityRecords[];
	private DNSRecord additionalRecords[];
	
	public byte[] getByteMessage() {
		return byteMessage;
	}
	
	public DNSHeader getHeader() {
		return header;
	}
	
	public void setHeader(DNSHeader header) {
		this.header = header;
	}
	
	public DNSQuestion[] getQuestions() {
		return questions;
	}
	
	public DNSRecord[] getAnswers() {
		return answers;
	}
	
	public DNSRecord[] getAuthorityRecords() {
		return authorityRecords;
	}
	
	public DNSRecord[] getAdditionalRecords() {
		return additionalRecords;
	}

	@Override
	public String toString() {
		return "DNSMessage [byteMessage=" + Arrays.toString(byteMessage) + ", header=" + header + ", questions="
				+ Arrays.toString(questions) + ", answers=" + Arrays.toString(answers) + ", authorityRecords="
				+ Arrays.toString(authorityRecords) + ", additionalRecords=" + Arrays.toString(additionalRecords)
				+ ", domainNameLocations=" + "]";
	}

	public String[] readDomainName(ByteArrayInputStream inStream) {
		ArrayList<String> labels = new ArrayList<>();
		while (true) {
			byte labelSize = (byte) inStream.read();
			//handling pointer to domain name
			if (labelSize < 0) {
				int mask = 0x3F;
				labelSize &= mask;
				labelSize <<= 8;
				labelSize |= inStream.read();
				return readDomainName(labelSize);
			}
			if (labelSize == 0) {
				labels.add("0");
				break;
			}
			String label = "";
			for (int i = 0; i < labelSize; i++) {
				label += (char) inStream.read(); 
			}
			labels.add(label);	
		}
		
		return labels.toArray(new String[labels.size()]);
	}
	
	public String[] readDomainName(int firstByte) {
		return readDomainName(new ByteArrayInputStream(byteMessage, firstByte, byteMessage.length-firstByte));
	}
//	
//	byte[] toBytes() -- 
//	get the bytes to put in a packet and send back
	
	public byte[] toBytes() {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//passed by value..?
		HashMap<String,	Integer> domainNameLocations = new HashMap<>();
		
		header.writeBytes(outStream);
		for (DNSQuestion question : questions) {
			question.writeBytes(outStream, domainNameLocations);
		}
		
//		System.out.println(outStream.size());
		
		for (DNSRecord record: answers) {
			record.writeBytes(outStream, domainNameLocations);
		}
		for (DNSRecord record: authorityRecords) {
			record.writeBytes(outStream, domainNameLocations);
		}
		for (DNSRecord record: additionalRecords) {
			record.writeBytes(outStream, domainNameLocations);
		}
		
		return outStream.toByteArray();
	}
	
	//TODO Error, last point
	public static String octetsToString(String[] octets) {
		String output = "";
		for (String octet : octets) {
			output += octet;
			output += '.';
		}
		return output;
	}
	
	
	public static DNSMessage decodeMessage(byte[] inputBytes) throws IOException {
		DNSMessage message = new DNSMessage();
		
		message.byteMessage = inputBytes;
		ByteArrayInputStream byteStream = new ByteArrayInputStream(inputBytes);
		
		
		message.header = DNSHeader.decodeHeader(byteStream);
		
		
		message.questions = new DNSQuestion[message.header.getQdCount()];
		for (int i = 0; i < message.questions.length; i++) {
			message.questions[i] = DNSQuestion.decodeQuestion(byteStream, message);
		}
		
		message.answers = new DNSRecord[message.header.getAnCount()];
		for (int i = 0; i < message.answers.length; i++) {
			message.answers[i] = DNSRecord.decodeRecord(byteStream, message);
		}
		
		message.authorityRecords = new DNSRecord[message.header.getNsCount()];
		for (int i = 0; i < message.authorityRecords.length; i++) {
			message.authorityRecords[i] = DNSRecord.decodeRecord(byteStream, message);
		}
		
		message.additionalRecords = new DNSRecord[message.header.getArCount()];
		for (int i = 0; i < message.additionalRecords.length; i++) {
			message.additionalRecords[i] = DNSRecord.decodeRecord(byteStream, message);
		}
		
		return message;
	}
	
		
	//TODO maybe problematic
	public static void writeDomainName(ByteArrayOutputStream outStream, HashMap<String,Integer> domainLocations, String[] domainPieces) {
		String domainKey = octetsToString(domainPieces);
		if (domainLocations.containsKey(domainKey)) {
			int intPointer = domainLocations.get(domainKey);
			byte secondByte = (byte) intPointer;
			intPointer >>= 8;
			byte firstByte = (byte) intPointer;
			int mask = 0xc0;
			mask <<= 8;
			intPointer |= mask;
			outStream.write(firstByte);
			outStream.write(secondByte);
		} else {
			domainLocations.put(octetsToString(domainPieces), outStream.size());
			for (int i = 0; i < domainPieces.length-1; i++) {
				outStream.write(domainPieces[i].length());
				for (char c : domainPieces[i].toCharArray()) {
					outStream.write(c);
				}
			}
			outStream.write(0);	
		}
	}
	
	public static DNSMessage buildResponse(DNSMessage inputRequest, DNSRecord[] inputAnswers) {
		DNSMessage response = new DNSMessage();
		response.questions = inputRequest.getQuestions();
		response.answers = inputAnswers;
		response.authorityRecords = inputRequest.getAuthorityRecords();
		response.additionalRecords = inputRequest.getAdditionalRecords();
		response.header = DNSHeader.buildResponseHeader(inputRequest, response);
		
		return response;
	}
}
