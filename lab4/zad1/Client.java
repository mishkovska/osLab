package lab.lab4.zad1;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {

    static final String serverAddress = "194.149.135.49";
    static final int serverPort = 9753;

    int userIndex;
    int colleagueIndex;

    public Client(int userIndex, int colleagueIndex) {
        this.userIndex = userIndex;
        this.colleagueIndex = colleagueIndex;
    }

    @Override
    public void run() {

        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            bufferedWriter.write("login:" + userIndex + "\n");
            bufferedWriter.flush();

            System.out.println(bufferedReader.readLine());

            bufferedWriter.write("hello:" + userIndex + "\n");
            bufferedWriter.flush();

            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader.readLine());

            Reading r = new Reading(bufferedReader);
            Writing w = new Writing(bufferedWriter, colleagueIndex);

            r.start();
            w.start();

            w.join();
            r.killThisThread();
            r.join();

        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client1 = new Client(201048, 201117);
        client1.start();
    }
}
