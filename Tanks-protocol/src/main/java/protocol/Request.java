package protocol;

import constants.MethodName;
import lombok.Getter;

@Getter
public class Request {
    private MethodName methodName;
    private final Message message;

    public Request(Message message){
        this.message = message;
    }
}
