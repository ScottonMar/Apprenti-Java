import java.util.Scanner;

public class LockerManager {
    private Locker[] lockers;
    private Scanner scanner;

    public LockerManager(int totalLockers) {
        lockers = new Locker[totalLockers];
        for (int i = 0; i < totalLockers; i++) {
            lockers[i] = new Locker(i);
        }
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nWhat would you like to do next?");
            if (!isFull()) System.out.println("1. Rent a Locker");
            System.out.println("2. Access a Locker");
            System.out.println("3. Release a Locker");
            System.out.println("Any other key to exit.");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (!isFull()) rentLocker();
                    else System.out.println("No lockers available.");
                    break;
                case "2":
                    accessLocker();
                    break;
                case "3":
                    releaseLocker();
                    break;
                default:
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }

    public void rentLocker() {
        for (Locker locker : lockers) {
            if (!locker.isOccupied()) {
                String pin = PinGenerator.generatePin();
                locker.setPin(pin);
                System.out.println("Locker #" + locker.getLockerNumber() + " rented. PIN: " + pin);
                return;
            }
        }
        System.out.println("No lockers available.");
    }

    public void accessLocker() {
        int lockerNum = InputHelper.promptForLockerNumber(lockers.length);
        if (!isValidLocker(lockerNum)) return;

        String pin = InputHelper.promptForPin();
        if (lockers[lockerNum].verifyPin(pin)) {
            System.out.println("Access granted to locker #" + lockerNum);
        } else {
            System.out.println("Incorrect PIN.");
        }
    }

    public void releaseLocker() {
        int lockerNum = InputHelper.promptForLockerNumber(lockers.length);
        if (!isValidLocker(lockerNum)) return;

        String pin = InputHelper.promptForPin();
        if (!lockers[lockerNum].verifyPin(pin)) {
            System.out.println("Incorrect PIN.");
            return;
        }

        if (InputHelper.confirm("Are you sure you want to release locker #" + lockerNum + "?")) {
            lockers[lockerNum].clear();
            System.out.println("Locker released.");
        } else {
            System.out.println("Canceled.");
        }
    }

    private boolean isValidLocker(int number) {
        return number >= 0 && number < lockers.length && lockers[number].isOccupied();
    }

    public boolean isFull() {
        for (Locker locker : lockers) {
            if (!locker.isOccupied()) return false;
        }
        return true;
    }
}
