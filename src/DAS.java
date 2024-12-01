import Constants.LogValue;
import Logs.Logger;
import UDP.Client;
import UDP.UDP;
import UDP.Server;
import UDP.Entity;

import Exceptions.FlagException;
import Exceptions.ModeException;
import Exceptions.OnlyNumberException;
import Exceptions.PortException;

import java.rmi.UnknownHostException;

public class DAS {

    public static void main(String[] args) throws Exception {

        Entity net;
        UDP entity = null;

        try { // Check input values (port, number)
            Entity.checkFlagLength(args);
            net = new Entity();
            net.setAndCheckPort(args[0]);
            net.setAndCheckNumber(args[1]);

            // Select running mode
            net.specifyMode();
            Logger.log("Load " + net.getMode()
                .getTextVersion() + (" utilities..."));
            switch (net.getMode()) {
                case CLIENT: entity = new Client(net); break;
                case SERVER: entity = new Server(net); break;
                case NONE:
                    throw new ModeException("Possible options are client or server");
            }
            Logger.sendStatus(LogValue.DONE);
            entity.util(); // Run entity (client or server)

        } catch (FlagException | OnlyNumberException | PortException exception) {
            System.err.println(" Input values exception:\n" + exception.getMessage());
        } catch (UnknownHostException exception) {
            System.err.println(" Cannot establish address:\n" + exception.getMessage());
        }
    }
}