/*

Add CSS elements to HTML page one
Add CSS elements to HTML page two

*/

"use strict";

window.onload = runChatLogin;
let mySocket;
let socketOpen = false;

let username;
let room;
let inRoom = false;



function runChatLogin() {
    console.log('Working');
    mySocket = new WebSocket("ws://localhost:8080")
    mySocket.onopen = function () {
        socketOpen = true
        console.log(socketOpen);
    };

    let joinForm= document.getElementById("joinForm");
    addEnterEventHandler(joinForm, sendJoinRequest);

    let button = document.getElementById("joinButton");
    button.addEventListener("click", sendJoinRequest);
}

function sendJoinRequest() {
    if (socketOpen) {
        console.log("listening");

        room = document.getElementById("room").value;
        username = document.getElementById("username").value;

        switchPage("webChatRoom.html");
    }
}



function switchPage(pageName) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", pageName);
    xhr.addEventListener("load", function () {
        document.getElementById("body").innerHTML = this.responseText;
        if (inRoom) {
            inRoom = false;
            mySocket.close();
            mySocket.onclose = runChatLogin;
        } else {
            inRoom = true;
            mySocket.onmessage = messageReceipt;
            mySocket.send("join " + room);
            runChatRoom();
        }
    })
    xhr.send();
}



function runChatRoom() {
    let messageForm = document.getElementById("messageForm");
    addEnterEventHandler(messageForm, sendMessage);

    let sendButton = document.getElementById("sendButton");
    sendButton.addEventListener("click", sendMessage);

    let exitButton = document.getElementById("exitButton");
    exitButton.addEventListener("click", function () { switchPage("webChatLogin.html") });
}

function sendMessage() {
    mySocket.send(username + " " + document.getElementById("messageBox").value);
}

function messageReceipt(event) {
    let response = event.data;
    let parsed = JSON.parse(response);
    console.log(parsed);

    let newUser = document.createElement("b");
    let newUserText = document.createTextNode(parsed.user);
    newUser.appendChild(newUserText);
    document.getElementById("body").appendChild(newUser);

    let newMessage = document.createElement("p");
    let newMessageText = document.createTextNode(parsed.message);
    newMessage.appendChild(newMessageText);
    document.getElementById("body").appendChild(newMessage);
}



function addEnterEventHandler(form, functionToCall) {
    form.addEventListener("keydown", function(event) {
        //'Enter key is key 13'
        if (event.keyCode == 13) {
            event.preventDefault();
            functionToCall();
        }
    });
}