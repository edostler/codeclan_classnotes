require( 'sinatra' )
require( 'sinatra/contrib/all' )
require_relative( '../models/victim.rb' )

get '/victims' do
  @victims = Victim.all()
  erb ( :"victims/index" )
end

get '/victims/:id' do
  @victim = Victim.find(params['id'].to_i)
  erb( :"victims/show" )
end
