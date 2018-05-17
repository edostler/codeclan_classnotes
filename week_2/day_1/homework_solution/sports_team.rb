class SportsTeam

  attr_accessor :name, :players, :coach, :points

  def initialize(name, players, coach)
    @name = name
    @players = players
    @coach = coach
    @points = 0
  end

  def add_player(player)
    @players.push(player)
  end

  def find_player(name)
    return @players.include?(name)
  end

  def check_win(result)
    @points += 1 if result == "Win"
  end

end
