package homework2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class DNSHeader {
	public int id;
	private int qr;
	private int opcode;
	private int aa;
	private int tc;
	private int rd;
	private int ra;
	private int z;
	private int ad;
	private int cd;
	private int rcode;
	private int qdCount;
	private int anCount;
	private int nsCount;
	private int arCount;
	
	public int getQdCount() {
		return qdCount;
	}
	
	public int getAnCount() {
		return anCount;
	}
	
	public int getNsCount() {
		return nsCount;
	}
	
	public int getArCount() {
		return arCount;
	}
	
	@Override
	public String toString() {
		return "DNSHeader [id=" + id + ", qr=" + qr + ", opcode=" + opcode + ", aa=" + aa + ", tc=" + tc + ", rd=" + rd
				+ ", ra=" + ra + ", z=" + z + ", ad=" + ad + ", cd=" + cd + ", rcode=" + rcode + ", qdcount=" + qdCount
				+ ", ancount=" + anCount + ", nscount=" + nsCount + ", arcount=" + arCount + "]";
	}


	public void writeBytes(ByteArrayOutputStream outStream) {
		DNSMessage.writeByteField(2, outStream, id);
		
		byte qrByte = DNSMessage.alignBitField(qr, 7);
		byte opCodeByte = DNSMessage.alignBitField(opcode, 3);
		byte aaByte = DNSMessage.alignBitField(aa, 2);
		byte tcByte = DNSMessage.alignBitField(tc, 1);
		byte rdByte = DNSMessage.alignBitField(rd, 0);
		byte combinedByteOne = qrByte;
		combinedByteOne |= opCodeByte;
		combinedByteOne |= aaByte;
		combinedByteOne |= tcByte;
		combinedByteOne |= rdByte;
		outStream.write(combinedByteOne);
		
		byte raByte = DNSMessage.alignBitField(ra, 7);
		byte zByte = DNSMessage.alignBitField(z, 6);
		byte adByte = DNSMessage.alignBitField(ad, 5);
		byte cdByte = DNSMessage.alignBitField(cd, 4);
		byte rcodeByte = DNSMessage.alignBitField(rcode, 0);
		byte combinedByteTwo = raByte;
		combinedByteTwo |= zByte;
		combinedByteTwo |= adByte;
		combinedByteTwo |= cdByte;
		combinedByteTwo |= rcodeByte;
		outStream.write(combinedByteTwo);
		
		DNSMessage.writeByteField(2, outStream, qdCount);
		DNSMessage.writeByteField(2, outStream, anCount);
		DNSMessage.writeByteField(2, outStream, nsCount);
		DNSMessage.writeByteField(2, outStream, arCount);
	}
	
	public static DNSHeader decodeHeader(ByteArrayInputStream inStream) throws IOException {
		DNSHeader header = new DNSHeader();
		
		header.id = DNSMessage.decodeByteField(2, inStream);
		
		int mask = 0xf;
		byte headerByteTwo = (byte) inStream.read();
		header.qr = DNSMessage.decodeSingleBitField(headerByteTwo, 0);
		header.aa = DNSMessage.decodeSingleBitField(headerByteTwo, 5);
		header.tc = DNSMessage.decodeSingleBitField(headerByteTwo, 6);
		header.rd = DNSMessage.decodeSingleBitField(headerByteTwo, 7);
		header.opcode |= headerByteTwo >> 3;
		header.opcode &= mask;
		
		byte headerByteThree = (byte) inStream.read();
		header.ra = DNSMessage.decodeSingleBitField(headerByteThree, 0);
		header.z = DNSMessage.decodeSingleBitField(headerByteThree, 1);
		header.ad = DNSMessage.decodeSingleBitField(headerByteThree, 2);
		header.cd = DNSMessage.decodeSingleBitField(headerByteThree, 3);
		header.rcode |= headerByteThree;
		header.rcode &= mask;

		header.qdCount = DNSMessage.decodeByteField(2, inStream);
		header.anCount = DNSMessage.decodeByteField(2, inStream);
		header.nsCount = DNSMessage.decodeByteField(2, inStream);
		header.arCount = DNSMessage.decodeByteField(2, inStream);
		
		return header;
		
	}
	
	public static DNSHeader buildResponseHeader(DNSMessage request, DNSMessage response) {
		DNSHeader workingHeader = request.getHeader();
		
		workingHeader.id = request.getHeader().id;
		workingHeader.qr = 1;
		workingHeader.opcode = 0;
		workingHeader.aa = 0;
		workingHeader.tc = 0;
		workingHeader.rd = 1;
		workingHeader.ra = 1;
		workingHeader.z = 0;
		workingHeader.ad = 0;
		workingHeader.cd = 0;
		workingHeader.rcode = 0;
		workingHeader.qdCount = response.getQuestions().length;
		workingHeader.anCount = response.getAnswers().length;
		workingHeader.nsCount = response.getAuthorityRecords().length;
		workingHeader.arCount = response.getAdditionalRecords().length;

		return workingHeader;
	}

}
