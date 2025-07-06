package service;

import model.Book;
import exception.DuplicateISBNException;
import exception.BookNotFoundException;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private LibraryService service;
    private static final String TEST_FILE = "test-books.txt";

    @BeforeEach
    void setup() {
        // Use a test-specific repository file
        LibraryRepository testRepo = new LibraryRepository(TEST_FILE);
        service = new LibraryService(testRepo);

        // Clear the test file
        File file = new File(TEST_FILE);
        if (file.exists()) file.delete();
    }

    @AfterEach
    void cleanup() {
        // Optionally delete the file after tests
        File file = new File(TEST_FILE);
        if (file.exists()) file.delete();
    }

    @Test
    void testAddBookSuccess() throws DuplicateISBNException {
        Book book = new Book("Test", 1, 1, 2000, "Author", "1234567890");
        service.addBook(book);
        List<Book> books = service.findBooksByCategory("Test");
        assertEquals(1, books.size());
    }

    @Test
    void testAddBookDuplicateISBN() throws DuplicateISBNException {
        Book book1 = new Book("Test", 1, 1, 2000, "Author", "ISBN001");
        Book book2 = new Book("Test", 1, 2, 2001, "Author", "ISBN001");
        service.addBook(book1);
        assertThrows(DuplicateISBNException.class, () -> service.addBook(book2));
    }

    @Test
    void testUpdateBookSuccess() throws Exception {
        Book book = new Book("Test", 1, 1, 2000, "Author", "ISBN002");
        service.addBook(book);
        Book updated = new Book("Test", 1, 1, 1999, "New Author", "ISBN002");
        service.updateBook("Test-1-1", updated);
        List<Book> result = service.findBooksByCategory("Test");
        assertEquals("New Author", result.get(0).getAuthor());
    }

    @Test
    void testRemoveBook() throws Exception {
        Book book = new Book("Test", 2, 3, 1995, "A", "ISBN999");
        service.addBook(book);
        service.removeBook("Test-2-3");
        assertTrue(service.findBooksByCategory("Test").isEmpty());
    }

    @Test
    void testValidationFailure() {
        Book invalid = new Book("", 0, 300, 2025, "", "");
        assertThrows(IllegalArgumentException.class, () -> service.addBook(invalid));
    }
}
