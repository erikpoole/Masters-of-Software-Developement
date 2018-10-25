
"use strict";

window.onload = runChatLogin;
let mySocket;
let socketOpen = false;

let username;
let room;


function runChatLogin() {
    console.log('Working');
    mySocket = new WebSocket("ws://" + location.host);
    mySocket.onopen = function () {
        socketOpen = true
        console.log(socketOpen);
    };

    let button = document.getElementById("joinButton");
    button.addEventListener("click", sendJoinRequest);

    let joinForm = document.getElementById("joinForm");
    fixEnterEvent(joinForm, sendJoinRequest);

    addBlankListener(document.getElementById("username"), button);
    addBlankListener(document.getElementById("room"), button);
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
        mySocket.onmessage = messageReceipt;
        mySocket.send("serverJoin " + room + " " + username);
        runChatRoom();
    });
    xhr.send();
}



function runChatRoom() {
    let sendButton = document.getElementById("sendButton");
    sendButton.addEventListener("click", sendMessage);

    let exitButton = document.getElementById("exitButton");
    exitButton.addEventListener("click", function () { window.location.reload(true)
        mySocket.send("serverExit");
     });

    let messageForm = document.getElementById("messageForm");
    fixEnterEvent(messageForm, sendMessage);

    addBlankListener(document.getElementById("messageBox"), sendButton);
}

function sendMessage() {
    let messageBox = document.getElementById("messageBox");
    mySocket.send(username + " " + messageBox.value);
    messageBox.value = "";
}

function messageReceipt(event) {
    let response = event.data;
    console.log(response);

    let newUser = document.createElement("b");
    let newUserText = document.createTextNode(response);
    newUser.appendChild(newUserText);
    document.getElementById("messages").appendChild(newUser);

    let newMessage = document.createElement("p");
    let newMessageText = document.createTextNode(response);
    newMessage.appendChild(newMessageText);
    document.getElementById("messages").appendChild(newMessage);
    newMessage.scrollIntoView({ behavior: "smooth" });
}



function clearElement(element) {
    element.value = "";
}

function fixEnterEvent(element, functionToCall) {
    element.addEventListener("keydown", function (event) {
        //'Enter key is key 13'
        if (event.keyCode == 13) {
            event.preventDefault();
            functionToCall();
        }
    });
}

function addBlankListener(element, button) {
    element.addEventListener("keyup", function () {
        button.disabled = false;
        if (element.value == "") {
            button.disabled = true;
        }
    });
}