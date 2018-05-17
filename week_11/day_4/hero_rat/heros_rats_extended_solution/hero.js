var _ = require("lodash")

var Hero = function(name, favouriteFood){
   this.name = name;
   this.health = 100;
   this.favouriteFood = favouriteFood;
   this.tasks = [];
}


Hero.prototype.talk = function () {
  return `I am ${this.name}`;
};

Hero.prototype.addTask = function (task) {
  this.tasks.push(task)
};

Hero.prototype.amountOfTasks = function () {
  return this.tasks.length
};

Hero.prototype.eat = function (food) {
  if(food.name === this.favouriteFood){
    this.health += (food.replenishValue * 1.5)
  } else {
    this.health += food.replenishValue
  }
};

Hero.prototype.sortTasks = function (property) {
  return _.orderBy(this.tasks, [property], ['desc'] )
};

Hero.prototype.completeTask = function (task) {
  task.completed = true
};

Hero.prototype.tasksByCompletion = function (status) {
  return _.filter(this.tasks, ['completed', status])
};




module.exports = Hero;
