package ru.kpfu.itis.util;

import constants.Entity;
import constants.MethodName;
import constants.ProtocolInfo;
import protocol.Request;

import java.util.HashMap;

public class RequestBuilder {
    public Request getRequest(MethodName methodName){
        return Request.builder()
                .methodName(methodName)
                .entity(Entity.ALL)
                .version(ProtocolInfo.VERSION)
                .headersMap(new HashMap<>())
                .build();
    }
}
