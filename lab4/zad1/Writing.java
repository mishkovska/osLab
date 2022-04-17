package lab.lab4.zad1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Writing extends Thread {
    BufferedWriter bufferedWriter;
    int colleagueIndex;

    public Writing(BufferedWriter bufferedWriter, int collegeIndex) {
        this.bufferedWriter = bufferedWriter;
        this.colleagueIndex = collegeIndex;
    }

    @Override
    public void run() {
        try (BufferedReader brIn = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String messageToSend = brIn.readLine();

                if (messageToSend.equals("END"))
                    break;

                try {
                    bufferedWriter.write(colleagueIndex + ":" + messageToSend + "\n");
                    bufferedWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
