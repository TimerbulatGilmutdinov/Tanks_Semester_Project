package protocol;

import constants.Entity;
import constants.MethodName;
import constants.ProtocolInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Request {
    private final MethodName methodName;
    private final Entity entity;
    private final String version;

    public Request(MethodName methodName, Entity entity, String version){
        this.methodName = methodName;
        this.entity = entity;
        this.version = version;
    }
}
