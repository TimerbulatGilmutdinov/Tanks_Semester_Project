package ru.kpfu.itis.parsers;

import ru.kpfu.itis.util.RequestLineChecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.RequestLineChecker.ILLEGAL_SAMPLE_MESSAGE;

public class HeaderValueParser {
    private Pattern pattern;
    private Matcher matcher;

    private final RequestLineChecker checker = new RequestLineChecker();

    public String parseValue(String requestLine) {
        if (!checker.hasCorrectSample(requestLine)) {
            throw new IllegalArgumentException(ILLEGAL_SAMPLE_MESSAGE);
        }
        String regex = ":[\\d.]+[0-9]";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);
        matcher.find();

        String result = matcher.group();
        return result.substring(1);
    }

    public boolean isNumericValue(String line) {
        try {
            Double.parseDouble(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
