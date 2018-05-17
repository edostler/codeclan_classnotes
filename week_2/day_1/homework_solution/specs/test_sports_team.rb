require('minitest/autorun')
require_relative("../sports_team.rb")

class TestSportsTeam < MiniTest::Test

  def setup
    @team = SportsTeam.new("My Team", ["Jeff Bridges", "Mo", "Curly", "Larry"], "Shemp")
  end

  def test_team_name
    assert_equal("My Team", @team.name)
  end

  def test_team_players
    assert_equal(["Jeff Bridges", "Mo", "Curly", "Larry"], @team.players)
  end

  def test_team_coach
    assert_equal("Shemp", @team.coach)
  end

  def test_set_coach
    @team.coach = "Mike"
    assert_equal("Mike", @team.coach )
  end

  def test_add_player
    @team.add_player("Jeff")
    assert_equal(5, @team.players.count())
  end

  def test_find_player
    assert_equal(true, @team.find_player("Mo"))
  end

  def test_points_start_at_0
    assert_equal(0, @team.points)
  end

  def test_win
    @team.check_win("Win")
    assert_equal(1, @team.points)
  end

  def test_loss
    @team.check_win("Lose")
    assert_equal(0, @team.points)
  end




end
