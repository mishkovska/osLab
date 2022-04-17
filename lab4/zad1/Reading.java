package lab.lab4.zad1;

import java.io.BufferedReader;
import java.io.IOException;

public class Reading extends Thread {
    BufferedReader bufferedReader;
    boolean isRunning;

    public Reading(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        isRunning = true;
    }

    void killThisThread() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                if (bufferedReader.ready())
                    System.out.println(bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
