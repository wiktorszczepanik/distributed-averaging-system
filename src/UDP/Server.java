package UDP;

import Constants.SiteType;
import Logs.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Server implements UDP {

    private final int port, number;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private SiteType mode;

    private InetAddress broadcastAddress;

    private long sum = 0;
    private int counter = 0;

    public Server(Entity entity) {
        this.port = entity.getPort();
        this.number = entity.getNumber();
        this.socket = entity.getSocket();
        this.mode = entity.getMode();
    }

    public void util() throws IOException {
        Logger.log("Start UDP server...");
        Logger.sendStatus("DONE");
        boolean state = true;
        while (state) {
            byte[] entryMessageBytes = getMessage();
            byte[] cleanMessageBytes = dropNulls(entryMessageBytes);
//            String message = cleanNumber(entryMessage);
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");
            System.out.println(Arrays.toString(cleanMessageBytes));

//            sum += Integer.parseInt(message);
//            counter++;
            System.out.println("Sum: " + sum + "\nCounter: " + counter);
        }
        broadcastAddress = InetAddress.getByName("255.255.255.255");
    }

    private byte[] getMessage() throws IOException {
        byte[] buffer = new byte[bufferSize];
        packet = new DatagramPacket(buffer, bufferSize);
        Logger.log("Waiting for packets...");
        socket.receive(packet);
        Logger.sendStatus("RECEIVED");
        return packet.getData();
    }

    private byte[] dropNulls(byte[] messageArray) { // here issue.........................
        int length = messageArray.length;
        int lastIndex = 1;
        for (int i = length - 1; i > 0; i--)
            if (messageArray[i] != 0x00) {
                lastIndex = i;
                break;
            }
        return Arrays.copyOfRange(messageArray, 0, lastIndex);
    }

    public SiteType getMode() {
        return mode;
    }

}
//            Logger.log("Packet info:\n" +
//                               "\tAddress: " + packet.getAddress() + "\n" +
//        "\tPort: " + packet.getPort() + "\n" +
//        "\tMessage: " + entryMessage
//            );
