package homework2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;


public class DNSRecord {
	private String[] name;
	private int type;
	private int class0;
	private int ttl;
	private int rdLength;
	private byte rData[];
	private LocalDateTime deathTime;	

	@Override
	public String toString() {
		return "DNSRecord [name=" + Arrays.toString(name) + ", type=" + type + ", class0=" + class0 + ", ttl=" + ttl
				+ ", rdLength=" + rdLength + ", rData=" + Arrays.toString(rData) + ", deathDate=" + deathTime + "]";
	}
	
	
	// ****************************************************************************************************
	// ****************************************************************************************************

	boolean isTimestampValid() {		
		if (deathTime.isAfter(LocalDateTime.now())) {
			System.out.println("Record expires at: " + deathTime);
			return true;
		}
		System.out.println("Record expired!");
		return false;
	}
	
	
	// ****************************************************************************************************
	// ****************************************************************************************************
	
	public void writeBytes(ByteArrayOutputStream outStream, HashMap<String, Integer> domainNameLocations) {
		DNSMessage.writeDomainName(outStream, domainNameLocations, name);
		
		DNSMessage.writeByteField(2, outStream, type);
		DNSMessage.writeByteField(2, outStream, class0);
		DNSMessage.writeByteField(4, outStream, ttl);
		DNSMessage.writeByteField(2, outStream, rdLength);
		
		for (byte b : rData) {
			outStream.write(b);
		}
	}
	
	static DNSRecord decodeRecord(ByteArrayInputStream inStream, DNSMessage inMessage) {
		DNSRecord record = new DNSRecord();

		record.name = inMessage.readDomainName(inStream);
		record.type = DNSMessage.decodeByteField(2, inStream);
		record.class0 = DNSMessage.decodeByteField(2, inStream);
		record.ttl = DNSMessage.decodeByteField(4, inStream);
		record.rdLength = DNSMessage.decodeByteField(2, inStream);
				
		record.rData = new byte[record.rdLength];
		for (int i = 0; i < record.rdLength; i++) {
			record.rData[i] = (byte) inStream.read(); 
		}
		
		record.deathTime = LocalDateTime.now().plusSeconds(record.ttl);

		return record;
	}

}
