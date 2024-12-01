package Constants;

public enum SiteType {

    CLIENT("Client"),
    SERVER("Server"),
    NONE("None");

    private final String textVersion;

    SiteType(String textVersion) {
        this.textVersion = textVersion;
    }

    public String getTextVersion() {
        return textVersion;
    }

}
