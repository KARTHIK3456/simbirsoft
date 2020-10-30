package parser.impl;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.log4j.Logger;
import parser.HtmlParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a class that parses html code from web page. It is based on regular expressions
 */
public class RegexHtmlParser implements HtmlParser {
    private static Logger logger = Logger.getLogger(RegexHtmlParser.class);

    /**
     * Finds all text content that is on the html page
     *
     * @param filePath path to the file to parse
     * @return string This is an all text content from html page
     */
    @Override
    public String findTextContent(String filePath) {
        String text = "";
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String sourceLine;

            while ((sourceLine = reader.readLine()) != null)
                text += sourceLine + " \t";

            text = StringEscapeUtils.unescapeHtml4(text);
            text = text.replaceAll("\\u00A0", " ");

            Pattern body = Pattern.compile("<body.*?>.*?</body>");
            Matcher matcher = body.matcher(text);
            while (matcher.find()) text = matcher.group();

            String replacement = " ";
            text = removeTags(text, "<style.*?>.*?</style>", replacement);
            text = removeTags(text, "<script.*?>.*?</script>", replacement);
            text = removeTags(text, "<.*?>", replacement);
            text = removeTags(text, "<!--.*?-->", replacement);
            text = removeTags(text, "\t+", "\n");

            text = text.replaceAll("\\s+", " ").trim();

        } catch (Exception e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
        return text;
    }

    /**
     * @param s           the line where you want to delete tags
     * @param regex       regular expression for the substring to replace
     * @param replacement new substring to replace
     * @return string without unnecessary tags
     */
    private String removeTags(String s, String regex, String replacement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find())
            s = matcher.replaceAll(replacement);

        s = s.replaceAll("\\s+", " ");
        return s;
    }
}
