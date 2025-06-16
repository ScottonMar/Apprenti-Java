import java.util.List;

public class RemoveMediaCommand {
    private MediaService mediaService;
    private TerminalUtils terminal;

    public RemoveMediaCommand(MediaService mediaService, TerminalUtils terminal) {
        this.mediaService = mediaService;
        this.terminal = terminal;
    }

    public void execute() {
        List<Media> mediaList = mediaService.getAllMedia();
        terminal.displayMediaList(mediaList);

        if (mediaList.isEmpty()) return;

        String name = terminal.getString("Enter name of media to remove: ");
        boolean removed = mediaService.removeMedia(name);
        if (removed) {
            terminal.displayMessage("Media removed successfully.");
        } else {
            terminal.displayMessage("Media not found.");
        }
    }
}
