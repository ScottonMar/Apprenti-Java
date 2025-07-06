package model;

public class Book {
    private String category;
    private int shelfNumber;
    private int position;
    private int yearPublished;
    private String author;
    private String isbn;

    public Book(String category, int shelfNumber, int position, int yearPublished, String author, String isbn) {
        this.category = category;
        this.shelfNumber = shelfNumber;
        this.position = position;
        this.yearPublished = yearPublished;
        this.author = author;
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public int getPosition() {
        return position;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getId() {
        return category + "-" + shelfNumber + "-" + position;
    }

    public String toFileString() {
        return category + "|" + shelfNumber + "|" + position + "|" + yearPublished + "|" + author + "|" + isbn;
    }

    public static Book fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new Book(
                parts[0],
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]),
                parts[4],
                parts[5]
        );
    }
}
