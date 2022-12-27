package ru.kpfu.itis;

import constants.MethodName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection {
    private final Socket socket;

    public Connection() throws IOException {
        socket = new Socket("localhost", 8080);
    }

    public Socket searchMatch() throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader (new InputStreamReader(socket.getInputStream()));
        String line;
        while (!(line = reader.readLine()).equals(MethodName.CONNECT.toString())) {
            Thread.sleep(10);
        }
        return socket;
    }
}
