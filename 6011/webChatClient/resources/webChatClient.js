/*
We've now seen all the necessary pieces to create a web (html + css + js) frontend to our Slack clone.  
I've again provided a server: www.cs.utah.edu/~benjones/secret/ChatServer.jar (Links to an external site.)Links to an external site. 
that you can run with java -jar ChatServer.jar .  Again, the server will look for request files in the resources/ directory.  

The chat protocol works over WebSockets (a protocol that allows us to send/receive arbitrary messages).  It is very simple and insecure/dumb.

Once the client connects via websocket (the URL you'll want to use is "ws://" + location.host  ) it sends a message to join a room:  "join NameOfRoom" .  
Legal room names contain only lowercase letters.            (This is my end -> server end)

Once its joined/connected, it can post messages to the room.  
The message format is very simple/dumb:  username message  (the username is the first word, and the "message" is the rest of the words).

The server will send the messages from other users (all of them when the server starts up, and new ones as they arrive at the server).  
The server sends them in json format:

{

  "user" : "theUserWhoSentTheMessage",

  "message" : "... the message"

}

Create simple HTML & Javascript File for first page (Username/Room Entry)
	Working buttons and console.debug to list messages sent to/from server
Successfully change to blank second HTML page with AJAX
Add HTML / Javascript elements to second page
	Listen for server messages and append them to the page
Add functionality to second HTML/JS page to send message 
Add functionality to second HTML/JS page to leave room (return to first) and reset server connection
Add CSS elements to HTML page one
Add CSS elements to HTML page two

*/


"use strict";

window.onload = runProgram;
let mySocket;
let socketOpen = false;

let username;
let room;

function runProgram() {
    console.log('Working');
    mySocket = new WebSocket("ws://localhost:8080")
    mySocket.onopen = function () {
        socketOpen = true
        console.log(socketOpen);
    };

    let button = document.getElementById("button");
    button.addEventListener("click", sendJoinRequest);
}

function sendJoinRequest() {
    let room = document.getElementById("room").value;
    console.log(room);
    mySocket.send("join " + room);

    if (socketOpen) {
        console.log("listening");
        mySocket.onmessage = messageReceipt;
    }


}

function messageReceipt(event) {
    let response = event.data;
    console.log(response);
}