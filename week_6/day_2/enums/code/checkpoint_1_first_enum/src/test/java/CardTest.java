import static org.junit.Assert.*;
import org.junit.*;

public class CardTest {

    Card card;

    @Before
    public void before(){
        card = new Card(SuitType.HEARTS);
    }

    @Test
    public void canGetSuit(){
        assertEquals(SuitType.HEARTS, card.getSuit());
    }

    //@Test
    //public void suitCanBeMispelled(){
    //  card = new Card("Heeaarts");
    //  assertEquals("Heeaarts", card.getSuit());
    //}

    //@Test
    //public void suitCanBeBananas(){
    //  card = new Card("Bananas");
    //  assertEquals("Bananas", card.getSuit());
    //}
}