package com.example.epoole.androidserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;


//More or less completely stolen from: en.proft.me/2018/05/10/quick-guide-websocket-communication-android
public class Login extends AppCompatActivity {

    public static final String USERNAME = "com.example.epoole.androidserver.USERNAME";
    public static final String ROOM = "com.example.epoole.androidserver.ROOM";


    public WebSocket ws = null;
    public String room;
    public String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        WebSocketFactory factory = new WebSocketFactory().setConnectionTimeout(5000);

        try {
            ws = factory.createSocket("ws://10.0.2.2:8080");

            ws.addListener(new WebSocketAdapter() {

                @Override
                public void onTextMessage(WebSocket webSocket, String message) throws Exception {

                    Log.d("TAG", "onTextMessage: " + message);
                }
            });

            ws.connectAsynchronously();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (ws != null) {
            ws.disconnect();
            ws = null;
        }
    }

    public void sendMessage(View v) {
        if (ws.isOpen()) {
            ws.sendText("Message from Android!");
        }
    }

    public void transferInput(View v) {
        Intent intent = new Intent(this, ChatRoom.class);
        EditText editUsername = (EditText) findViewById(R.id.usernameForm);
        EditText editRoom = (EditText) findViewById(R.id.roomForm);
        username = editUsername.getText().toString();
        room = editRoom.getText().toString();
        intent.putExtra(USERNAME, username);
        intent.putExtra(ROOM, room);
        startActivity(intent);
    }



}
