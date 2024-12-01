package Constants;

public enum LogValue {

    CORRECT("<CORRECT>"),
    DONE("<DONE>"),
    RECEIVED("<RECEIVED>");

    private final String header;

    LogValue(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

}
