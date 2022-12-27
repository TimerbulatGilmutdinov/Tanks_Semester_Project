package protocol;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Response {
    private int statusCode;
    private final String version;
    private final Map<String, Float> headersMap;

    public Response(int statusCode, String version, Map<String,Float> headersMap) {
        this.statusCode = statusCode;
        this.version = version;
        this.headersMap = headersMap;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
