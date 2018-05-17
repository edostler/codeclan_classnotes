var Enumeration = function () {}

Enumeration.prototype.find = function (array, callback) {
  for (var object of array) {
    if (callback(object)) {
      return object;
    }
  }
}


Enumeration.prototype.map = function(array, callback){
  const newArray = [];
  for(let element of array){
    newArray.push(callback(element));
  }
  return newArray;
}

module.exports = Enumeration;
