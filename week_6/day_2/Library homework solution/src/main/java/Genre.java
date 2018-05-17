public enum Genre {

    CRIME("Criminal Stuff"),
    HORROR("Scary Stuff"),
    CHILDREN("Kittens and Stuff");

    private final String description;

    Genre(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
