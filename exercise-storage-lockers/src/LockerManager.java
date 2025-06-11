import java.util.ArrayList;

public class LockerManager {
    private ArrayList<Locker> lockers;

    // Constructor: create an empty list of Lockers
    public LockerManager() {
        lockers = new ArrayList<>();
    }

    // Add a new Locker
    public void addLocker(String lockerId) {
        lockers.add(new Locker(lockerId));
        System.out.println("Locker " + lockerId + " added.");
    }

    // Remove locker by ID
    public void removeLocker(String lockerId) {
        Locker locker = getLocker(lockerId);
        if (locker != null) {
            lockers.remove(locker);
            System.out.println("Locker " + lockerId + " removed.");
        } else {
            System.out.println("Locker not found.");
        }
    }

    // Get locker by ID
    public Locker getLocker(String lockerId) {
        for (Locker locker : lockers) {
            if (locker.getLockerId().equals(lockerId)) {
                return locker;
            }
        }
        return null;
    }

    // Display all lockers
    public void displayAllLockers() {
        if (lockers.isEmpty()) {
            System.out.println("No lockers found.");
        } else {
            for (Locker locker : lockers) {
                System.out.println(locker.toString());
            }
        }
    }
}
