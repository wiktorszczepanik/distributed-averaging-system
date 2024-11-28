package Logs;

import Constants.SiteType;

public class Logger {

    public static SiteType prefix = SiteType.NONE;
    public static int lineLength = 50;
    public static int lastLineLength = 0;

    public static void setPrefix(SiteType prefixType) {
        if (prefixType == SiteType.CLIENT) prefix = SiteType.CLIENT;
        else prefix = SiteType.SERVER;
    }

    public static void log(String message) {
        System.out.print("[" + prefix + "] " + message);
        lastLineLength = ("[" + prefix + "] " + message).length();
        System.out.flush();
    }

    public static void sendStatus(String status) {
        numberOfDots(lineLength - lastLineLength);
        System.out.println("<" + status.toUpperCase() + ">");
        System.out.flush();
    }

    private static void numberOfDots(int till) {
        for (int i = 0; i < till; i++)
            System.out.print('.');
    }
}
