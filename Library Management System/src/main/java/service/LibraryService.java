package service;

import model.Book;
import exception.BookNotFoundException;
import exception.DuplicateISBNException;

import java.util.List;

public class LibraryService {
    private final LibraryRepository repository;

    public LibraryService() {
        this.repository = new LibraryRepository(); // default
    }

    public LibraryService(LibraryRepository repository) {
        this.repository = repository; // injected (test)
    }

    public void addBook(Book book) throws DuplicateISBNException {
        validateBook(book);
        repository.addBook(book);
    }

    public void updateBook(String id, Book updatedBook) throws BookNotFoundException {
        validateBook(updatedBook);
        repository.updateBook(id, updatedBook);
    }

    public void removeBook(String id) throws BookNotFoundException {
        repository.removeBook(id);
    }

    public List<Book> findBooksByCategory(String category) {
        return repository.findByCategory(category);
    }

    private void validateBook(Book book) {
        if (book.getCategory() == null || book.getCategory().isBlank())
            throw new IllegalArgumentException("Category is required.");
        if (book.getShelfNumber() <= 0 || book.getShelfNumber() > 250)
            throw new IllegalArgumentException("Shelf Number must be between 1 and 250.");
        if (book.getPosition() <= 0 || book.getPosition() > 250)
            throw new IllegalArgumentException("Position must be between 1 and 250.");
        if (book.getYearPublished() > java.time.Year.now().getValue())
            throw new IllegalArgumentException("Year Published must be in the past.");
        if (book.getAuthor() == null || book.getAuthor().isBlank())
            throw new IllegalArgumentException("Author is required.");
        if (book.getIsbn() == null || book.getIsbn().isBlank())
            throw new IllegalArgumentException("ISBN is required.");
    }
}
