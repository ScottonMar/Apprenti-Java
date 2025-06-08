public class LockerApp {
    public static void main(String[] args) {
        LockerManager manager = new LockerManager(10);  // or any number of lockers
        manager.start();
    }
}
