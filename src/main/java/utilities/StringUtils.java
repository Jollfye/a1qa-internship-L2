package utilities;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class StringUtils {
    public static String getRegExGroup(String text, String regex, int group) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(group);
        } else {
            throw new IllegalArgumentException(String.format("RegEx (%1$s) group %2$d not found in text: %3$s", regex, group, text));
        }
    }
}
