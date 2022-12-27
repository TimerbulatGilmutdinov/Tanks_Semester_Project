package listeners;

import constants.MethodName;
import protocol.Request;
import server.Connection;
import server.Server;

public interface ServerEventListener {
    void init(Server server);

    void handle(Connection connection, Request request);

    MethodName getMethodName();
}
