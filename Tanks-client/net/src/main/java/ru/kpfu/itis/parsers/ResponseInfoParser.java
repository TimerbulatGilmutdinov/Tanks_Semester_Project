package ru.kpfu.itis.parsers;

import constants.MethodName;
import constants.ProtocolInfo;
import constants.StatusCodes;
import exceptions.IllegalProtocolInfoException;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseInfoParser {
    private Pattern pattern;
    private Matcher matcher;
    private final String ILLEGAL_STATUS_CODE = "Illegal status code!";
    private final String ILLEGAL_VERSION_MSG = "Illegal protocol version!";

    private String buildRegexForStatusCode() {
        StringBuilder regex = new StringBuilder();
        Class<StatusCodes> c = StatusCodes.class;
        Field[] fields = c.getDeclaredFields();
        StatusCodes statusCodes = new StatusCodes();
        regex.append("(");
        for(Field f:fields){
            try {
                regex.append(f.get(statusCodes)).append("|");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        regex.deleteCharAt(regex.length() - 1);
        regex.append(")");
        return regex.toString();
    }

    public String parseVersion(String requestLine) throws IllegalProtocolInfoException {
        String regex = ProtocolInfo.VERSION;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);

        if (!matcher.find()) {
            throw new IllegalProtocolInfoException(ILLEGAL_VERSION_MSG);
        }
        return matcher.group();
    }

    public int parseStatusCode(String line){
        String regex = buildRegexForStatusCode();
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(line);

        if (!matcher.find()) {
            throw new IllegalArgumentException(ILLEGAL_STATUS_CODE);
        }
        return Integer.parseInt(matcher.group());
    }
}
