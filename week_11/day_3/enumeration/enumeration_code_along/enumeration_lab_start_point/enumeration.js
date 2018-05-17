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

Enumeration.prototype.filter = function (array, callback) {
  var filteredArray = [];
  for (var object of array) {
    if (callback(object)) {
      filteredArray.push(object);
    }
  }
  return filteredArray;
}

Enumeration.prototype.some = function(array, callback){
  for(let element of array){
    if(callback(element)){
      return true;
    }
  }
  return false;
}

Enumeration.prototype.every = function(array, callback){
  for(let element of array){
    if(!callback(element)){
      return false;
    }
  }
  return true;
}

Enumeration.prototype.reduce = function(array, callback){
  let total = 0;
  for(var element of array){
    total = callback(total, element);
  }
  return total;
}



module.exports = Enumeration;
