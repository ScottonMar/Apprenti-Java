import java.util.List;

public class ListAllMediaCommand {
    private MediaService mediaService;
    private TerminalUtils terminal;

    public ListAllMediaCommand(MediaService mediaService, TerminalUtils terminal) {
        this.mediaService = mediaService;
        this.terminal = terminal;
    }

    public void execute() {
        List<Media> mediaList = mediaService.getAllMedia();
        terminal.displayMessage("All Media in Library:");
        terminal.displayMediaList(mediaList);
    }
}
