package service;

import model.Book;
import exception.BookNotFoundException;
import exception.DuplicateISBNException;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryRepositoryTest {

    private LibraryRepository repository;
    private static final String TEST_FILE = "test-books.txt";

    @BeforeEach
    void setUp() {
        // Use test-specific repository file
        repository = new LibraryRepository(TEST_FILE);

        // Clear or create test file
        File file = new File(TEST_FILE);
        if (file.exists()) file.delete();
    }

    @AfterEach
    void tearDown() {
        // Clean up test file after each test
        File file = new File(TEST_FILE);
        if (file.exists()) file.delete();
    }

    @Test
    void testAddBookSuccess() throws Exception {
        Book book = new Book("RepoTest", 1, 1, 1990, "Test Author", "ISBN-REPO-1");
        repository.addBook(book);
        List<Book> books = repository.getAllBooks();
        assertEquals(1, books.size());
        assertEquals("ISBN-REPO-1", books.get(0).getIsbn());
    }

    @Test
    void testAddBookDuplicateISBN() throws Exception {
        Book book1 = new Book("RepoTest", 1, 1, 1990, "Author1", "DUPLICATE");
        Book book2 = new Book("RepoTest", 1, 2, 1991, "Author2", "DUPLICATE");
        repository.addBook(book1);
        assertThrows(DuplicateISBNException.class, () -> repository.addBook(book2));
    }

    @Test
    void testFindByCategoryReturnsCorrectBooks() throws Exception {
        repository.addBook(new Book("SciFi", 1, 1, 2001, "A1", "ISBN001"));
        repository.addBook(new Book("SciFi", 1, 2, 2002, "A2", "ISBN002"));
        repository.addBook(new Book("Fantasy", 1, 3, 2003, "A3", "ISBN003"));

        List<Book> sciFiBooks = repository.findByCategory("SciFi");
        assertEquals(2, sciFiBooks.size());

        List<Book> fantasyBooks = repository.findByCategory("Fantasy");
        assertEquals(1, fantasyBooks.size());
    }

    @Test
    void testUpdateBookSuccess() throws Exception {
        Book original = new Book("UpdateCat", 2, 2, 2005, "Old Author", "UPDATE-ISBN");
        repository.addBook(original);

        Book updated = new Book("UpdateCat", 2, 2, 2006, "New Author", "UPDATE-ISBN");
        repository.updateBook(original.getId(), updated);

        List<Book> books = repository.getAllBooks();
        assertEquals("New Author", books.get(0).getAuthor());
        assertEquals(2006, books.get(0).getYearPublished());
    }

    @Test
    void testUpdateBookNotFoundThrowsException() {
        Book book = new Book("Cat", 5, 5, 2000, "Someone", "NOID");
        assertThrows(BookNotFoundException.class, () -> repository.updateBook("Nonexistent-1-1", book));
    }

    @Test
    void testRemoveBookSuccess() throws Exception {
        Book book = new Book("RemoveCat", 3, 3, 2000, "Author", "REMOVE-ISBN");
        repository.addBook(book);
        repository.removeBook(book.getId());

        List<Book> books = repository.getAllBooks();
        assertTrue(books.isEmpty());
    }

    @Test
    void testRemoveBookNotFoundThrowsException() {
        assertThrows(BookNotFoundException.class, () -> repository.removeBook("Fake-1-1"));
    }

    @Test
    void testGetAllBooksWhenEmptyReturnsEmptyList() {
        List<Book> books = repository.getAllBooks();
        assertTrue(books.isEmpty());
    }
}
