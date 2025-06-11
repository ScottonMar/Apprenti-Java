import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LockerManager lockerManager = new LockerManager();

        boolean exit = false;

        System.out.println("Welcome to the Storage Locker Manager!");

        while (!exit) {
            // Display menu
            System.out.println("\n1. Add Locker");
            System.out.println("2. Remove Locker");
            System.out.println("3. Store Item");
            System.out.println("4. Retrieve Item");
            System.out.println("5. Display All Lockers");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter locker ID: ");
                    String newId = scanner.nextLine();
                    lockerManager.addLocker(newId);
                    break;

                case "2":
                    System.out.print("Enter locker ID to remove: ");
                    String removeId = scanner.nextLine();
                    lockerManager.removeLocker(removeId);
                    break;

                case "3":
                    System.out.print("Enter locker ID: ");
                    String storeId = scanner.nextLine();
                    Locker storeLocker = lockerManager.getLocker(storeId);
                    if (storeLocker != null) {
                        System.out.print("Enter item to store: ");
                        String item = scanner.nextLine();
                        storeLocker.storeItem(item);
                        System.out.println("Item stored in locker " + storeId + ".");
                    } else {
                        System.out.println("Locker not found.");
                    }
                    break;

                case "4":
                    System.out.print("Enter locker ID: ");
                    String retrieveId = scanner.nextLine();
                    Locker retrieveLocker = lockerManager.getLocker(retrieveId);
                    if (retrieveLocker != null) {
                        retrieveLocker.removeItem();
                        System.out.println("Item retrieved from locker " + retrieveId + ".");
                    } else {
                        System.out.println("Locker not found.");
                    }
                    break;

                case "5":
                    lockerManager.displayAllLockers();
                    break;

                case "6":
                    exit = true;
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
