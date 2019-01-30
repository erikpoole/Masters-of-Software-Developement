package homework2;

import java.util.HashMap;

public class DNSCache {
	private HashMap<DNSQuestion, DNSRecord> map;

	public DNSCache() {
		map = new HashMap<>();
	}
	
	// ****************************************************************************************************
	// ****************************************************************************************************

	public DNSRecord searchFor(DNSQuestion inputQuestion) {
		if (map.containsKey(inputQuestion)) {
			DNSRecord record = map.get(inputQuestion);
			if (record.isTimestampValid()) {
				return record;
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
