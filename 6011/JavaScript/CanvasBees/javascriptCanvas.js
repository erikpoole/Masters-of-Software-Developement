'use strict';

window.onload = startProgram;

function startProgram() {
    let canvas = document.getElementsByTagName("canvas")[0];
    canvas.style.cursor = "none";
    let ctx = canvas.getContext('2d');

    let nicImage = new Image();
    nicImage.src = 'Nicolas.jpg';
    let beeImage = new Image();
    beeImage.src = 'bee.png';

    let audio1 = new Audio();
    audio1.src = 'Audio1.mp3';
    let audio2 = new Audio();
    audio2.src = 'Audio2.mp3';
    let audio3 = new Audio();
    audio3.src = 'Audio3.mp3';
    let count = 0;

    let mouseX = 0;
    let mouseY = 0;

    let bees = [];


    window.requestAnimationFrame(update);


    document.addEventListener('mousedown', playaudio);
    function playaudio() {
        if (count % 3 == 0) {
            audio1.play();
        } else if (count % 3 == 1) {
            audio2.play();
        } else {
            audio3.play();
        }
        count++;
    }



    function createBees(number) {
        for (let i = 0; i < number; i++) {
            let locationX;
            let locationY;
            let locationRandomizer = Math.random();
            if (locationRandomizer < .25) {
                locationX = 50;
                locationY = Math.floor(Math.random() * 500) + 51;
            } else if (locationRandomizer < .5) {
                locationX = 1150;
                locationY = Math.floor(Math.random() * 500) + 51;
            } else if (locationRandomizer < .75) {
                locationX = Math.floor(Math.random() * 1100) + 51;
                locationY = 50;
            } else {
                locationX = Math.floor(Math.random() * 1100) + 51;
                locationY = 550;
            }
            let bee = { beeX: locationX, beeY: locationY };
            bees.push(bee);
        }
    }



    function update() {
        if (Math.random() < .05) {
            createBees(1);
        }
        ctx.clearRect(0, 0, 1200, 600);

        document.addEventListener('mousemove', moveObject);
        function moveObject(event) {
            mouseX = event.pageX;
            mouseY = event.pageY;
        }
        ctx.drawImage(nicImage, mouseX - 50, mouseY - 50, nicImage.width = 150, nicImage.height = 150);

        for (let i = 0; i < bees.length; i++) {
            if (bees[i].beeX < mouseX) {
                bees[i].beeX += 1;
            } else {
                bees[i].beeX -= 3;
            }
            if (bees[i].beeY < mouseY) {
                bees[i].beeY += 1;
            } else {
                bees[i].beeY -= 1;
            }

            bees[i].beeX += (Math.random() - Math.random()) * 3;
            bees[i].beeY += (Math.random() - Math.random()) * 3;

            ctx.drawImage(beeImage, bees[i].beeX, bees[i].beeY, beeImage.width = 75, beeImage.height = 75);
        }


        window.requestAnimationFrame(update);

    }
}


