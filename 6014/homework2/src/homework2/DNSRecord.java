package homework2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;

/*
//Everything after the header and question parts of the DNS message are stored as records. 
//This should have all the fields listed in the spec as well as a Date object storing when this record was created by your program. 
//It should also have the following public methods:
//
//static DNSRecord decodeRecord(InputStream, DNSMessage)
//
//static writeBytes(ByteArrayOutputStream, HashMap<String, Integer>)
//
//String toString()
//
//boolean timestampValid() -- 
//return whether the creation date + the time to live is after the current time. The Date and Calendar classes will be useful for this.

         0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
       |                                               |
       /                                               /
       /                      NAME                     /
       /                                               /
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
       |                      TYPE                     |
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
       |                     CLASS                     |
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
       |                      TTL                      |
       |                                               |
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
       |                   RDLENGTH                    |
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--|
       /                     RDATA                     /
       /                                               /
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+

 */

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

	static DNSRecord decodeRecord(ByteArrayInputStream inStream, DNSMessage inMessage) {
		DNSRecord record = new DNSRecord();

		record.name = inMessage.readDomainName(inStream);
		
		
		record.type |= inStream.read() << 8;
		record.type |= inStream.read();

		record.class0 |= inStream.read() << 8;
		record.class0 |= inStream.read();

		record.ttl |= inStream.read() << 24;
		record.ttl |= inStream.read() << 16;
		record.ttl |= inStream.read() << 8;
		record.ttl |= inStream.read();

		record.rdLength |= inStream.read() << 8;
		record.rdLength |= inStream.read();
		
		record.rData = new byte[record.rdLength];
		for (int i = 0; i < record.rdLength; i++) {
			record.rData[i] = (byte) inStream.read(); 
		}
		
		record.deathTime = LocalDateTime.now().plusSeconds(record.ttl);

//		System.out.println("Record:");
//		System.out.println(record.name);
//		System.out.println(record.type);
//		System.out.println(record.class0);
//		System.out.println(record.ttl);
//		System.out.println(record.rdLength);
//		System.out.println(record.rData);
//		System.out.println(record.deathDate);

		return record;
	}
	
	public void writeBytes(ByteArrayOutputStream outStream, HashMap<String, Integer> domainNameLocations) {
		DNSMessage.writeDomainName(outStream, domainNameLocations, name);
		
		int typeWorking = type;
		byte secondByte = (byte) typeWorking;
		typeWorking >>= 8;
		byte firstByte = (byte) typeWorking;
		outStream.write(firstByte);
		outStream.write(secondByte);
		
		int classWorking = class0;
		secondByte = (byte) classWorking;
		classWorking >>= 8;
		firstByte = (byte) classWorking;
		outStream.write(firstByte);
		outStream.write(secondByte);
		
		int ttlWorking = ttl;
		byte fourthByte = (byte) ttlWorking;
		ttlWorking >>= 8;
		byte thirdByte = (byte) ttlWorking;
		ttlWorking >>= 8;
		secondByte = (byte) ttlWorking;
		ttlWorking >>= 8;
		firstByte = (byte) ttlWorking;
		outStream.write(firstByte);
		outStream.write(secondByte);
		outStream.write(thirdByte);
		outStream.write(fourthByte);
		
		int rdLengthWorking = rdLength;
		secondByte = (byte) rdLengthWorking;
		rdLengthWorking >>= 8;
		firstByte = (byte) rdLengthWorking;
		outStream.write(firstByte);
		outStream.write(secondByte);
		
		for (byte b : rData) {
			outStream.write(b);
		}
	}

	boolean isTimestampValid() {		
		if (deathTime.isAfter(LocalDateTime.now())) {
			System.out.println("Record expires at: " + deathTime);
			return true;
		}
		System.out.println("Record expired!");
		return false;
	}
}
