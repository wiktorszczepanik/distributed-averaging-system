package Logs;

import Constants.SiteType;

public class Log {

    public static SiteType prefix = SiteType.NONE;

    public static void setPrefix(SiteType prefixType) {
        if (prefixType == SiteType.CLIENT) prefix = SiteType.CLIENT;
        else prefix = SiteType.SERVER;
    }

    public static void log(String message) {
        System.out.println("[" + prefix + "] " + message);
        System.out.flush();
    }
}
