public class Locker {
    private String lockerId;
    private boolean isOccupied;
    private String contents;

    // Constructor to set locker ID and initialize it as empty
    public Locker(String lockerId) {
        this.lockerId = lockerId;
        this.isOccupied = false;
        this.contents = "";
    }

    // Store an item in the locker
    public void storeItem(String item) {
        contents = item;
        isOccupied = true;
    }

    // Remove the item from the locker
    public void removeItem() {
        contents = "";
        isOccupied = false;
    }

    // Return information about this locker
    public String toString() {
        return "Locker ID: " + lockerId + ", Occupied: " + isOccupied + ", Contents: " + contents;
    }

    // Getter for lockerId
    public String getLockerId() {
        return lockerId;
    }
}
