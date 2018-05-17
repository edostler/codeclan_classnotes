import db.DBHelper;
import models.Guitar;
import models.MusicBook;
import models.MusicStand;
import models.Piano;

public class Runner {

    public static void main(String[] args) {
        Guitar guitar = new Guitar("Fender", "White", 6, 500, 700);
        Piano piano = new Piano("Yamahah", "HotPink", 1000, 1500);
        MusicStand stand = new MusicStand("Blue", 4, 6);
        MusicBook book = new MusicBook("Learn music good", 5, 10);
        DBHelper.saveOrUpdate(guitar);
        DBHelper.saveOrUpdate(piano);
        DBHelper.saveOrUpdate(stand);
        DBHelper.saveOrUpdate(book);

        Guitar foundGuitar = DBHelper.find(Guitar.class, guitar.getId());
        Piano foundPiano = DBHelper.find(Piano.class, piano.getId());
        MusicStand foundStand = DBHelper.find(MusicStand.class, stand.getId());
        MusicBook foundBook = DBHelper.find(MusicBook.class, book.getId());
    }
}
