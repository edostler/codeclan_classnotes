require( 'sinatra' )
require( 'sinatra/contrib/all' )
require_relative( '../models/zombie.rb' )

get '/zombies' do
  @zombies = Zombie.all()
  erb ( :"zombies/index" )
end

get '/zombies/:id' do
  @zombie = Zombie.find(params['id'].to_i)
  erb(:"zombies/show")
end
