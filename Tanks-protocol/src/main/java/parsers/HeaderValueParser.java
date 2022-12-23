package parsers;

import exceptions.ColonCountException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HeaderValueParser {
    private final String COLON_COUNT_EXCEPTION_MESSAGE = "Invalid colon (':') char count, should be 1";
    private Pattern pattern;
    private Matcher matcher;

    public String parseValue(String requestLine) throws ColonCountException {
        if (hasOneColon(requestLine)) {
            String regex = ":[\\d\\w.]+";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(requestLine);
            matcher.find();
        } else {
            throw new ColonCountException(COLON_COUNT_EXCEPTION_MESSAGE);
        }
        return matcher.group();
    }

    public boolean hasOneColon(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (c == ':')
                count++;
        }
        return count == 1;
    }
}
