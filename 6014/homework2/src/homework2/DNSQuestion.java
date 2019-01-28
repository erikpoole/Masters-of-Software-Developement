package homework2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;


/* 
This class represents a client request. It should have the following public methods:

static DNSQuestion decodeQuestion(InputStream, DNSMessage) -- 
read a question from the input stream. 
Due to compression, the parent may actually needed to read some of the fields.

void writeBytes(ByteArrayOutputStream, HashMap<String,Integer> domainNameLocations) -- 
Write the question bytes which will be sent to the client. 
The hash map is used for us to compess the message, see the DNSMessage class below.

toString(), equals(), and hashCode() -- 
Let your IDE generate these. 
They're needed to use a question as a HashMap key, and to get a human readable string.
 */

public class DNSQuestion {
	private String[] qName;
	private int qType;
	private int qClass;

	
	public String[] getqName() {
		return qName;
	}
	
	void writeBytes(ByteArrayOutputStream outStream, HashMap<String, Integer> domainNameLocations) {
		
	}
	
	static DNSQuestion decodeQuestion(ByteArrayInputStream inStream, DNSMessage inMessage) {
		DNSQuestion question = new DNSQuestion();
		
		question.qName = inMessage.readDomainName(inStream);
		
		question.qType |= inStream.read() << 8;
		question.qType |= inStream.read();
		
		question.qClass |= inStream.read() << 8;
		question.qClass |= inStream.read();
		
//		System.out.println("Question:");
//		System.out.println(question.qName);
//		System.out.println(question.qType);
//		System.out.println(question.qClass);
		
		return question;
	}
		
}
