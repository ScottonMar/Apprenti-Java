package service;

import model.Book;
import exception.BookNotFoundException;
import exception.DuplicateISBNException;

import java.io.*;
import java.util.*;

public class LibraryRepository {
    private final File file;

    public LibraryRepository() {
        this("src/resources/books.txt"); // default path
    }

    public LibraryRepository(String filePath) {
        this.file = new File(filePath);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        if (!file.exists()) return books;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                books.add(Book.fromFileString(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading books file.");
        }

        return books;
    }

    public void saveBooks(List<Book> books) {
        try {
            // Ensure parent folder exists
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs(); // create src/resources if needed
            }

            // Create the file if it doesn’t exist
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (Book b : books) {
                    bw.write(b.toFileString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("[Err] Error writing to books file.");
        }
    }

    public void addBook(Book book) throws DuplicateISBNException {
        List<Book> books = getAllBooks();
        for (Book b : books) {
            if (b.getIsbn().equals(book.getIsbn())) {
                throw new DuplicateISBNException("ISBN already exists.");
            }
        }
        books.add(book);
        saveBooks(books);
    }

    public void updateBook(String id, Book updatedBook) throws BookNotFoundException {
        List<Book> books = getAllBooks();
        boolean found = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                books.set(i, updatedBook);
                found = true;
                break;
            }
        }

        if (!found) throw new BookNotFoundException("Book not found.");
        saveBooks(books);
    }

    public void removeBook(String id) throws BookNotFoundException {
        List<Book> books = getAllBooks();
        boolean removed = books.removeIf(b -> b.getId().equals(id));
        if (!removed) throw new BookNotFoundException("Book not found.");
        saveBooks(books);
    }

    public List<Book> findByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book b : getAllBooks()) {
            if (b.getCategory().equalsIgnoreCase(category)) {
                result.add(b);
            }
        }
        return result;
    }
}
