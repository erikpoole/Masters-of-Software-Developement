package homework2;

import java.util.HashMap;

public class DNSCache {
	private HashMap<DNSQuestion, DNSRecord> map;
	
	public DNSCache() {
		map = new HashMap<>();
	}
	
	public DNSRecord searchFor(DNSQuestion inputQuestion) {
		if (map.containsKey(inputQuestion)) {
			if (map.get(inputQuestion).isTimestampValid()) {
				return map.get(inputQuestion);
			} else {
				map.remove(inputQuestion);
			}
		}
		return null;
	}
	
	public void addRecord(DNSQuestion inputQuestion, DNSRecord inputRecord) {
		map.put(inputQuestion, inputRecord);
	}

}
