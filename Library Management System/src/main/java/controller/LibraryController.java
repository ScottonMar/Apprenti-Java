package controller;

import model.Book;
import service.LibraryService;
import exception.BookNotFoundException;
import exception.DuplicateISBNException;

import java.util.List;
import java.util.Scanner;

public class LibraryController {
    private final LibraryService service = new LibraryService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("""
                    \nMain Menu
                    =========
                    0. Exit
                    1. Find Books by Category
                    2. Add a Book
                    3. Update a Book
                    4. Remove a Book
                    Select [0-4]:""");
            switch (scanner.nextLine()) {
                case "0" -> { return; }
                case "1" -> findByCategory();
                case "2" -> addBook();
                case "3" -> updateBook();
                case "4" -> removeBook();
                default -> System.out.println("[Err] Invalid option.");
            }
        }
    }

    private void findByCategory() {
        System.out.print("Category: ");
        String category = scanner.nextLine();
        List<Book> books = service.findBooksByCategory(category);
        System.out.println("Books in " + category);
        System.out.println("Shelf Pos Year Author ISBN");
        for (Book b : books) {
            System.out.printf("%d\t%d\t%d\t%s\t%s%n",
                    b.getShelfNumber(), b.getPosition(), b.getYearPublished(), b.getAuthor(), b.getIsbn());
        }
    }

    private void addBook() {
        try {
            Book book = readBookInput();
            service.addBook(book);
            System.out.println("[Success] Book " + book.getId() + " added.");
        } catch (Exception e) {
            System.out.println("[Err] " + e.getMessage());
        }
    }

    private void updateBook() {
        try {
            System.out.print("Category: ");
            String category = scanner.nextLine();
            System.out.print("Shelf Number: ");
            int shelf = Integer.parseInt(scanner.nextLine());
            System.out.print("Position: ");
            int pos = Integer.parseInt(scanner.nextLine());

            String id = category + "-" + shelf + "-" + pos;
            System.out.println("Editing " + id + ". Press [Enter] to keep original value.");

            Book old = new Book(category, shelf, pos, 0, "", ""); // placeholder
            Book updated = readBookInputAllowEmpty(old);
            service.updateBook(id, updated);
            System.out.println("[Success] Book " + updated.getId() + " updated.");
        } catch (Exception e) {
            System.out.println("[Err] " + e.getMessage());
        }
    }

    private void removeBook() {
        try {
            System.out.print("Category: ");
            String category = scanner.nextLine();
            System.out.print("Shelf Number: ");
            int shelf = Integer.parseInt(scanner.nextLine());
            System.out.print("Position: ");
            int pos = Integer.parseInt(scanner.nextLine());

            String id = category + "-" + shelf + "-" + pos;
            service.removeBook(id);
            System.out.println("[Success] Book " + id + " removed.");
        } catch (BookNotFoundException e) {
            System.out.println("[Err] " + e.getMessage());
        }
    }

    private Book readBookInput() {
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Shelf Number: ");
        int shelf = Integer.parseInt(scanner.nextLine());
        System.out.print("Position: ");
        int pos = Integer.parseInt(scanner.nextLine());
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Year Published: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        return new Book(category, shelf, pos, year, author, isbn);
    }

    private Book readBookInputAllowEmpty(Book original) {
        System.out.print("Category (" + original.getCategory() + "): ");
        String category = scanner.nextLine();
        if (category.isBlank()) category = original.getCategory();

        System.out.print("Shelf Number (" + original.getShelfNumber() + "): ");
        String shelfInput = scanner.nextLine();
        int shelf = shelfInput.isBlank() ? original.getShelfNumber() : Integer.parseInt(shelfInput);

        System.out.print("Position (" + original.getPosition() + "): ");
        String posInput = scanner.nextLine();
        int pos = posInput.isBlank() ? original.getPosition() : Integer.parseInt(posInput);

        System.out.print("Author: ");
        String author = scanner.nextLine();
        if (author.isBlank()) author = original.getAuthor();

        System.out.print("Year Published: ");
        String yearInput = scanner.nextLine();
        int year = yearInput.isBlank() ? original.getYearPublished() : Integer.parseInt(yearInput);

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        if (isbn.isBlank()) isbn = original.getIsbn();

        return new Book(category, shelf, pos, year, author, isbn);
    }
}
