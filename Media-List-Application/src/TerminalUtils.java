import java.util.List;
import java.util.Scanner;

public class TerminalUtils {
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("\n=== Media List Application ===");
        System.out.println("1. Add Media");
        System.out.println("2. Remove Media");
        System.out.println("3. Play Media");
        System.out.println("4. List All Media");
        System.out.println("5. Quit");
    }

    public int getMenuChoice() {
        return getInt("Choose an option: ");
    }

    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayMediaList(List<Media> mediaList) {
        if (mediaList.isEmpty()) {
            System.out.println("No media in the library.");
        } else {
            int index = 1;
            for (Media media : mediaList) {
                System.out.println(index++ + ". " + media.getDescription());
            }
        }
    }
}
