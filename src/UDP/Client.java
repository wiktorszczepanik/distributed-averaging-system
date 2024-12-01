package UDP;

import Constants.LogValue;
import Logs.Logger;

import java.io.IOException;
import java.net.*;

public class Client implements UDP {

    private final int targetPort;
    private final String textNumber;
    private DatagramSocket socket;

    public Client(Entity entity) {
        this.targetPort = entity.getPort();
        this.textNumber = String.valueOf(entity.getNumber());
        this.socket = entity.getSocket();
    }

    public void util() throws IOException {
        socket = new DatagramSocket();
        byte[] buffer = textNumber.getBytes();
        byte[] cleanBuffer = UDP.dropNulls(buffer);
        Logger.log("Sending number: " + textNumber + " ");
        InetAddress host = InetAddress.getByName("localhost");
        DatagramPacket packet = new DatagramPacket(cleanBuffer, cleanBuffer.length, host, targetPort);
        socket.send(packet);
        socket.close();
        Logger.sendStatus(LogValue.DONE);

        Logger.log("Close client...");
        Logger.sendStatus(LogValue.DONE);
    }

}
