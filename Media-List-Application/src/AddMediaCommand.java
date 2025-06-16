public class AddMediaCommand {
    private MediaService mediaService;
    private TerminalUtils terminal;

    public AddMediaCommand(MediaService mediaService, TerminalUtils terminal) {
        this.mediaService = mediaService;
        this.terminal = terminal;
    }

    public void execute() {
        System.out.println("\nSelect media type:");
        System.out.println("1. Video");
        System.out.println("2. Audio");
        System.out.println("3. Image");
        System.out.println("4. Book");

        int choice = terminal.getInt("Choose type: ");
        String name = terminal.getString("Enter media name: ");

        switch (choice) {
            case 1 -> {
                int duration = terminal.getInt("Enter duration (minutes): ");
                String resolution = terminal.getString("Enter resolution: ");
                mediaService.addMedia(new Video(name, duration, resolution));
                terminal.displayMessage("Video added successfully!");
            }
            case 2 -> {
                int duration = terminal.getInt("Enter duration (minutes): ");
                String artist = terminal.getString("Enter artist name: ");
                mediaService.addMedia(new Audio(name, duration, artist));
                terminal.displayMessage("Audio added successfully!");
            }
            case 3 -> {
                String dimensions = terminal.getString("Enter dimensions (e.g., 1920x1080): ");
                String format = terminal.getString("Enter file format (e.g., JPEG): ");
                mediaService.addMedia(new Image(name, dimensions, format));
                terminal.displayMessage("Image added successfully!");
            }
            case 4 -> {
                String author = terminal.getString("Enter author: ");
                int pages = terminal.getInt("Enter number of pages: ");
                mediaService.addMedia(new Book(name, author, pages));
                terminal.displayMessage("Book added successfully!");
            }
            default -> terminal.displayMessage("Invalid media type selected.");
        }
    }
}
