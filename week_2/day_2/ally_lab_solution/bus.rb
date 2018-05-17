class Bus
  attr_accessor :route, :destination

  def initialize(destination, route)
    @destination = destination
    @route = route
    @passengers = []
  end

  def drive()
    return "Brum brum"
  end

  def passenger_count()
    return @passengers.count()
  end

  def pick_up(person)
    @passengers << person
  end

  def drop_off(person)
    @passengers.delete(person)
  end

  def empty()
    @passengers.clear()
  end

  def pick_up_from_stop(stop)
    for person in stop.get_queue_copy()
      pick_up(person)
    end
    stop.clear_queue()
  end
end
