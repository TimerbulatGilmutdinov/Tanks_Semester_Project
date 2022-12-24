package protocol;

import constants.MethodName;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private MethodName methodName;
    private Message message;
    private Map<String,String> headersMap = new HashMap<>();
}
