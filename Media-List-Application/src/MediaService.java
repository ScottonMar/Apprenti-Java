import java.util.ArrayList;
import java.util.List;

public class MediaService {
    private ArrayList<Media> mediaList;

    public MediaService() {
        mediaList = new ArrayList<>();
    }

    public void addMedia(Media media) {
        mediaList.add(media);
    }

    public boolean removeMedia(String name) {
        Media media = findMediaByName(name);
        if (media != null) {
            mediaList.remove(media);
            return true;
        }
        return false;
    }

    public Media findMediaByName(String name) {
        for (Media media : mediaList) {
            if (media.getName().equalsIgnoreCase(name)) {
                return media;
            }
        }
        return null;
    }

    public List<Media> getAllMedia() {
        return new ArrayList<>(mediaList);
    }

    public int getMediaCount() {
        return mediaList.size();
    }

    public boolean isEmpty() {
        return mediaList.isEmpty();
    }
}
