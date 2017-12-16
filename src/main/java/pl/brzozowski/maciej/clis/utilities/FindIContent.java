package pl.brzozowski.maciej.clis.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindIContent {


    public String findStringByRegex(String response, double quantity, String unitIn, String unitOut) {

        Pattern pattern = Pattern.compile(quantity + " " + unitIn + " = (.*?) " + unitOut);
        Matcher matcher = pattern.matcher(response);
        return matcher.find() ? matcher.group(1) : "";
    }
}
