package parsers;

import exceptions.ColonCountException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeaderParser {
    private final String COLON_COUNT_EXCEPTION_MESSAGE = "Invalid colon (':') char count, should be 1";
    private Pattern pattern;
    private Matcher matcher;

    public String parseHeader(String requestLine)  {
        if (hasOneColon(requestLine)) {
            String regex = "[\\d\\w]+:";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(requestLine);
            matcher.find();
        }
//        else {
//            throw new ColonCountException(COLON_COUNT_EXCEPTION_MESSAGE);
//        }
        String result = matcher.group();
        return result.substring(0,result.length()-1);
    }

    private boolean hasOneColon(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (c == ':')
                count++;
        }
        return count == 1;
    }
}