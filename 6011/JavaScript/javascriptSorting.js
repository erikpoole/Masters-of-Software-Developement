/*
In this assignment, we'll write a sorting function in ANOTHER language!  We'll see YET ANOTHER way of comparing elements to be sorted.

First, create an empty HTML page + javascript file for this assignment.

Then, implement selection sort (a sort function, and a findMinLocation function) that take an array and sort it, like we did in C++ and java.  
Note, that your array parameter is essentially passed by pointer like object types in java (if you modify the contents of the parameter, you modify the contents of the original).

Test your sorting function with an array of ints, floating point numbers, strings, or a mix.  What happens for each case?

Now we'll add another parameter to our function so that the user can define how to compare the things in our array.  This function should take two parameters, and basically return "is a less than b".  
This is essentially a simpler version of the compareTo method from Java's comparable interface (or the guts of what you'd put in an overloaded operator< from C++). 

Verify that your sort function still works now that you take the comparator as a parameter.

Test that you can use your upgraded sort function to sort a list of names.  A "name" is an object with "first" and "last" fields.  
Names should be sorted by last name first, then by first name to break ties.
*/

'use strict';

console.log("Working");

function sort(inputArray, comparator) {
    for (let i = 0; i < inputArray.length; i++) {
        let smallLocation = findMinLocation(inputArray, i, comparator);
        let temp = inputArray[i];
        inputArray[i] = inputArray[smallLocation];
        inputArray[smallLocation] = temp;
    }
}

function findMinLocation(inputArray, startingIndex, comparator) {
    let minValue = inputArray[startingIndex];
    let location = startingIndex;
    for (let i = startingIndex; i < inputArray.length; i++) {
        if (comparator(inputArray[i], minValue)) {
            minValue = inputArray[i];
            location = i;
        }
    }
    return location
}

let myArray = [5, 10, 3, 1];
let myArray2 = [2, -3, -10, 3];
let floatArray = [3.1, 0.0, 4.6, -3.3];
let stringArray = ["Hi, ", "how", "are", "you"];
let mixedArray = ["Hi, ", 3.1, -1, "there"];

let nameArray = [
    { first: "Jared", last: "Hoffman" },
    { first: "Brent", last: "Rain" },
    { first: "Sean", last: "Evans" },
    { first: "Sarah", last: "Poole" },
    { first: "Erik", last: "Poole" }
]

let lessThan = function (a, b) {
    return a < b;
}

let greaterThan = function (a, b) {
    return a > b;
}

console.log(nameArray[0].first);

let nameSorter = function (a, b) {
    if (a.last == b.last) {
        return a.first < b.first;
    } else {
        return a.last < b.last;
    }
}

//same as sorted array, even though function call is after this line
console.log(myArray);

sort(myArray, lessThan);
console.log(myArray);

sort(myArray2, greaterThan);
console.log(myArray2);

sort(myArray2, lessThan);
console.log(myArray2);

sort(floatArray, greaterThan);
console.log(floatArray);

sort(stringArray, lessThan);
console.log(stringArray);

sort(mixedArray, greaterThan);
console.log(mixedArray);

sort(nameArray, nameSorter);
console.log(nameArray);