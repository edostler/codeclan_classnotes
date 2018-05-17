// const logRed = function(message){
//   console.log(message);
// };
//
// const logNotRed(){
//   console.log("Its NOT red");
// };
//
// var redChecker = function(message, colour, isRed, isNotRed){
//   if(colour === "red"){
//     isRed(message);
//   }
//   else{
//     isNotRed();
//   }
// }
//
// redChecker("This will print out when the colour is red!", "red", logRed, logNotRed);

const myCallback = function(number){
  console.log(number);
}

const functionCaller = function(myCallback, number){
  console.log(myCallback(number));
}

console.log(functionCaller(myCallback, 100));


const increment = function(number){
  return number + 1;
}

const square = function(number){
  return number * number;
}

const doSomeMathsForMe = function(operation, number){
  return operation(number);
}
