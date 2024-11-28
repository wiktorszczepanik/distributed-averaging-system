package UDP;

import java.net.DatagramSocket;
import java.net.SocketException;

import Constants.SiteType;
import Exceptions.FlagException;
import Exceptions.PortException;
import Logs.Logger;
import sun.rmi.runtime.Log;

public class Entity {

    private final int port, number;
    private DatagramSocket socket;
    private SiteType mode;

    public Entity(String port, String number) throws NumberFormatException {
        this.port = Integer.parseInt(port);
        this.number = Integer.parseInt(number);
    }

    public SiteType getMode() { return mode; }
    public DatagramSocket getSocket() { return socket; }
    public int getPort() { return port; }
    public int getNumber() { return number; }

    public void checkPortNumber() throws PortException {
        Logger.log("Check port number...");
        if (!(port >= 0 && port <= 65535))
            throw new PortException("Incorrect port number. Available are <0-65535>");
        Logger.sendStatus("OK");
    }

    public void specifyMode() {
        Logger.log("Specify mode...");
        try { // Server or Client
            socket = new DatagramSocket(port);
            mode = SiteType.SERVER;
        } catch (SocketException exception) {
            mode = SiteType.CLIENT;
        }
        Logger.setPrefix(mode);
        Logger.sendStatus("DONE");
    }

    public static void checkFlagLength(String[] programArguments) throws FlagException {
        Logger.log("Check number of flags...");
        if (programArguments.length != 2)
            throw new FlagException(
                    "Incorrect nubmer of flags. The structure is: java DAS <port> <number>"
            );
        Logger.sendStatus("OK");
    }
}
