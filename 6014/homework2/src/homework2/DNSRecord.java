package homework2;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

/*
 * Everything after the header and question parts of the DNS message are stored as records. 
 * This should have all the fields listed in the spec as well as a Date object storing when this record 
 * was created by your program. It should also have the following public methods:

static DNSRecord decodeRecord(InputStream, DNSMessage)

static writeBytes(ByteArrayOutputStream, HashMap<String, Integer>)

String toString()

boolean timestampValid() -- 
return whether the creation date + the time to live is after the current time. The Date and Calendar classes will be useful for this.

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
	private int rData;
	private LocalDate deathDate;

	static DNSRecord decodeRecord(ByteArrayInputStream inStream, DNSMessage inMessage) {
		DNSRecord record = new DNSRecord();

		record.name = inMessage.readDomainName(inStream);
		
		
		record.type |= inStream.read() << 8;
		record.type |= inStream.read();

		record.class0 |= inStream.read() << 8;
		record.class0 |= inStream.read();

		record.ttl |= inStream.read() << 8;
		record.ttl |= inStream.read();

		record.rdLength |= inStream.read() << 8;
		record.rdLength |= inStream.read();

		record.rData |= inStream.read() << 8;
		record.rData |= inStream.read();

		record.deathDate = LocalDate.now().plusDays(7);

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

	boolean isTimestampValid() {
		if (deathDate.isAfter(LocalDate.now())) {
			return true;
		}
		return false;
	}
}
