package hwBasicHTTPServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

public class Server {

	public ServerSocket serverSocket;
	public static HashMap<String, Room> roomList;
	
	// Constructor - takes socket number as argument
	// exception not caught - will crash if something goes wrong
	public Server(int socketNum) throws IOException {
		serverSocket = new ServerSocket(socketNum);
		roomList = new HashMap<String, Room>();
	}

//*************************************************************************************	
//*************************************************************************************

	public static synchronized void broadcastMessage(byte[] messageBytes, String roomName)
			throws IOException {
		for (ClientSocket socket : roomList.get(roomName).clientList)
			socket.sendMessage(messageBytes);
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
