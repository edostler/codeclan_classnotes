import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;
    private String name;
    private int capacity;

    public Library(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.books = new ArrayList<>();
    }

    public int bookCollectionSize(){
        return this.books.size();
    }

    public void addBook(Book book){
        if(bookCollectionSize() < this.capacity){
            books.add(book);
        }
    }

    public Book handOutBook(){
        return books.remove(0);
    }
}
