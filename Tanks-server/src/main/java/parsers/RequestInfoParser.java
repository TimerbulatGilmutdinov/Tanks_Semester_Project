package parsers;

import constants.Entity;
import constants.MethodName;
import constants.ProtocolInfo;
import exceptions.IllegalProtocolInfoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestInfoParser {
    private Pattern pattern;
    private Matcher matcher;
    private final String ILLEGAL_METHOD_MSG = "Illegal protocol method name!";
    private final String ILLEGAL_VERSION_MSG = "Illegal protocol version!";
    private final String ILLEGAL_ENTITY_MSG = "Illegal protocol entity!";
    private final String ILLEGAL_NAME_MSG = "Illegal protocol name!";

    public String parseMethod(String requestLine) throws IllegalProtocolInfoException {
        String regex = buildRegexForProtocolMethod();
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);

        if (!matcher.find()) {
            throw new IllegalProtocolInfoException(ILLEGAL_METHOD_MSG);
        }
        return matcher.group();
    }

    private String buildRegexForProtocolMethod() {
        MethodName[] methods = MethodName.values();
        StringBuilder regex = new StringBuilder();
        regex.append("(");
        for (MethodName methodName : methods) {
            regex.append(methodName).append("|");
        }
        regex.deleteCharAt(regex.length() - 1);
        regex.append(")");
        return regex.toString();
    }

    public String parseName(String requestLine) throws IllegalProtocolInfoException {
        String regex = ProtocolInfo.NAME;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);

        if (!matcher.find()) {
            throw new IllegalProtocolInfoException(ILLEGAL_NAME_MSG);
        }
        return matcher.group();
    }

    public String parseVersion(String requestLine) throws IllegalProtocolInfoException {
        String regex = "/" + ProtocolInfo.VERSION;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);

        if (!matcher.find()) {
            throw new IllegalProtocolInfoException(ILLEGAL_VERSION_MSG);
        }
        return matcher.group().substring(1);
    }

    public String parseEntity(String requestLine) throws IllegalProtocolInfoException{
        String regex = buildRegexForProtocolEntity();
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);

        if (!matcher.find()) {
            throw new IllegalProtocolInfoException(ILLEGAL_ENTITY_MSG);
        }
        return matcher.group().substring(1);
    }

    private String buildRegexForProtocolEntity(){
        Entity[] entities = Entity.values();
        StringBuilder regex = new StringBuilder();
        regex.append("(");
        for (Entity entity : entities) {
            regex.append(entity).append("|");
        }
        regex.deleteCharAt(regex.length() - 1);
        regex.append(")");
        return regex.toString();
    }
}
