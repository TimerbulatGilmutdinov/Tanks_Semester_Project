package protocol;

import constants.MethodName;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Request {
    private MethodName methodName;
    private final Message message;
    private Map<String,String> headersMap = new HashMap<>();

    public Request(Message message){
        this.message = message;
    }
}
