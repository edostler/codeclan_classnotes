var assert = require("assert");
var Task = require("../task")

describe("Task", function(){

var task

  beforeEach(function(){
    task = new Task(10, 5, "fiver")
  })

  it('should have a difficulty', function(){
    assert.strictEqual(task.difficulty, 10)
  });

  it('should have an urgency', function(){
    assert.strictEqual(task.urgency, 5)
  });

  it('should have a reward', function(){
    assert.strictEqual(task.reward, "fiver")
  });

  it('should have a completed status', function(){
    assert.strictEqual(task.completed, false)
  })

})
