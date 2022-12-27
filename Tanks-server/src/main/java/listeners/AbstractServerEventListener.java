package listeners;

import constants.MethodName;
import server.Server;

public abstract class AbstractServerEventListener implements ServerEventListener {
    protected boolean init;
    protected Server server;
    protected MethodName methodName;

    public AbstractServerEventListener(MethodName methodName) {
        this.methodName = methodName;
    }

    public static ServerEventListener getEventListener(MethodName methodName) {
        ServerEventListener serverEventListener;
        switch (methodName) {
            case CONNECT:
                serverEventListener =  new ConnectListener();
                break;
            case GET:
                serverEventListener = new GetListener();
                break;
            case POST:
                serverEventListener = new PostListener();
                break;
            default:
                throw new IllegalArgumentException("Illegal protocol method!");
        }
        return serverEventListener;
    }

    @Override
    public void init(Server server) {
        this.init = true;
        this.server = server;
    }

    @Override
    public MethodName getMethodName() {
        return methodName;
    }
}
