package homework2;

import java.util.HashMap;

/*
 * 
This class is the local cache. It should basically just have a HashMap<DNSQuestion, DNSRecord> in it. 
You can just store the first answer for any question in the cache (a response for google.com might return 10 IP addresses, 
just store the first one). This class should have methods for querying and inserting records into the cache. 
When you look up an entry, if it is too old (its TTL has expired), remove it and return "not found."
 */

public class DNSCache {
	private HashMap<DNSQuestion, DNSRecord> map;
	
	public DNSCache() {
		map = new HashMap<>();
	}
	
	
	public HashMap<DNSQuestion, DNSRecord> getMap() {
		return map;
	}
	
	public DNSRecord search(DNSQuestion inputQuestion) {
		//check if entry is old
		//return Record if in map
		
		return null;
	}
	
	public void addRecord(DNSRecord inputRecord) {
		
	}

}
