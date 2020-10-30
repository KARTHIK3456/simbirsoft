package solution;

import counter.WordCounter;
import dao.UniqueWordDao;
import downloader.WebPageDownloader;
import model.UniqueWord;
import parser.HtmlParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is intended for performing the main tasks of the application
 */
public class Solution {
    private WebPageDownloader downloader;
    private HtmlParser parser;
    private WordCounter counter;

    public Solution(WebPageDownloader downloader, HtmlParser parser, WordCounter counter) {
        this.downloader = downloader;
        this.parser = parser;
        this.counter = counter;
    }

    /**
     * Downloads an HTML page, saves the downloaded file to
     * the result folder, counts the number of unique words on
     * the page, outputs statistics on words to the console and
     * saves statistics into database
     *
     * @param url web page address
     */
    public void downloadHtmlPage(String url) {
        String filePath = downloader.downloadPage(url);
        String text = parser.findTextContent(filePath);
        HashMap<String, Integer> result = counter.calculateWordCount(text);
        UniqueWordDao wordDao = new UniqueWordDao();
        ArrayList<UniqueWord> words = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            UniqueWord word = new UniqueWord();
            word.setWord(entry.getKey());
            word.setCount(entry.getValue());
            word.setUrl(url);
            words.add(word);
            System.out.println(word);
        }
        wordDao.addWords(words);
    }

    public WebPageDownloader getDownloader() {
        return downloader;
    }

    public void setDownloader(WebPageDownloader downloader) {
        this.downloader = downloader;
    }

    public HtmlParser getParser() {
        return parser;
    }

    public void setParser(HtmlParser parser) {
        this.parser = parser;
    }

    public WordCounter getCounter() {
        return counter;
    }

    public void setCounter(WordCounter counter) {
        this.counter = counter;
    }
}
