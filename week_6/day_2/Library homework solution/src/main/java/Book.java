public class Book {

    private Genre genre;
    private String title;

    public Book(Genre genre, String title) {
        this.genre = genre;
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription(){
        return this.genre.getDescription();
    }
}
