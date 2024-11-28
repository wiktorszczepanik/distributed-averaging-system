package UDP;

import Constants.SiteType;
import Logs.Logger;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.*;

public class Client implements UDP {

    private final int targetPort;
    private final String textNumber;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private SiteType mode;

    public Client(Entity entity) {
        this.targetPort = entity.getPort();
        this.textNumber = String.valueOf(entity.getNumber());
        this.socket = entity.getSocket();
        this.mode = entity.getMode();
    }

    public void util() throws IOException {
        socket = new DatagramSocket();
        Logger.log("Sending number: " + textNumber + " ");
        byte[] buffer = textNumber.getBytes();
        InetAddress host = InetAddress.getByName("localhost");
        packet = new DatagramPacket(buffer, buffer.length, host, targetPort);
        socket.send(packet);
        Logger.sendStatus("DONE");
        socket.close();
    }

    public SiteType getMode() {
        return mode;
    }

}
