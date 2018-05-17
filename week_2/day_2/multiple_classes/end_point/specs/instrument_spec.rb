require("minitest/autorun")
require("minitest/rg")

require_relative("../instrument")

class InstrumentTest < MiniTest::Test

  def setup
    @instrument = Instrument.new("Les Paul", "guitar")
  end

  def test_has_name
    assert_equal("Les Paul", @instrument.name)
  end

  def test_has_type
    assert_equal("guitar", @instrument.type)
  end

  def test_can_play
    assert_equal("I'm playing Stairway to Heaven!", @instrument.play("Stairway to Heaven"))
  end

  def test_can_play__piano
    piano = Instrument.new("Yamaha", "piano")
    assert_equal("Plink plonk... I'm playing Ordinary People!", piano.play("Ordinary People"))
  end

end
