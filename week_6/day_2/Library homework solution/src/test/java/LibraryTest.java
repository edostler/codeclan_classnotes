import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LibraryTest {

    Library library;
    Book book;

    @Before
    public void before(){
        library = new Library("Louise's Library", 100);
        book = new Book(Genre.CHILDREN, "Harry Potter");
    }

    @Test
    public void canCountBooksInLibrary(){
        library.addBook(book);
        assertEquals(1, library.bookCollectionSize());
    }

    @Test
    public void canHandOutBooks(){
        library.addBook(book);
        library.handOutBook();
        assertEquals(0, library.bookCollectionSize());
    }



}
