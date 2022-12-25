package protocol;

import constants.Entity;
import constants.MethodName;
import lombok.Getter;

import java.util.Map;

@Getter
public class Response {
    private int statusCode;
    private final MethodName methodName;
    private final Entity entity;
    private final Map<String,String> headersMap;

    public Response(MethodName methodName,Entity entity,Map<String,String> headersMap) {
        this.methodName = methodName;
        this.entity = entity;
        this.headersMap = headersMap;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
