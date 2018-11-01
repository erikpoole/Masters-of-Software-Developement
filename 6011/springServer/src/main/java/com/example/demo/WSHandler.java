package com.example.demo;

import java.util.HashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WSHandler extends TextWebSocketHandler {

	public static HashMap<String, Room> roomNameAndRoom = new HashMap<String, Room>();
	public static HashMap<WebSocketSession, Room> clientAndRoom = new HashMap<WebSocketSession, Room>();

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage inputMessage) throws Exception {
		super.handleTextMessage(session, inputMessage);
		String stringMessage = inputMessage.getPayload();
		String[] messageArray = stringMessage.split("\\s+");

		if (messageArray[0].equals("serverJoin")) {
			addClient(session, messageArray[1]);
		} else {
			clientAndRoom.get(session).broadcastMessage(messageArray);
		}
	}

	private void addClient(WebSocketSession session, String roomName) {
		System.out.println();
		System.out.println("Join Room: " + roomName);
		if (!roomNameAndRoom.containsKey(roomName)) {
			roomNameAndRoom.put(roomName, new Room(roomName));
		}
		Room clientRoom = roomNameAndRoom.get(roomName);
		clientAndRoom.put(session, clientRoom);
		clientRoom.clients.add(session);
		logNumClients(clientRoom);
	}

	private void logNumClients(Room currentRoom) {
		System.out.println("Number of Rooms: " + roomNameAndRoom.size());
		System.out.println("Number of Clients in Room: " + currentRoom.clients.size());
		System.out.println("Number of Clients Total: " + clientAndRoom.size());
		System.out.println();
	}

//*************************************************************************************	
//*************************************************************************************	

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		removeclient(session);
	}

	private void removeclient(WebSocketSession session) {
		Room clientRoom = clientAndRoom.get(session);
		clientRoom.clients.remove(session);
		clientAndRoom.remove(session);
		if (clientAndRoom.isEmpty()) {
			roomNameAndRoom.remove(clientRoom.name);
		}
	}

}
