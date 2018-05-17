class Library
  attr_accessor :books

  def initialize(books)
    @books = books
  end

  def get_info_from_title(title)
    for book in @books
      if book[:title] == title
        return book
      end
    end
    return nil
  end

  def get_rental_details(title)
    for book in @books
      if book[:title] == title
        return book[:rental_details]
      end
    end
    return nil
  end

  def add_book(title)
    book = {
      title: title,
      rental_details:
      {
        student_name: "",
        date: ""
      }
    }

    @books.push(book)
  end

  def change_rental_details(title, student, date)
    for book in @books
      if book[:title] == title
        book[:rental_details][:student_name] = student
        book[:rental_details][:date] = date
      end
    end
  end

end
