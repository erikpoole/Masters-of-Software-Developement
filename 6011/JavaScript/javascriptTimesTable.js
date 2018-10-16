
'use strict';
let background = "white";

window.onload = createTable(10, 10);
window.setInterval(changeBackground, 1000);


function changeBackground() {
    if (background == "white") {
        background = "purple";
        document.body.style.backgroundColor = "purple";
    } else {
        background = "white";
        document.body.style.backgroundColor = "white";
    }
}


function mouseoverEnter() {
    this.classList.add("hovered");
}

function mouseoverLeave() {
    this.classList.remove("hovered");
}

function mouseClick() {
    let previous = document.getElementsByClassName("clicked");
    for (let i = 0; i < previous.length; i++) {
        previous[i].classList.remove("clicked");
    }
    this.classList.add("clicked")
}

function createTable(x, y) {
    let myTable = document.createElement('table');
    document.body.appendChild(myTable);
    myTable.setAttribute('id', 'myTable');
    for (let i = 1; i <= x; i++) {
        if (i == 1) {
            let myRow = document.createElement('tr');
            myTable.appendChild(myRow);
            for (let j = 0; j <= y; j++) {
                let myHeader = document.createElement('th');
                let headerText = document.createTextNode(j);
                myHeader.appendChild(headerText);
                myRow.appendChild(myHeader);
            }
        }
        let myRow = document.createElement('tr');
        myTable.appendChild(myRow);
        let myHeader = document.createElement('th');
        let headerText = document.createTextNode(i);
        myHeader.appendChild(headerText);
        myRow.appendChild(myHeader);
        for (let j = 1; j <= y; j++) {
            let myCell = document.createElement('td');
            let valueText = document.createTextNode(i * j);
            myCell.appendChild(valueText);
            myCell.addEventListener('mouseenter', mouseoverEnter);
            myCell.addEventListener('mouseleave', mouseoverLeave);
            myCell.addEventListener('mousedown', mouseClick);
            myRow.appendChild(myCell);
        }

    }
}