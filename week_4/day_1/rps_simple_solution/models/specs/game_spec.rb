require('minitest/autorun')
require('minitest/rg')
require_relative('../game')

class GameTest < MiniTest::Test

def setup()
  @game = Game.new("Player 1", "Player 2")
end

def test_is_draw()
  assert_equal("It's a draw", @game.play("rock", "rock"))
end

def test_rock_beats_scissor()
  assert_equal("Player 1 wins", @game.play("rock", "scissors"))
end

def test_paper_beats_rock()
  assert_equal("Player 1 wins", @game.play("paper", "rock"))
end

def test_scissors_beats_paper()
  assert_equal("Player 1 wins", @game.play("scissors", "paper"))
end

def test_player2_wins()
  assert_equal("Player 2 wins", @game.play("paper", "scissors"))
end

end
