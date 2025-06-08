import java.util.Scanner;

public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);

    public static int promptForLockerNumber(int max) {
        System.out.print("Enter locker number (0 to " + (max - 1) + "): ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return -1;
        }
    }

    public static String promptForPin() {
        System.out.print("Enter PIN: ");
        return scanner.nextLine();
    }

    public static boolean confirm(String message) {
        System.out.print(message + " (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }
}
