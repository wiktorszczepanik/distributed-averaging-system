import Exceptions.FlagException;
import Exceptions.ModeException;
import Exceptions.PortException;
import Logs.Logger;
import Sites.Client;
import Sites.Server;
import Sites.Entity;

public class DAS {

    public static void main(String[] args) throws Exception {

        Entity net;
        try { // Check INPUT values (port, number)
            Entity.checkFlagLength(args);
            net = new Entity(args[0], args[1]);
            net.checkPortNumber();
        } catch (FlagException | NumberFormatException | PortException exception) {
            System.err.println("Input exception with ".concat(exception.getMessage()));
            throw exception;
        }

        Entity entity = null;
        try { // SELECT running mode.
            net.specifyMode();
            switch (net.getMode()) {
                case CLIENT: entity = new Client(net);
                case SERVER: entity = new Server(net);
                case NONE: throw new ModeException("Possible options are CLIENT or SERVER.");
            }
            Logger.log(entity.getMode()
                .getTextVersion()
                .concat(" utilities loaded.")
            );
        } catch (Exception exception) {
            System.err.println("Select exception with ".concat(exception.getMessage()));
            throw exception;
        }


        try { // Run entity.
            entity.util();
        } catch (Exception exception) {
            throw exception;
        }
    }
}