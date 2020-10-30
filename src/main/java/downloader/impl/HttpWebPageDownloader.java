package downloader.impl;

import downloader.WebPageDownloader;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * This is a class for loading an html page
 */
public class HttpWebPageDownloader implements WebPageDownloader {
    private static Logger logger = Logger.getLogger(HttpWebPageDownloader.class);

    /**
     * Downloads web page into file and saves this file into result folder
     * @param address page url
     * @return string This method returns an absolute path of the file where
     * the html page was loaded or null if the file was not created
     */
    @Override
    public String downloadPage(String address) {
        String sourceLine;
        try {
            URL url = new URL(address);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            UUID id = UUID.randomUUID();
            String fileName = id.toString().concat(".html");
            File file = new File("result\\" + fileName);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(file), StandardCharsets.UTF_8));

            while ((sourceLine = reader.readLine()) != null)
                writer.write(sourceLine + "\n");

            writer.flush();
            writer.close();
            reader.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
