package hwBasicHTTPServer;

import java.io.IOException;
import java.util.ArrayList;

public class Room {

	public static ArrayList<ClientSocket> clientList = new ArrayList<ClientSocket>();

	public synchronized static void broadcastMessage(byte[] messageBytes, byte messageLength, String roomName)
			throws IOException {
		for (ClientSocket clientSocket : clientList) {
			System.out.println("Target Room: " + roomName);
			System.out.println("Current Room: " + clientSocket.room);
			// needs help - not sure why not matching
			if (roomName == clientSocket.room) {
				System.out.println("Matched!");
				clientSocket.sendMessage(messageBytes, messageLength);
			}
		}
	}
}
