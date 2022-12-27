package ru.kpfu.itis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private Socket socket;
    private static final String CONNECT = "CONNECT";

    public Client() throws IOException {
        socket = new Socket("localhost", 8080);
    }

    public Socket searchMatch() throws IOException, InterruptedException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        String line;
        while (!(line = reader.readLine()).equals(CONNECT)) {
            Thread.sleep(10);
        }
        return socket;
    }
}
