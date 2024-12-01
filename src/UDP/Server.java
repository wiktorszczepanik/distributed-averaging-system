package UDP;

import Constants.LogValue;
import Exceptions.OnlyNumberException;
import Logs.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server implements UDP {

    private final int port;
    private final String number;
    private final DatagramSocket socket;
    private DatagramPacket packet;

    private BigInteger sum = BigInteger.ZERO;
    private int counter = 0;

    public Server(Entity entity) {
        this.port = entity.getPort();
        this.number = entity.getNumber();
        this.socket = entity.getSocket();
    }

    public void util() throws IOException {
        Logger.log("Start UDP server...");
        Logger.sendStatus(LogValue.DONE);
        addInitValues(); // Number and counter
        boolean state = true;
        while (state) {
            byte[] fullArray = getByteMessage();
            byte[] arrayMessage = UDP.dropNulls(fullArray);
            String textMessage = transformToTextMessage(arrayMessage);
            if (Entity.isNotNumber(textMessage))
                throw new OnlyNumberException("Incorrect number format.");
            BigInteger messageToNumber = new BigInteger(textMessage);
            if (textMessage.equals("0")) {
                BigDecimal avg = calcAVG();
                sendAVGMessage(avg);
                byte[] broadcast = (avg.toString()).getBytes();
                sendBroadcastMessage(broadcast);
            } else if (textMessage.equals("-1")) {
                sendMessageToConsole(messageToNumber);
                byte[] broadcast = {(byte) -1};
                sendBroadcastMessage(broadcast);
                socket.close();
                state = false;
            } else {
                sendMessageToConsole(messageToNumber);
                sum = sum.add(messageToNumber);
                counter++;
            }
        }
        Logger.log("Close service...");
        Logger.sendStatus(LogValue.DONE);
    }

    private void addInitValues() {
        Logger.log("Add init values...");
        sum = new BigInteger(number);
        counter++;
        Logger.sendStatus(LogValue.DONE);
    }

    private void sendMessageToConsole(BigInteger number) {
        Logger.log("Received number message: ");
        String appendToMessage = number + " ";
        System.out.print(appendToMessage);
        Logger.updateLastLine(appendToMessage.length());
        Logger.sendStatus(LogValue.CORRECT);
    }

    private BigDecimal calcAVG() {
        BigDecimal tempSum = new BigDecimal(sum),
                tempCounter = new BigDecimal(counter);
        return tempSum.divide(tempCounter, 3, RoundingMode.DOWN);
    }

    private void sendAVGMessage(BigDecimal avg) {
        Logger.log("AVG message: ");
        String appendToMessage = avg + " ";
        System.out.print(appendToMessage);
        Logger.updateLastLine(appendToMessage.length());
        Logger.sendStatus(LogValue.CORRECT);
    }

    private void sendBroadcastMessage(byte[] message) throws IOException {
        Logger.log("Send broadcast message (10.10.10.255) ...");
        InetAddress broadcastAddress = InetAddress.getByName("10.10.10.255");
        packet = new DatagramPacket(message, message.length, broadcastAddress, port);
        socket.send(packet);
        Logger.sendStatus(LogValue.DONE);
    }

    private byte[] getByteMessage() throws IOException {
        byte[] buffer = new byte[bufferSize];
        packet = new DatagramPacket(buffer, bufferSize);
        Logger.log("Waiting for packets...");
        socket.receive(packet);
        Logger.sendStatus(LogValue.RECEIVED);
        return packet.getData();
    }

    private String transformToTextMessage(byte[] messageArray) {
        StringBuilder text = new StringBuilder();
        for (byte b : messageArray)
            text.append((char) b);
        return text.toString();
    }

}
