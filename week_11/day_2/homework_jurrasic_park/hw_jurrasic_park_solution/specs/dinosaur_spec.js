var assert = require('assert');
var Dinosaur = require('../Dinosaur');

describe("Dinosaur", function() {

  var tyrannosaurus;

  beforeEach(function() {
    tyrannosaurus = new Dinosaur("tyrannosaurus", 3);
  });

  it('should have a type', function(){
    assert.strictEqual(tyrannosaurus.type, "tyrannosaurus");
  });

  it('should have a number of offspring per year', function() {
    assert.strictEqual(tyrannosaurus.annualOffspring, 3);
  });

});
