package parsers;

import constants.MethodName;
import constants.ProtocolInfo;
import exceptions.IllegalProtocolMethodException;
import exceptions.IllegalProtocolNameException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestInfoParser {
    private Pattern pattern;
    private Matcher matcher;

    public String parseMethod(String requestLine) throws IllegalProtocolMethodException {
        String regex = buildRegexForProtocolMethod();
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);

        if (!matcher.find()) {
            throw new IllegalProtocolMethodException();
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

    public String parseName(String requestLine) throws IllegalProtocolNameException {
        String regex = ProtocolInfo.NAME;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);

        if (!matcher.find()) {
            throw new IllegalProtocolNameException();
        }
        return matcher.group();
    }

    public String parseVersion(String requestLine) throws IllegalProtocolNameException {
        String regex = "/" + ProtocolInfo.VERSION;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);

        if (!matcher.find()) {
            throw new IllegalProtocolNameException();
        }
        return matcher.group().substring(1);
    }
}
