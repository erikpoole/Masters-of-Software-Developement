package homework2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


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

	// ****************************************************************************************************
	// ****************************************************************************************************

	@Override
	public String toString() {
		return "DNSMessage [byteMessage=" + Arrays.toString(byteMessage) + ", header=" + header + ", questions="
				+ Arrays.toString(questions) + ", answers=" + Arrays.toString(answers) + ", authorityRecords="
				+ Arrays.toString(authorityRecords) + ", additionalRecords=" + Arrays.toString(additionalRecords)
				+ ", domainNameLocations=" + "]";
	}

	public static String octetsToString(String[] octets) {
		String output = "";
		for (int i = 0; i < octets.length; i++) {
			output += octets[i];
			if (i < octets.length - 1) {
				output += '.';
			}
		}
		return output;
	}

	// ****************************************************************************************************
	// ****************************************************************************************************

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

	// ****************************************************************************************************
	// ****************************************************************************************************

	public static DNSMessage buildResponse(DNSMessage inputRequest, DNSRecord[] inputAnswers) {
		DNSMessage response = new DNSMessage();
		response.questions = inputRequest.getQuestions();
		response.answers = inputAnswers;
		response.authorityRecords = inputRequest.getAuthorityRecords();
		response.additionalRecords = inputRequest.getAdditionalRecords();
		response.header = DNSHeader.buildResponseHeader(inputRequest, response);

		return response;
	}

	public byte[] toBytes() {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		HashMap<String, Integer> domainNameLocations = new HashMap<>();

		header.writeBytes(outStream);
		for (DNSQuestion question : questions) {
			question.writeBytes(outStream, domainNameLocations);
		}
		for (DNSRecord record : answers) {
			record.writeBytes(outStream, domainNameLocations);
		}
		for (DNSRecord record : authorityRecords) {
			record.writeBytes(outStream, domainNameLocations);
		}
		for (DNSRecord record : additionalRecords) {
			record.writeBytes(outStream, domainNameLocations);
		}

		return outStream.toByteArray();
	}

	// ****************************************************************************************************
	// ****************************************************************************************************

	public String[] readDomainName(ByteArrayInputStream inStream) {
		ArrayList<String> labels = new ArrayList<>();
		while (true) {
			byte labelSize = (byte) inStream.read();
			// handling pointer to domain name
			if (labelSize < 0) {
				int mask = 0x3F;
				labelSize &= mask;
				labelSize <<= 8;
				labelSize |= inStream.read();
				return readDomainName(labelSize);
			}
			if (labelSize == 0) {
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

	// handling pointer to domain name
	public String[] readDomainName(int firstByte) {
		return readDomainName(new ByteArrayInputStream(byteMessage, firstByte, byteMessage.length - firstByte));
	}

	public static void writeDomainName(ByteArrayOutputStream outStream, HashMap<String, Integer> domainLocations,
			String[] domainPieces) {
		String domainKey = octetsToString(domainPieces);

		if (domainLocations.containsKey(domainKey)) {
			int intPointer = domainLocations.get(domainKey);
			byte secondByte = (byte) intPointer;
			intPointer >>= 8;
			byte firstByte = (byte) intPointer;
			byte mask = (byte) 0xc0;
			firstByte |= mask;

			outStream.write(firstByte);
			outStream.write(secondByte);

		} else {
			domainLocations.put(domainKey, outStream.size());

			for (int i = 0; i < domainPieces.length; i++) {
				outStream.write(domainPieces[i].length());
				for (char c : domainPieces[i].toCharArray()) {
					outStream.write(c);
				}
			}
			outStream.write(0);
		}
	}

	// ****************************************************************************************************
	// ****************************************************************************************************

	public static int decodeByteField(ByteArrayInputStream inStream, int numberOfBytes) {
		int output = 0;
		int mask = 0xff;
		for (int i = numberOfBytes - 1; i >= 0; i--) {
			int workingByte = inStream.read();
			workingByte &= mask;
			workingByte <<= (8 * i);
			output |= workingByte;
		}

		return output;
	}

	public static void writeField(int inputField, int numberOfBytes, ByteArrayOutputStream outStream) {
		byte arr[] = new byte[numberOfBytes];
		for (int i = numberOfBytes - 1; i >= 0; i--) {
			arr[i] = (byte) inputField;
			inputField >>= 8;
		}

		for (byte b : arr) {
			outStream.write(b);
		}
	}

}
