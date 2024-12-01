package UDP;
import Constants.LogValue;
import Logs.Logger;

import java.util.Arrays;

public interface UDP {

    int bufferSize = 1500;
    void util() throws Exception;

    static byte[] dropNulls(byte[] arrayMessage) {
        Logger.log("Clean data...");
        int length = arrayMessage.length;
        int lastIndex = 1;
        for (int i = length - 1; i > 0; i--)
            if (arrayMessage[i] != 0x00) {
                lastIndex = i + 1;
                break;
            }
        Logger.sendStatus(LogValue.DONE);
        return Arrays.copyOfRange(arrayMessage, 0, lastIndex);
    }

}
