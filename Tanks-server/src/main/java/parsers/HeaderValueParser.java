package parsers;

import exceptions.ColonCountException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeaderValueParser {
    private final String COLON_COUNT_EXCEPTION_MESSAGE = "Invalid colon (':') char count, should be 1";
    private Pattern pattern;
    private Matcher matcher;

    public String parseValue(String requestLine)  {
        if (hasOneColon(requestLine)) {
            String regex = ":[\\s\\d\\w.]+";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(requestLine);
            matcher.find();
        }
//        else {
//            throw new ColonCountException(COLON_COUNT_EXCEPTION_MESSAGE);
//        }
        String result = matcher.group();
        return result.substring(1);
    }

    private boolean hasOneColon(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (c == ':')
                count++;
        }
        return count == 1;
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
