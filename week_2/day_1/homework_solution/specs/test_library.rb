require('minitest/autorun')
require_relative("../library.rb")

class TestLibrary < MiniTest::Test

  def setup

    @books = [ {
    title: "lord_of_the_rings",
    rental_details: {
     student_name: "Jeff",
     date: "01/12/16"
    }
  }, {
  title: "lord_of_the_flies",
  rental_details: {
   student_name: "Jeff",
   date: "01/12/16"
  }
}]

  @library = Library.new(@books)
  end

  def test_get_books
    expected = @books
    assert_equal( expected, @library.books)
  end

  def test_get_book_info
    book_info = @library.get_info_from_title(@books[0][:title])

    expected = @books[0]
    assert_equal(expected, book_info)
  end

  def test_get_rental_details
    rental_details = @library.get_rental_details("lord_of_the_rings")
    expected = {
     student_name: "Jeff",
     date: "01/12/16"
    }

    assert_equal(expected, rental_details)
  end

  def test_add_book
    @library.add_book("Magician")
    result = @library.get_info_from_title("Magician")
    assert_equal("Magician", result[:title])
    assert_equal("", result[:rental_details][:student_name])
    assert_equal("", result[:rental_details][:date])
  end

  def test_change_rental_details
    @library.change_rental_details("lord_of_the_rings", "Fred", "30/01/2018")
    rental_details = @library.get_rental_details("lord_of_the_rings")

    assert_equal("Fred", rental_details[:student_name])

    assert_equal("30/01/2018", rental_details[:date])
  end

end
