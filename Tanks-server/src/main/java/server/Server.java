package server;

import exceptions.ServerException;
import listeners.ServerEventListener;
import protocol.Response;
import storage.PlayerData;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    protected boolean started;

    protected List<ServerEventListener> listeners = new ArrayList<>();
    protected ServerSocket server;
    protected List<Connection> connections = new ArrayList<>();
    protected Map<Integer, PlayerData> allPlayersDataMap = new HashMap<>();

    public final int port;
    private final int MAX_PLAYERS_COUNT = 2;

    public Server(int port) {
        this.port = port;
        started = false;
    }

    public void start() throws ServerException {
        try {
            server = new ServerSocket(port);
            started = true;

            for (int i = 1; i < MAX_PLAYERS_COUNT; i++) {
                Socket client = server.accept();
                handleConnection(client);
            }
        } catch (IOException ex) {
            throw new ServerException("Problem with server starting.", ex);
        }
    }

    protected void handleConnection(Socket client) throws ServerException {
        try {
            Connection connection = new Connection(this, client);
            if (!connections.contains(connection)) {
                connections.add(connection);
            }
            Thread thread = new Thread(connection);
            thread.start();
        } catch (IOException ex) {
            throw new ServerException("Problem with handling connection.", ex);
        }
    }

    public void sendResponse(Connection connection, Response response) {
        try {
            connection.responseSender.sendResponse(response);
        } catch (IOException e) {
            removeConnection(connection);
        }
    }

    public Connection getConnectionById(Integer id) {
        for (Connection connection : connections) {
            if (connection.getId() == id) {
                return connection;
            }
        }
        return null;
    }

    public List<Connection> getAllConnections() {
        return connections;
    }

    public Map<Integer, PlayerData> getAllPlayersDataMap() {
        return allPlayersDataMap;
    }

    public void removeConnection(Connection connection) {
        connections.removeIf(conn -> connection.getId() == conn.getId());
    }
}
