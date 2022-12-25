package parsers;

import constants.HeaderName;
import exceptions.IllegalHeaderNameException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeaderParser {
    private final String ILLEGAL_SAMPLE_MESSAGE = "Request line has invalid sample";
    private final String ILLEGAL_LINE_START_MESSAGE = "Request line starts with illegal header name";
    private final String COLON_COUNT_EXCEPTION_MESSAGE = "Invalid colon (':') char count, should be 1";
    private Pattern pattern;
    private Matcher matcher;

    public String parseHeader(String requestLine) throws IllegalHeaderNameException {
        if (!hasCorrectSample(requestLine)) {
            throw new IllegalArgumentException(ILLEGAL_SAMPLE_MESSAGE);
        }
        if (!startsWithCorrectHeader(requestLine)) {
            throw new IllegalHeaderNameException(ILLEGAL_LINE_START_MESSAGE);
        }
        String regex = buildRegexForHeaderName();
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);
        matcher.find();
        return matcher.group();
    }

    private boolean startsWithCorrectHeader(String line) {
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

    private boolean hasCorrectSample(String line) {
        String regex = "[\\w]+:[\\d]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(line);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    private String buildRegexForHeaderName() {
        HeaderName[] headerNames = HeaderName.values();
        StringBuilder regex = new StringBuilder();
        regex.append("(");
        for (HeaderName headerName : headerNames) {
            regex.append(headerName).append("|");
        }
        regex.deleteCharAt(regex.length() - 1);
        regex.append(")");
        return regex.toString();
    }
}
