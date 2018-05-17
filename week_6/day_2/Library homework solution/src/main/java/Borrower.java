import java.util.ArrayList;

public class Borrower {

    private String name;
    private ArrayList<Book> collection;

    public Borrower(String name) {
        this.name = name;
        this.collection = new ArrayList<>();
    }

    public int collectionSize(){
        return collection.size();
    }

    public void borrowBook(Library library){
        Book book = library.handOutBook();
        collection.add(book);

    }


}
