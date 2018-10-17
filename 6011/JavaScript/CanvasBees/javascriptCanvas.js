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

    let mouseX = 0;
    let mouseY = 0;

    let bees = [];
    createBees(10);

    window.requestAnimationFrame(update);



    function createBees(number) {
        for (let i = 0; i < number; i++) {
            let locationX;
            let locationY;
            let locationRandomizer = Math.random();
            console.log(locationRandomizer);
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
        ctx.clearRect(0, 0, 1200, 600);

        document.addEventListener('mousemove', moveObject);
        function moveObject(event) {
            mouseX = event.pageX;
            mouseY = event.pageY;
        }

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
            console.log(bees[i].beeX);
        }



        ctx.drawImage(nicImage, mouseX - 50, mouseY - 50, nicImage.width = 150, nicImage.height = 150);

        window.requestAnimationFrame(update);

    }
}


