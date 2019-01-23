package homework2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DNSHeader {
	private int id = 0;
	private int qr = 0;
	private int opcode = 0;
	private int aa = 0;
	private int tc = 0;
	private int rd = 0;
	private int ra = 0;
	private int z = 0;
	private int ad = 0;
	private int cd = 0;
	private int rcode = 0;
	private int qdcount = 0;
	private int ancount = 0;
	private int nscount = 0;
	private int arcount = 0;
	
	private void writeBytes(OutputStream outStream) {
		
	}
	
	public String toString() {
		return null;
	}
	
	//most significant bit on left
	//byte to int is causing promotion problems
	public static DNSHeader decodeHeader(final ByteArrayInputStream inStream) throws IOException {
		DNSHeader header = new DNSHeader();
		byte[] inBuffer = new byte[12];
		inStream.read(inBuffer);
		
//		for (byte b : inBuffer) {
//			System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
//		}
		
		int mask = 0xf;
		
		header.opcode |= inBuffer[2] >> 3;
		header.opcode &= mask;

		header.rcode &= mask;

		header.id = handleTwoByteField(inBuffer, 0);
		header.qdcount = handleTwoByteField(inBuffer, 4);
		header.ancount = handleTwoByteField(inBuffer, 6);
		header.nscount = handleTwoByteField(inBuffer, 8);
		header.arcount = handleTwoByteField(inBuffer, 10);

		header.qr = handleSingleBitField(inBuffer, 2, 0);
		header.aa = handleSingleBitField(inBuffer, 2, 5);
		header.tc = handleSingleBitField(inBuffer, 2, 6);
		header.rd = handleSingleBitField(inBuffer, 2, 7);
		header.ra = handleSingleBitField(inBuffer, 3, 0);
		header.z = handleSingleBitField(inBuffer, 3, 1);
		header.ad = handleSingleBitField(inBuffer, 3, 2);
		header.cd = handleSingleBitField(inBuffer, 3, 3);
		
//		System.out.println();
//		System.out.println("0 & 1: " + Integer.toBinaryString(header.id));
//		System.out.println("2: " + Integer.toBinaryString(header.qr));
//		System.out.println("2: " + Integer.toBinaryString(header.opcode));
//		System.out.println("2: " + Integer.toBinaryString(header.aa));
//		System.out.println("2: " + Integer.toBinaryString(header.tc));
//		System.out.println("2: " + Integer.toBinaryString(header.rd));
//		System.out.println("3: " + Integer.toBinaryString(header.ra));
//		System.out.println("3: " + Integer.toBinaryString(header.z));
//		System.out.println("3: " + Integer.toBinaryString(header.ad));
//		System.out.println("3: " + Integer.toBinaryString(header.cd));
//		System.out.println("3: " + Integer.toBinaryString(header.rcode));
//		System.out.println("4 & 5: " + Integer.toBinaryString(header.qdcount));
//		System.out.println("6 & 7: " + Integer.toBinaryString(header.ancount));
//		System.out.println("8 & 9: " + Integer.toBinaryString(header.nscount));
//		System.out.println("10 & 11: " + Integer.toBinaryString(header.arcount));
		
		
		return header;
		
	}
	
	private static int handleTwoByteField(final byte[] inBuffer, int firstBytePosition) {
		int mask = 0xff;
		int byte1 = inBuffer[firstBytePosition] & mask;
		int byte2 = inBuffer[firstBytePosition+1] & mask;

		int output = 0;
		output |= byte1 << 8;
		output |= byte2;
		
		return output;
	}
	
	private static int handleSingleBitField(final byte[] inBuffer, int bytePosition, int bitPosition) {		
		int value = 0;
		int mask = 0xff;
		value |= inBuffer[bytePosition] << bitPosition;
		value &= mask;
		value >>= 7;
		
		return value;
	}
	
	
	public static DNSHeader buildResponseHeader(DNSMessage request, DNSMessage response) {
		return null;
	}

}
