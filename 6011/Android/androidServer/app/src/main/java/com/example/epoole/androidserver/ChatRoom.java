package com.example.epoole.androidserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChatRoom extends AppCompatActivity {

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
    }
}
