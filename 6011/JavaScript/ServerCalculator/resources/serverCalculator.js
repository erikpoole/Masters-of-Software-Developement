/*
In this lab, you'll make an interactive calculator application that uses a web server (provided by me) to perform simple math and return the result.

Download the server program from here: http://www.cs.utah.edu/~benjones/secret/CalculatorServer.jar

It is a slightly modified version of the server you wrote a couple weeks ago.  
Place any files you want to serve in the resources directory (relative to wherever you run the server.  
I'd suggest putting the .jar file and your resources directory in the same folder), 
which should include at least an html file for the calculator app, and a javascript file to handle the interaction.  
You can run the server from the terminal using the command java -jar CalculatorServer.jar.  control-C should kill it.


The server is modified so that if you ask for a file that looks like:  
/calculate?x=14&y=25  it will give you back the answer x + y, which in this case is 39.                             (Put that in AJAX)
That weird looking format is actually pretty standard and is known as a "query string."  

Write a webpage (html + JS) that allows the user to enter numbers x and y and makes an XMLHttpRequest to the server to get the answer.  
DO NOT JUST ADD X AND Y YOURSELF IN YOUR FILE!  Display the result to the user, or display an error if there was an error.  
I'd suggest starting by just making the request when the window loads, and console.log -ing the result, 
and then adding on a more interesting interface after that.


The server also listens for web socket messages consisting of 2 space-separated numbers.  
Add an additional interface to your website that displays the server's computed result as transmitted via web sockets.
*/

"use strict";

window.onload = runProgram;


function runProgram() {
    console.log('Working');

    let button = document.getElementById("button");
    button.addEventListener("click", sendRequest);
}


function sendRequest() {
    let number1 = document.getElementById("number1").value;
    let number2 = document.getElementById("number2").value;
    let requestString = "/calculate?x=" + number1 + "&y=" + number2;

    let xhr = new XMLHttpRequest();
    xhr.open("GET", requestString);
    xhr.addEventListener("load", successCallback);
    xhr.addEventListener("error", failCallback);

    //xhr.overrideMimeType("text/plain");
    xhr.send();
}

function successCallback() {
    let response = this.responseText;
    document.getElementById("total").innerHTML = "Total: " + response;
}

function failCallback() {
    document.getElementById("total").innerHTML = "Failed! Bad Request."
}
