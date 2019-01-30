package homework2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashMap;


/* 
//This class represents a client request. It should have the following public methods:
//
//static DNSQuestion decodeQuestion(InputStream, DNSMessage) -- 
//read a question from the input stream. 
//Due to compression, the parent may actually needed to read some of the fields.
//
//void writeBytes(ByteArrayOutputStream, HashMap<String,Integer> domainNameLocations) -- 
//Write the question bytes which will be sent to the client. 
//The hash map is used for us to compess the message, see the DNSMessage class below.
//
//toString(), equals(), and hashCode() -- 
//Let your IDE generate these. 
//They're needed to use a question as a HashMap key, and to get a human readable string.
 */

public class DNSQuestion {
	private String[] qName;
	private int qType;
	private int qClass;

	
	@Override
	public String toString() {
		return "DNSQuestion [qName=" + Arrays.toString(qName) + ", qType=" + qType + ", qClass=" + qClass + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + qClass;
		result = prime * result + Arrays.hashCode(qName);
		result = prime * result + qType;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DNSQuestion other = (DNSQuestion) obj;
		if (qClass != other.qClass)
			return false;
		if (!Arrays.equals(qName, other.qName))
			return false;
		if (qType != other.qType)
			return false;
		return true;
	}



	public void writeBytes(ByteArrayOutputStream outStream, HashMap<String, Integer> domainNameLocations) {
		DNSMessage.writeDomainName(outStream, domainNameLocations, qName);
		
		int qTypeWorking = qType;
		byte secondByte = (byte) qTypeWorking;
		qTypeWorking >>= 8;
		byte firstByte = (byte) qTypeWorking;
		outStream.write(firstByte);
		outStream.write(secondByte);
		
		System.out.println(qType);
		System.out.println(firstByte);
		System.out.println(secondByte);
		
		
		int qClassWorking = qClass;
		secondByte = (byte) qClassWorking;
		qClassWorking >>= 8;
		firstByte = (byte) qClassWorking;
		outStream.write(firstByte);
		outStream.write(secondByte);
		
		System.out.println(qClass);
		System.out.println(firstByte);
		System.out.println(secondByte);
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
