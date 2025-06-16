public class Book extends Media {
    private String author;
    private int pageCount;

    public Book(String name, String author, int pageCount) {
        super(name);
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public void play() {
        System.out.println("Opening book '" + name + "' using e-reader software");
    }

    @Override
    public String getDescription() {
        return "Book '" + name + "' - Author: " + author + ", Pages: " + pageCount;
    }
}
