public class Main {
    public static void main(String[] args) {
        MediaService mediaService = new MediaService();
        TerminalUtils terminal = new TerminalUtils();
        boolean running = true;

        while (running) {
            terminal.displayMenu();
            int choice = terminal.getMenuChoice();

            switch (choice) {
                case 1 -> new AddMediaCommand(mediaService, terminal).execute();
                case 2 -> new RemoveMediaCommand(mediaService, terminal).execute();
                case 3 -> new PlayMediaCommand(mediaService, terminal).execute();
                case 4 -> new ListAllMediaCommand(mediaService, terminal).execute();
                case 5 -> {
                    terminal.displayMessage("Goodbye!");
                    running = false;
                }
                default -> terminal.displayMessage("Invalid option. Try again.");
            }
        }
    }
}
