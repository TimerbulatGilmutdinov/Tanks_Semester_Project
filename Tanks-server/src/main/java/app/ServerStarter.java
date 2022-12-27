package app;

import exceptions.ServerException;
import server.Server;

public class ServerStarter {
    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server(port);
        try {
            server.start();
        } catch (ServerException e) {
            throw new IllegalStateException(e);
        }
    }
}
