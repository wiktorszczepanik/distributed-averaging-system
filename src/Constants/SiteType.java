package Constants;

public enum SiteType {

    CLIENT('c', "Client"),
    SERVER('s', "Server"),
    NONE('n', "None");

    private final char charVersion;
    private final String textVersion;

    SiteType(char charVersion, String textVersion) {
        this.charVersion = charVersion;
        this.textVersion = textVersion;
    }

    public char getCharVersion() {
        return charVersion;
    }

    public String getTextVersion() {
        return textVersion;
    }

}
