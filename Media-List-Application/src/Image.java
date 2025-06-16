public class Image extends Media {
    private String dimensions;
    private String fileFormat;

    public Image(String name, String dimensions, String fileFormat) {
        super(name);
        this.dimensions = dimensions;
        this.fileFormat = fileFormat;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Override
    public void play() {
        System.out.println("Displaying image '" + name + "' using image viewer software");
    }

    @Override
    public String getDescription() {
        return "Image '" + name + "' - Dimensions: " + dimensions + ", Format: " + fileFormat;
    }
}
