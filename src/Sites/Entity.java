package Sites;

import java.net.DatagramSocket;
import java.net.SocketException;

import Constants.SiteType;
import Exceptions.FlagException;
import Exceptions.PortException;
import Logs.Logger;

public class Entity {

    private final int port, number;
    private DatagramSocket socket;
    private SiteType mode;

    public Entity(String port, String number) throws NumberFormatException {
        this.port = Integer.parseInt(port);
        this.number = Integer.parseInt(port);
    }

    public Entity(Entity entity) {
        this.port = entity.getPort();
        this.number = entity.getNumber();
        this.socket = entity.getSocket();
        this.mode = entity.getMode();
    }

    public SiteType getMode() { return mode; }
    public DatagramSocket getSocket() { return socket; }
    public int getPort() { return port; }
    public int getNumber() { return number; }

    public void checkPortNumber() throws PortException {
        Logger.log("Check port number...");
        if (!(port >= 0 && port <= 65535))
            throw new PortException("Incorrect port number. Available are <0-65535>");
        Logger.log("Correct port number.");
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
        Logger.log("Application mode specified!");
    }

    public static void checkFlagLength(String[] programArguments) throws FlagException {
        if (programArguments.length != 2)
            throw new FlagException(
                    "Incorrect nubmer of flags. The structure is: java DAS <port> <number>"
            );
        Logger.log("Correct number of flags.");
    }
}
