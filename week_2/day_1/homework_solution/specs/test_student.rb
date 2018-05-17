require('minitest/autorun')
require_relative('../student.rb')

class TestStudent < MiniTest::Test

  def setup
    @student = Student.new("Hector", 4)
  end

  def test_student_name
    assert_equal("Hector", @student.name)
  end

  def test_student_cohort
    assert_equal(4, @student.cohort)
  end

  def test_set_student_name
    @student.set_name("Bobby")
    assert_equal("Bobby", @student.name)
  end

  def test_set_student_cohort
    @student.set_cohort(5)
    assert_equal(5, @student.cohort)
  end

  def test_student_talks
    assert_equal("I can talk!", @student.talk())
  end

  def test_student_fav_language
    assert_equal("I love Ruby.", @student.say_favourite_language("Ruby"))
  end
end
