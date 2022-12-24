package protocol;

import constants.MethodName;
import lombok.Getter;

import java.util.Map;

@Getter
public class Response {
    private int statusCode;
    private final MethodName methodName;
    private final Map<String,String> headersMap;

    public Response(MethodName methodName,Map<String,String> headersMap) {
        this.methodName = methodName;
        this.headersMap = headersMap;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
