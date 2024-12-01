package UDP;

import java.net.DatagramSocket;
import java.net.SocketException;

import Constants.LogValue;
import Constants.SiteType;
import Exceptions.FlagException;
import Exceptions.OnlyNumberException;
import Exceptions.PortException;
import Logs.Logger;

public class Entity {

    private int port;
    private String number;
    private DatagramSocket socket;
    private SiteType mode;

    public static void checkFlagLength(String[] programArguments) throws FlagException {
        Logger.log("Check number of flags...");
        if (programArguments.length != 2)
            throw new FlagException(
                "Incorrect number of flags. The structure is: java DAS <port> <number>"
            );
        Logger.sendStatus(LogValue.CORRECT);
    }

    public void setAndCheckPort(String port) throws PortException {
        Logger.log("Check port number...");
        if (isNotNumber(port))
            throw new PortException("Incorrect number format.");
        int portCheck = Integer.parseInt(port);
        if (!(portCheck >= 0 && portCheck <= 65535))
            throw new PortException("Incorrect port number. Available are <0-65535>");
        this.port = portCheck;
        Logger.sendStatus(LogValue.CORRECT);
    }

    public void setAndCheckNumber(String number) throws OnlyNumberException {
        Logger.log("Check number format...");
        if (isNotNumber(number))
            throw new OnlyNumberException("Incorrect number format.");
        this.number = number;
        Logger.sendStatus(LogValue.CORRECT);
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
        Logger.sendStatus(LogValue.DONE);
    }

    public SiteType getMode() { return mode; }
    public DatagramSocket getSocket() { return socket; }
    public int getPort() { return port; }
    public String getNumber() { return number; }

    public static boolean isNotNumber(String text) {
        return !text.matches("-?\\d+");
    }
}
