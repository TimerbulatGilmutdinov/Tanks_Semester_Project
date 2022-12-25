package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket client;
    public final int port;
    private final int MAX_PLAYERS_COUNT = 2;

    public Server(int port) {
        this.port = port;
    }

    private void start() {
        try {
            System.out.println("Initializing server");
            serverSocket = new ServerSocket(port);
            for (int i = 1; i < MAX_PLAYERS_COUNT; i++) {
                client = serverSocket.accept();
                System.out.println(client.getInetAddress().getHostName() + "connected");
                new ServerThread(i).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
