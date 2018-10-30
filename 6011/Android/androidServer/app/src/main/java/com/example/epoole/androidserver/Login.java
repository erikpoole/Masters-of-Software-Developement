package com.example.epoole.androidserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;


//More or less completely stolen from: en.proft.me/2018/05/10/quick-guide-websocket-communication-android
public class Login extends AppCompatActivity {

    WebSocket ws = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        WebSocketFactory factory = new WebSocketFactory().setConnectionTimeout(5000);

        try {
            ws = factory.createSocket("ws://192.168.24.104:8888/ws/");

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
