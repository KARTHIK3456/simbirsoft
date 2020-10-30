import counter.WordCounter;
import downloader.WebPageDownloader;
import downloader.impl.HttpWebPageDownloader;
import parser.HtmlParser;
import parser.impl.JsoupHtmlParser;
import solution.Solution;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1)
            System.out.println("Invalid parameter size");
        else {
            HtmlParser parser = new JsoupHtmlParser();
            WordCounter counter = new WordCounter();
            WebPageDownloader downloader = new HttpWebPageDownloader();

            Solution solution = new Solution(downloader, parser, counter);
            solution.downloadHtmlPage(args[0]);
        }
    }
}

