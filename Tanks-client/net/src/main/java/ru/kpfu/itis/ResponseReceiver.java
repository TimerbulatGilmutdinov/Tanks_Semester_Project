package ru.kpfu.itis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ResponseReceiver {
    private BufferedReader reader;
    private List<String> list;

    public ResponseReceiver(Socket socket) throws IOException {
        reader =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        list = new ArrayList<>();
    }

    public List<String> receive() throws IOException {
        list.clear();
        String line;
        while (!(line = reader.readLine()).equals("")) {
            list.add(line);
        }
        return list;
    }
}
