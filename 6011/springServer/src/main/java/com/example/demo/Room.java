package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class Room {
	
	String name;
	
	public Room(String input) {
		name = input;
	}

	ArrayList<WebSocketSession> clients = new ArrayList<WebSocketSession>();

	public void broadcastMessage(String[] messageArray) throws IOException {
		String jsonMessage = "{ \"username\":\"" + messageArray[0] + "\" , \"message\":\"" + messageArray[1] + "\" }";
		TextMessage output = new TextMessage(jsonMessage);
		System.out.println("Broadcasting Message: " + jsonMessage);
		for (WebSocketSession client : clients) {
			client.sendMessage(output);
		}

	}

}
