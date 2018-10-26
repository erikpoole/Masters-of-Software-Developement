package hwBasicHTTPServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

public class Server {

	public ServerSocket serverSocket;
	public static ArrayList<ClientSocket> clientList = new ArrayList<ClientSocket>();

	// Constructor - takes socket number as argument
	// exception not caught - will crash if something goes wrong
	public Server(int socketNum) throws IOException {
		serverSocket = new ServerSocket(socketNum);
	}

//*************************************************************************************	
//*************************************************************************************

	public static synchronized void broadcastMessage(byte[] messageBytes, byte messageLength, String roomName)
			throws IOException {
		for (ClientSocket clientSocket : clientList) {
			System.out.println("Target Room: " + roomName);
			System.out.println("Current Room: " + clientSocket.room);
			// needs help - not sure why not matching
			if (roomName.equals(clientSocket.room)) {
				System.out.println("Matched!");
				clientSocket.sendMessage(messageBytes, messageLength);
			}
		}
	}

	public static synchronized String calculateHash(String inputHash) throws NoSuchAlgorithmException {
		String protocolString = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
		String concatStrings = inputHash + protocolString;

		byte protocolBytes[] = concatStrings.getBytes();

		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		byte hashedBytes[] = messageDigest.digest(protocolBytes);

		return Base64.getEncoder().encodeToString(hashedBytes);
	}
}
