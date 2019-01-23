package homework2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Vector;

public class DNSQuestion {
	private Vector<String> labels = new Vector<>();
	//has character zero, not byte zero at end, maybe problematic
	private String qName = "";
	private int qType = 0;
	private int qClass = 0;

	//, DNSMessage inputMessage
	static DNSQuestion decodeQuestion(ByteArrayInputStream inStream) {
		DNSQuestion question = new DNSQuestion();
		
		while (true) {
			Byte labelSize = (byte) inStream.read();
			if (labelSize == 0) {
				break;
			}
			String label = "";
			for (int i = 0; i < labelSize; i++) {
				label += (char) inStream.read(); 
			}
			question.labels.add(label);	
		}
		
		for(String label : question.labels) {
			question.qName += label;
		}
		question.qName += 00;
		
		
		question.qType |= inStream.read() << 8;
		question.qType |= inStream.read();
		
		question.qClass |= inStream.read() << 8;
		question.qClass |= inStream.read();
		
		System.out.println(question.qName);
		System.out.println(question.qType);
		System.err.println(question.qClass);
		
		return question;
	}
	
	void writeBytes(ByteArrayOutputStream outStream, HashMap<String, Integer> domainNameLocations) {
		
	}
	
	
	
}
