package parsers;

import constants.HeaderName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeaderValueParser {
    private Pattern pattern;
    private Matcher matcher;
    public String parseValue(String requestLine){
        String regex = ":[\\d\\w.]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(requestLine);
        matcher.find();
        return matcher.group();
    }
}
