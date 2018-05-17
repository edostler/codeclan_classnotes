var assert = require("assert");
var Hero = require("../hero");
var Food = require("../food");
var Task = require("../task")
var Rat = require("../rat")

describe("Hero", function(){

var hero, spiders, task1, task2, rat

  beforeEach(function(){
    hero = new Hero("Spiderman", "spiders");
    spiders = new Food("spiders", 25);
    jelly = new Food("jelly", 10);
    task1 = new Task(5, 20, "fiver");
    task2 = new Task(10, 4, "sweetie");
    rat = new Rat();
    hero.addTask(task1);
    hero.addTask(task2);
  });

  it("should have a name", function(){
    assert.strictEqual(hero.name, "Spiderman")
  });

  it("should have health", function(){
    assert.strictEqual(hero.health, 100)
  });

  it("should have a favourite food", function(){
    assert.strictEqual(hero.favouriteFood, "spiders")
  });

  it("can talk, saying its name", function(){
    assert.strictEqual(hero.talk(), "I am Spiderman")
  });

  it("should be able to eat food, if its their fav they get a wee boost", function(){
    hero.eat(spiders);
    assert.strictEqual(hero.health, 137.5);
  });

  it("should be able to eat food, if its not their fav food they get a normal replenishment", function() {
    hero.eat(jelly);
    assert.strictEqual(hero.health, 110)
  });

  it("should be able to sort their tasks by difficulty, urgency or reward", function(){
    assert.strictEqual(hero.amountOfTasks(), 2)
    assert.deepStrictEqual(hero.sortTasks("difficulty"), [task2, task1])
  });

  it("can set a task as completed", function() {
    hero.completeTask(task1)
    assert.strictEqual(task1.completed, true)
  })

  it("can view tasks by completion status", function() {
    hero.completeTask(task2)
    assert.deepStrictEqual(hero.tasksByCompletion(true), [task2])
    assert.deepStrictEqual(hero.tasksByCompletion(false), [task1])
  })

  it("should loose health if it eats food poisoned by a rat", function(){
    rat.eat(jelly)
    hero.eat(jelly)
    assert.strictEqual(hero.health, 60)
  })


})
