var assert = require("assert");
var Food = require("../food")


describe("Food", function() {
  var food

  beforeEach(function() {
    food = new Food("pizza", 25)
  })

  it('should have a name', function() {
    assert.strictEqual(food.name, "pizza")
  });

  it('should have a replenishment value', function() {
    assert.strictEqual(food.replenishValue, 25)
  })
})
