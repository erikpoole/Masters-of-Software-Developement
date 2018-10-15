/*
Create an empty html web page and an accompanying javascript file (loaded via  a <script> tag).

        Write "hello world" from Javascript, first using document.writeln(), second using console.log().  What's the difference?  Why would you want to use one or the other?
        
         
        
        Create an array literal containing a string, a boolean, an int, a floating point number, and an object.  
        
        Print your array, using console.log(), modify it somehow, then print it again.  Examine the console.  What do you notice?
        
         
        
        Define a function two add two parameters together.  First, use the more C++ style syntax:  function f(a, b){ ... }, then use the "function literal" syntax:  let myFunction = function(a, b){ ... }
        
        Which syntax do you prefer?  Can you detect any difference between the two ways of declaring functions?
        
        Test your functions using ints, floating point numbers, strings, and a mix of these.  What do you observe?
        
        Check your html + javascript files into your git repo.
*/

//same scope as C++/Java
let hello = "Hello World";

    //global scope
    //hello = "Hello World";

    //function scope
    //var hello = "Hello World";

//prints for user
document.writeln(hello);
//prints to console
console.log(hello);

let myArray = [myString = "Snoopy", myBool = false, myInt = 4, myFloat = 4.4, myObject = { }];
console.log(myArray);

//my bool will change in every location it's called
myArray.myBool = true;
console.log(myArray);

//declaration is global, can be called before definition
console.log(myfunction("pizza", 100.1));

function myfunction(a, b) {return a+b;}
//declares value
let myfunction2 = function(a,b) {return a+b;}

console.log(myfunction(5, 5));
console.log(myfunction2(5, 5));

//returns undefined for second parameter
console.log(myfunction2("Hiya "));

//doesn't use parameters beyond what is expected
console.log(myfunction(1, 2, 3));