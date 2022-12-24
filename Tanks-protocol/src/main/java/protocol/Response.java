package protocol;

import constants.MethodName;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private MethodName methodName;
    private final Message message;

    public Response(Message message) {
        this.message = message;
    }
}
