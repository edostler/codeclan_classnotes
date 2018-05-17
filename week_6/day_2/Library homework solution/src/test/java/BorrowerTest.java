import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BorrowerTest {

    Borrower borrower;
    Book book;
    Library library;

    @Before
    public void before(){
        library = new Library("Louise's Library", 100);
        borrower = new Borrower("Louise");
        book = new Book(Genre.CHILDREN, "Harry Potter");
    }

    @Test
    public void canTakeABook(){
        library.addBook(book);
        borrower.borrowBook(library);
        assertEquals(1, borrower.collectionSize());
    }
}
