/*
Recreate (part of) the web page you created in the first week of this course using only the Javascript DOM api.

Create an HTML document wth the appropriate stuff in the <head> tag, and a body that is empty except for a <script> tag.
Use the DOM api to recreate the HTML/CSS page you wrote.
*/

'use strict';


function addTextElement(elementType, text) {
    let element = document.createElement(elementType);
    let textNode = document.createTextNode(text);
    document.body.appendChild(element);
    element.appendChild(textNode);
}

addTextElement('h1', 'This is a Title!');
addTextElement('p', 'This is a sentence!');
addTextElement('b', 'This is a bold sentence!');
document.body.appendChild(document.createElement('br'));
document.body.appendChild(document.createElement('br'));


let abbreviation = document.createElement('abbr');
let abbreviationText = document.createTextNode('The CRAP');
abbreviation.setAttribute('Title', 'Creative Renaissance Artists and Painters')
abbreviation.appendChild(abbreviationText);
document.body.appendChild(abbreviation);
document.body.appendChild(document.createElement('br'));
document.body.appendChild(document.createElement('br'));


let form = document.createElement('form');
form.textContent = 'Are hotdogs Sandwiches?';

let input1 = document.createElement('input');
input1.setAttribute('type', 'radio');
let text1 = document.createTextNode("Yes");
let input2 = document.createElement('input');
input2.setAttribute('type', 'radio');
let text2 = document.createTextNode("Hell No");

let basicForm = document.body.appendChild(form);
basicForm.appendChild(document.createElement('br'));
basicForm.appendChild(input1);
basicForm.appendChild(text1);
basicForm.appendChild(document.createElement('br'));
basicForm.appendChild(input2);
basicForm.appendChild(text2);
basicForm.appendChild(document.createElement('br'));


let listMain = document.createElement('ol');

function addListElement(text) {
    let element = document.createElement('li');
    let textNode = document.createTextNode(text);
    listMain.appendChild(element);
    element.appendChild(textNode);
}

addListElement('Make a terrible looking webpage');
addListElement('???');
addListElement('Profit!!!');
document.body.appendChild(listMain);


let link = document.createElement('a');
let linktext = document.createTextNode('This is a link to the whole of human knowledge');
link.href = 'https://www.wikipedia.org/';
document.body.appendChild(link);
link.appendChild(linktext);

/*


        <iframe width="560" height="315" 
        src="https://www.youtube.com/embed/DLzxrzFCyOs" 
        frameborder="0" allow="autoplay; encrypted-media" 
        allowfullscreen></iframe>

        <iframe width="1120" height="630" 
        src="https://www.youtube.com/embed/DLzxrzFCyOs" 
        frameborder="0" allow="autoplay; encrypted-media" 
        allowfullscreen></iframe>

        <iframe width="1680" height="945" 
        src="https://www.youtube.com/embed/DLzxrzFCyOs" 
        frameborder="0" allow="autoplay; encrypted-media" 
        allowfullscreen></iframe>


*/
