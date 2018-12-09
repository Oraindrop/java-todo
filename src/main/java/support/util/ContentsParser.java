package support.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentsParser {
    public static List<Long> parseReference(String target) {
        String regEx = "@[0-9]+";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(target);
        List<Long> refers = new ArrayList<>();

        while (matcher.find()) {
            refers.add(Long.parseLong(matcher.group().substring(1)));
        }

        return refers;
    }
}
