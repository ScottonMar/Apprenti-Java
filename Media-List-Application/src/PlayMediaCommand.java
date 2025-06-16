import java.util.List;

public class PlayMediaCommand {
    private MediaService mediaService;
    private TerminalUtils terminal;

    public PlayMediaCommand(MediaService mediaService, TerminalUtils terminal) {
        this.mediaService = mediaService;
        this.terminal = terminal;
    }

    public void execute() {
        List<Media> mediaList = mediaService.getAllMedia();
        if (mediaList.isEmpty()) {
            terminal.displayMessage("No media to play.");
            return;
        }

        int index = 1;
        for (Media media : mediaList) {
            System.out.println(index++ + ". " + media.getName());
        }

        int choice = terminal.getInt("Choose media: ");
        if (choice >= 1 && choice <= mediaList.size()) {
            mediaList.get(choice - 1).play();
        } else {
            terminal.displayMessage("Invalid selection.");
        }
    }
}
