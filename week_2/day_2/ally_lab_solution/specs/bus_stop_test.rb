require('minitest/autorun')
require('minitest/rg')
require_relative('../bus_stop.rb')
require_relative('../person.rb')

class BusStopTest < MiniTest::Test

  def setup
    @stop = BusStop.new("Cowcaddens")
    @person = Person.new("Jeff Bridges", 60)
  end

  def test_can_add_person
    @stop.add_person(@person)
    assert_equal(1, @stop.queue_count())
  end

end
