package protocol;

import constants.Entity;
import constants.MethodName;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Response {
    private final String version;
    private int statusCode;
    private final Map<String,String> headersMap;

    public Response(int statusCode, String version, Map<String,String> headersMap) {
        this.statusCode = statusCode;
        this.version = version;
        this.headersMap = headersMap;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
