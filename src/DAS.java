import Constants.SiteType;
import Exceptions.FlagException;
import Exceptions.ModeException;
import Exceptions.PortException;
import Logs.Logger;
import UDP.Client;
import UDP.UDP;
import UDP.Server;
import UDP.Entity;

import java.rmi.UnknownHostException;

public class DAS {

    public static void main(String[] args) throws Exception {

        Entity net;
        try { // Check INPUT values (port, number)
            Entity.checkFlagLength(args);
            net = new Entity(args[0], args[1]);
            net.checkPortNumber();
        } catch (FlagException | NumberFormatException | PortException exception) {
            System.err.println("Input values exception:\n");
            throw exception;
        }

        UDP entity = null;
        try { // SELECT running mode.
            net.specifyMode();
            Logger.log("Load " + net
                    .getMode()
                    .getTextVersion()
                    .concat(" utilities...")
            );
            switch (net.getMode()) {
                case CLIENT:
                    entity = new Client(net);
                    break;
                case SERVER:
                    entity = new Server(net);
                    break;
                case NONE:
                    throw new ModeException("Possible options are CLIENT or SERVER.");
            }
            Logger.sendStatus("DONE");
        } catch (Exception exception) {
            System.err.println("Select mode exception:\n");
            throw exception;
        }

        try { // Run entity.
            entity.util();
        } catch (UnknownHostException exception) {
            System.err.println("Cannot establish broadcast address:\n");
            throw exception;
        }
    }
}