package parser;

import org.junit.Before;
import org.junit.Test;
import parser.impl.JsoupHtmlParser;
import parser.impl.RegexHtmlParser;

import static org.junit.Assert.*;

public class HtmlParserTest {
    private RegexHtmlParser regexHtmlParser;
    private JsoupHtmlParser jsoupHtmlParser;

    @Before
    public void initTest() {
        regexHtmlParser = new RegexHtmlParser();
        jsoupHtmlParser = new JsoupHtmlParser();
    }

    @Test
    public void parseTextTest() {
        String path = "src/test/resources/test.html";
        String text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diem nonummy nibh euismod " +
                "tincidunt ut lacreet dolore magna aliguam erat volutpat. Ut wisis enim ad minim veniam, " +
                "quis nostrud exerci tution ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.";

        String res1 = regexHtmlParser.findTextContent(path);
        String res2 = jsoupHtmlParser.findTextContent(path);
        assertEquals(text, res1);
        assertEquals(text, res2);
    }
}