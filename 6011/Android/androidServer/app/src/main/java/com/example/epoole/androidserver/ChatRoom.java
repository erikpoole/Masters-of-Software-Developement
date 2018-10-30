package com.example.epoole.androidserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;

public class ChatRoom extends AppCompatActivity {

    public WebSocket ws = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        Intent intent = getIntent();
        String username = intent.getStringExtra(Login.USERNAME);
        String room = intent.getStringExtra(Login.ROOM);
        TextView usernameTitle = findViewById(R.id.usernameTitle);
        TextView roomTitle = findViewById(R.id.roomTitle);
        usernameTitle.setText("Username: " + username);
        roomTitle.setText("Room: " + room);

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
}
