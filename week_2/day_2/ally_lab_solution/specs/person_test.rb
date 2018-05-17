require('minitest/autorun')
require('minitest/rg')
require_relative('../person.rb')

class TestPerson < MiniTest::Test

  def setup
    @person = Person.new("Jeff Bridges", 60)
  end

  def test_has_name
    assert_equal("Jeff Bridges", @person.name)
  end

  def test_has_age
    assert_equal(60, @person.age)
  end

end
