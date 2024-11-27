package Sites;

import java.net.DatagramSocket;
import java.net.SocketException;

import Constants.SiteType;
import Exceptions.FlagException;
import Exceptions.PortException;
import Logs.Log;

public class Site {

    private final int port, number;
    private DatagramSocket socket;
    private SiteType mode;

    public Site(String port, String number) throws NumberFormatException {
        this.port = Integer.parseInt(port);
        this.number = Integer.parseInt(port);
    }

    public void checkPortNumber() throws PortException {
        if (!(port >= 0 && port <= 65535))
            throw new PortException("Incorrect port number. Available are <0-65535>");
    }

    public void specifyMode() {
        Log.log("Specify mode...");
        try { // Server or Client
            socket = new DatagramSocket(port);
            mode = SiteType.SERVER;
        } catch (SocketException exception) {
            mode = SiteType.CLIENT;
        }
        Log.setPrefix(mode);
        Log.log("Application mode specified!");
    }

    public SiteType getMode() {
        return mode;
    }

    public static void checkFlagLength(String[] programArguments) throws FlagException {
        if (programArguments.length != 2)
            throw new FlagException(
                    "Incorrect nubmer of flags. The structure is: java DAS <port> <number>"
            );
    }
}
