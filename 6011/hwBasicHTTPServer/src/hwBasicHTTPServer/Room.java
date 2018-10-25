package hwBasicHTTPServer;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	
	public static HashMap<String, String> map = new HashMap<String, String>();
	public static ArrayList<ClientSocket> clientList = new ArrayList<ClientSocket>();
	
	public static void sendMessage() {
		for (String key : Room.map.keySet()) {
			for (ClientSocket clientSocket : clientList) {
				if (key == clientSocket.room) {
					System.out.println("Proof of Concept");
				}
			}
		}
	}
}
