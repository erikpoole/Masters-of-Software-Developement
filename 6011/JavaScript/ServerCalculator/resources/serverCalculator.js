
"use strict";

window.onload = runProgram;
let mySocket;
let socketOpen = false;

function runProgram() {
    console.log('Working');
    mySocket = new WebSocket("ws://localhost:8080")
    mySocket.onopen = function () { socketOpen = true };

    let button1 = document.getElementById("button1");
    button1.addEventListener("click", sendHTTPRequest);

    let button2 = document.getElementById("button2");
    button2.addEventListener("click", sendServerRequest);
}




function sendHTTPRequest() {
    let number1 = document.getElementById("number1").value;
    let number2 = document.getElementById("number2").value;
    let requestString = "/calculate?x=" + number1 + "&y=" + number2;

    let xhr = new XMLHttpRequest();
    xhr.open("GET", requestString);
    xhr.addEventListener("load", successCallbackHTTP);
    xhr.addEventListener("error", failCallbackHTTP);

    //xhr.overrideMimeType("text/plain");
    xhr.send();
}

function successCallbackHTTP() {
    let response = this.responseText;
    document.getElementById("total1").innerHTML = "Total: " + response;
}

function failCallbackHTTP() {
    document.getElementById("total1").innerHTML = "Failed! Bad Request."
}




function sendServerRequest() {
    let number3 = document.getElementById("number3").value;
    let number4 = document.getElementById("number4").value;
    let requestString = number3 + " " + number4;

    if (socketOpen) {
        mySocket.send(requestString);
        mySocket.onmessage = successCallbackServer;
        mySocket.onerror = failCallbackServer;
    }


}

function successCallbackServer(event) {
    let response = event.data;
    document.getElementById("total2").innerHTML = "Total: " + response;
}

function failCallbackServer() {
    document.getElementById("total1").innerHTML = "Failed! Bad Request."
}