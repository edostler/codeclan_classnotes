var Rat = function(){}

Rat.prototype.eat = function (food) {
  food.replenishValue -= 50
};


module.exports = Rat;
