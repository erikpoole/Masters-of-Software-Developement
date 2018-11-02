package com.example.epoole.androidserver;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//More or less completely stolen from: en.proft.me/2018/05/10/quick-guide-websocket-communication-android
public class ChatRoom extends AppCompatActivity {

    public WebSocket ws = null;
    public String username = null;
    public String room = null;

    public ArrayAdapter<String> adapter;
    public ArrayList<String> messageList = new ArrayList<String>();
    public Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        Intent intent = getIntent();
        username = intent.getStringExtra(Login.USERNAME);
        room = intent.getStringExtra(Login.ROOM);
        TextView usernameTitle = findViewById(R.id.usernameTitle);
        TextView roomTitle = findViewById(R.id.roomTitle);
        usernameTitle.setText("Username: " + username);
        roomTitle.setText("Room: " + room);

        final ListView messageDisplay = (ListView) findViewById(R.id.messages);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messageList);
        messageDisplay.setAdapter(adapter);

        WebSocketFactory factory = new WebSocketFactory().setConnectionTimeout(5000);

        try {
            Log.d("myDebug", "Activity Opened");
            //ws = factory.createSocket("ws://10.0.2.2:8080");
            ws = factory.createSocket("http://erikpooleserver.eastus.cloudapp.azure.com:8080/webChatLogin.html");

            ws.addListener(new WebSocketAdapter() {
                @Override
                public void onConnected(WebSocket webSocket, Map<String, List<String>> headers) throws Exception {
                    sendJoin();
                }
            });

            ws.addListener(new WebSocketAdapter() {
                @Override
                public void onTextMessage(WebSocket webSocket, final String message) throws Exception {
                    messageReceipt(message);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();

                            //attempting to get color working:
                            //
//                            int temp = messageDisplay.getChildCount();
//                            String temp2 = String.valueOf(temp);
//                            Log.d("myDebug", temp2);
                            messageDisplay.smoothScrollToPosition(adapter.getCount() - 1);
//                            messageDisplay.getChildAt(messageDisplay.getLastVisiblePosition() - messageDisplay.getFirstVisiblePosition()).setBackgroundColor(Color.GREEN);
                        }
                    });
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

    public void sendJoin() {
        if (ws.isOpen()) {
            Log.d("myDebug", "Join Sent");
            ws.sendText("serverJoin " + room);
        }
    }

    public void sendMessage(View v) {
        if (ws.isOpen()) {
            Log.d("myDebug", "Message Sent");
            EditText editMessage = (EditText) findViewById(R.id.messageBox);
            String message = editMessage.getText().toString();
            ws.sendText(username + " " + message);
            editMessage.getText().clear();
            editMessage.onEditorAction(EditorInfo.IME_ACTION_DONE);
        }
    }

    public void messageReceipt(String message) throws Exception {
        JSONObject jsonMessage = new JSONObject(message);
        String sendingUser = jsonMessage.getString("username");
        String sentMessage = jsonMessage.getString("message");

        Log.d("myDebug", "From: " + sendingUser);
        Log.d("myDebug", "Message:" + sentMessage);
        messageList.add(sendingUser + ": " + sentMessage);
    }
}
