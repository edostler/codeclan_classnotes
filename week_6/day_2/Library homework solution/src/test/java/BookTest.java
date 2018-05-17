import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    Book book;

    @Before
    public void before(){
        book = new Book(Genre.CHILDREN, "Harry Potter");
    }

    @Test
    public void bookHasGenre(){
        assertEquals(Genre.CHILDREN, book.getGenre());
    }

    @Test
    public void bookGenreHasDescription(){
        assertEquals("Kittens and Stuff", book.getDescription());
    }


}
