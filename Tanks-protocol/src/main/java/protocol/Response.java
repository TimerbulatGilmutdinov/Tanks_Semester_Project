package protocol;

import constants.Entity;
import constants.MethodName;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Response {
    private final MethodName methodName;
    private final Entity entity;
    private final String version;
    private int statusCode;
    private final Map<String,String> headersMap;

    public Response(MethodName methodName,Entity entity, String version, int statusCode,Map<String,String> headersMap) {
        this.methodName = methodName;
        this.entity = entity;
        this.version = version;
        this.statusCode = statusCode;
        this.headersMap = headersMap;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
