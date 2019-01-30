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

	boolean isTimestampValid() {		
		if (deathTime.isAfter(LocalDateTime.now())) {
			System.out.println("Record expires at: " + deathTime);
			return true;
		}
		System.out.println("Record expired!");
		return false;
	}
	
	public void writeBytes(ByteArrayOutputStream outStream, HashMap<String, Integer> domainNameLocations) {
		DNSMessage.writeDomainName(outStream, domainNameLocations, name);
		
		DNSMessage.writeField(type, 2, outStream);
		DNSMessage.writeField(class0, 2, outStream);
		DNSMessage.writeField(ttl, 4, outStream);
		DNSMessage.writeField(rdLength, 2, outStream);
		
		for (byte b : rData) {
			outStream.write(b);
		}
	}
	
	static DNSRecord decodeRecord(ByteArrayInputStream inStream, DNSMessage inMessage) {
		DNSRecord record = new DNSRecord();

		record.name = inMessage.readDomainName(inStream);
		record.type = DNSMessage.decodeByteField(inStream, 2);
		record.class0 = DNSMessage.decodeByteField(inStream, 2);
		record.ttl = DNSMessage.decodeByteField(inStream, 4);
		record.rdLength = DNSMessage.decodeByteField(inStream, 2);
				
		record.rData = new byte[record.rdLength];
		for (int i = 0; i < record.rdLength; i++) {
			record.rData[i] = (byte) inStream.read(); 
		}
		
		record.deathTime = LocalDateTime.now().plusSeconds(record.ttl);

		return record;
	}

}
