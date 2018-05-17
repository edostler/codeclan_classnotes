class Game

  def initialize(player1, player2)
      @player1 = player1
      @player2 = player2
  end

  def play(choice1, choice2)
    if choice1 == choice2
      return "It's a draw"
    end
    plays = ["rock", "paper", "scissors"]
    result = plays.index(choice1) - plays.index(choice2)
    if result == 1 || result == -2
      return "#{@player1} wins"
    else
      return "#{@player2} wins"
    end
  end
end
