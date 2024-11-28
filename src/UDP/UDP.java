package UDP;
import Constants.SiteType;

public interface UDP {

    final int bufferSize = 1500;

    void util() throws Exception;
    SiteType getMode();

}
