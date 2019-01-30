package homework2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashMap;


public class DNSQuestion {
	private String[] qName;
	private int qType;
	private int qClass;

	public String[] getqName() {
		return qName;
	}
	
	@Override
	public String toString() {
		return "DNSQuestion [qName=" + Arrays.toString(qName) + ", qType=" + qType + ", qClass=" + qClass + "]";
	}
	
	
	// ****************************************************************************************************
	// ****************************************************************************************************

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
	
	
	// ****************************************************************************************************
	// ****************************************************************************************************

	public void writeBytes(ByteArrayOutputStream outStream, HashMap<String, Integer> domainNameLocations) {
		DNSMessage.writeDomainName(outStream, domainNameLocations, qName);
		DNSMessage.writeByteField(2, outStream, qType);
		DNSMessage.writeByteField(2, outStream, qClass);
	}

	public static DNSQuestion decodeQuestion(ByteArrayInputStream inStream, DNSMessage inMessage) {
		DNSQuestion question = new DNSQuestion();

		question.qName = inMessage.readDomainName(inStream);
		question.qType = DNSMessage.decodeByteField(2, inStream);
		question.qClass = DNSMessage.decodeByteField(2, inStream);

		return question;
	}

}
