import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkDown {

    private static final String markdownInput =
            "# Sample Document\n" +
                    "\n" +
                    "Hello!\n" +
                    "\n" +
                    "This is sample markdown for the [Mailchimp](https://www.mailchimp.com) homework assignment.";

    private static final String markdownInput2 =
            "# Header one\n" +
                    "\n" +
                    "Hello there\n" +
                    "\n" +
                    "How are you? " +
                    "What's going on?\n" +
                    "\n" +
                    "## Another Header\n" +
                    "\n" +
                    "This is a paragraph [with an inline link](http://google.com). Neat, eh?\n" +
                    "\n" +
                    "## This is a header [with a link](http://yahoo.com)";

    private static LinkedHashMap<String, String> PATTERN_REPLACEMENT_LIST() {
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("(?m)^######(?!######)(.*)", "<h6>$1</h6>");
        hashMap.put("(?m)^#####(?!#####)(.*)", "<h5>$1</h5>");
        hashMap.put("(?m)^####(?!####)(.*)", "<h4>$1</h4>");
        hashMap.put("(?m)^###(?!###)(.*)", "<h3>$1</h3>");
        hashMap.put("(?m)^##(?!##)(.*)", "<h2>$1</h1>");
        hashMap.put("(?m)^#(?!#)(.*)", "<h1>$1</h1>");
        hashMap.put("(?m)^(?![\\n|<])(.*)", "<p>$1</p>");
        hashMap.put("\\[([^\\]]+)\\]\\(([^)]+)\\)", "<a href=\\\"$2\\\">$1</a>");
        return hashMap;
    }


    public static void main(String[] arg) {
        replaceAll(markdownInput2);
    }

    private static void replaceAll(final String markdown) {
        String temp = markdown;
        for (Map.Entry<String, String> p : PATTERN_REPLACEMENT_LIST().entrySet()) {
            temp = replace(temp, p.getKey(), p.getValue());
        }
        System.out.println(temp);
    }

    private static String replace(final String str, final String pattern, final String replacement) {
        final Pattern p = Pattern.compile(pattern);
        final Matcher matcher = p.matcher(str);

        if (matcher.find()) {
            return matcher.replaceAll(replacement);
        }

        return str;
    }


}
