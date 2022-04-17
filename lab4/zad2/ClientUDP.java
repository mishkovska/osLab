package lab.lab4.zad2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class ClientUDP extends Thread {
    static final String serverAddress = "194.149.135.49";
    static final int serverPort = 9753;

    private String userIndex;
    private byte[] buf;

    public ClientUDP(String userIndex) {
        this.userIndex = userIndex;
    }

    @Override
    public void run() {
        buf = userIndex.getBytes();
        try (DatagramSocket socket = new DatagramSocket()) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(serverAddress), serverPort);
            socket.send(packet);
            packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(serverAddress), serverPort);
            socket.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        ClientUDP client1 = new ClientUDP("201048");
        client1.start();
    }
}
