require('minitest/autorun')
require('minitest/rg')
require_relative('../bus.rb')
require_relative('../person.rb')
require_relative('../bus_stop.rb')

class TestBus < MiniTest::Test

  def setup
    @bus = Bus.new("Ocean Terminal", 22)
    @person = Person.new("Jeff Bridges", 60)
    @stop = BusStop.new("Cowcaddens")
  end

  def test_can_get_route
    assert_equal(22, @bus.route)
  end

  def test_can_get_name
    assert_equal("Ocean Terminal", @bus.destination)
end

  def test_bus_can_drive
    assert_equal("Brum brum", @bus.drive())
  end

  def test_passenger_count
    assert_equal(0, @bus.passenger_count())
  end

  def test_can_add_passenger
    @bus.pick_up(@person)
    assert_equal(1, @bus.passenger_count())
  end

  def test_can_drop_off
    @bus.pick_up(@person)
    @bus.drop_off(@person)
    assert_equal(0, @bus.passenger_count())
  end

  def test_can_empty
    @bus.pick_up(@person)
    @bus.pick_up(@person)

    @bus.empty()

    assert_equal(0, @bus.passenger_count())
  end

  def test_can_get_from_stop
    @stop.add_person(@person)
    @stop.add_person(@person)
    @bus.pick_up_from_stop(@stop)

    assert_equal(2, @bus.passenger_count())
    assert_equal(0, @stop.queue_count())


  end


end
