import Exceptions.FlagException;
import Exceptions.ModeException;
import Exceptions.PortException;
import Sites.Client;
import Sites.InitUDP;
import Sites.Server;
import Sites.Site;

public class DAS {

    public static void main(String[] args) throws Exception {

        Site site;
        try { // Check INPUT values (port, number)
            Site.checkFlagLength(args);
            site = new Site(args[0], args[1]);
            site.checkPortNumber();
        } catch (FlagException | NumberFormatException | PortException exception) {
            System.err.println("Input exception with ".concat(exception.getMessage()));
            throw exception;
        }

        InitUDP entity = null;
        try { // SELECT running mode.
            switch (site.getMode()) {
                case CLIENT: entity = new Client();
                case SERVER: entity = new Server();
                case NONE: throw new ModeException("Possible options are CLIENT or SERVER.");
            }
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