package ru.kpfu.itis.parsers;

import constants.HeaderName;
import ru.kpfu.itis.exceptions.IllegalHeaderNameException;
import ru.kpfu.itis.util.RequestLineChecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.RequestLineChecker.ILLEGAL_LINE_START_MESSAGE;
import static util.RequestLineChecker.ILLEGAL_SAMPLE_MESSAGE;

public class HeaderParser {
    private Pattern pattern;
    private Matcher matcher;

    private final RequestLineChecker checker = new RequestLineChecker();

    public String parseHeader(String requestLine) throws IllegalHeaderNameException {
        if (!checker.hasCorrectSample(requestLine)) {
            throw new IllegalArgumentException(ILLEGAL_SAMPLE_MESSAGE);
        }
        if (!checker.startsWithCorrectHeader(requestLine)) {
            throw new IllegalHeaderNameException(ILLEGAL_LINE_START_MESSAGE);
        }
        String regex = buildRegexForHeaderName();
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);
        matcher.find();
        return matcher.group();
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
