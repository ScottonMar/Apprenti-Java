public class Locker {
    private int lockerNumber;
    private String pin;

    public Locker(int lockerNumber) {
        this.lockerNumber = lockerNumber;
        this.pin = null;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }

    public boolean isOccupied() {
        return pin != null;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean verifyPin(String input) {
        return pin != null && pin.equals(input);
    }

    public void clear() {
        pin = null;
    }
}
