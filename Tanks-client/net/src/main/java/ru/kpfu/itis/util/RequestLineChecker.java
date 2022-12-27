package ru.kpfu.itis.util;

import constants.HeaderName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestLineChecker {
    public static final String ILLEGAL_SAMPLE_MESSAGE = "Request line has invalid sample";
    public static final String ILLEGAL_LINE_START_MESSAGE = "Request line starts with illegal header name";
    private Matcher matcher;
    private Pattern pattern;

    public boolean hasCorrectSample(String line){
        String regex = buildRegexToCheckSample();
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(line);
        return matcher.find();
    }
    public boolean startsWithCorrectHeader(String line) {
        HeaderName[] headerNames = HeaderName.values();
        boolean result = false;
        for (HeaderName headerName : headerNames) {
            if (line.startsWith(headerName.name())) {
                result = true;
                break;
            }
        }
        return result;
    }
    private String buildRegexToCheckSample(){
        HeaderName[] headerNames = HeaderName.values();
        StringBuilder regex = new StringBuilder();
        regex.append("(");
        for (HeaderName headerName : headerNames) {
            regex.append(headerName).append("|");
        }
        regex.deleteCharAt(regex.length() - 1);
        regex.append(")");
        regex.append(":").append("[0-9.]+[0-9]");
        return regex.toString();
    }
}