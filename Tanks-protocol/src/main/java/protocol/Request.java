package protocol;

import constants.MethodName;
import lombok.Getter;

import java.util.Map;

@Getter
public class Request {
    private final MethodName methodName;
    private final Map<String,String> headersMap;

    public Request(MethodName methodName, Map<String,String> headersMap){
        this.methodName = methodName;
        this.headersMap = headersMap;
    }
}
