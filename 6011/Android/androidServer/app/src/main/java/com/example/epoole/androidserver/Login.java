package com.example.epoole.androidserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.neovisionaries.ws.client.WebSocket;


//More or less completely stolen from: en.proft.me/2018/05/10/quick-guide-websocket-communication-android
public class Login extends AppCompatActivity {

    public static final String USERNAME = "com.example.epoole.androidserver.USERNAME";
    public static final String ROOM = "com.example.epoole.androidserver.ROOM";

    public String room;
    public String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
