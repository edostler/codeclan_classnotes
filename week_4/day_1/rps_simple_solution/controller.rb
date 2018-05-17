require("sinatra")
require("sinatra/contrib/all")
require_relative('./models/game')

get ('/play/:choice1/:choice2') do
  game = Game.new("Player 1", "Player 2")
  choice1 = params[:choice1]
  choice2 = params[:choice2]
  @result = game.play(choice1, choice2)
  erb(:result)
end
