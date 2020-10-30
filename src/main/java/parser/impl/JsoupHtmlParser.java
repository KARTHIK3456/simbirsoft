package parser.impl;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import parser.HtmlParser;

import java.io.File;

/**
 * This is a class that parses html code from web page. It uses Jsoup libary
 */
public class JsoupHtmlParser implements HtmlParser {
    private static Logger logger = Logger.getLogger(JsoupHtmlParser.class);

    /**
     * Finds all text content that is on the html page
     *
     * @param filePath path to the file to parse
     * @return string This is an all text content from html page
     */
    @Override
    public String findTextContent(String filePath) {
        String text = "";
        try {
            Document doc = Jsoup.parse(new File(filePath), "UTF-8");
            for (Element el : doc.select("body").select("*")) {
                for (TextNode node : el.textNodes()) {
                    text += node.text() + " ";
                }
            }

            text = text.replaceAll("\\s+", " ").trim();
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
        return text;
    }
}
