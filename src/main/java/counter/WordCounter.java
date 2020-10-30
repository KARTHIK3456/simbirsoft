package counter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

/**
 * This is a class that splits a string into words and counts the number
 * of occurrences of each unique word in the string.
 */
public class WordCounter {
    private char[] separators = {' ', ',', '.', '!', '?', '"', ';', ':', '[', ']', '(', ')', '\n',
            '\r', '\t'};

    public WordCounter() {
    }

    public WordCounter(char[] separators) {
        this.separators = separators;
    }

    public char[] getSeparators() {
        return separators;
    }

    public void setSeparators(char[] separators) {
        this.separators = separators;
    }

    /**
     * Divides the string into words by delimiters and counts the
     * number of occurrences of each word in the string
     *
     * @param content This is a string where you need to find the number of occurrences of words in it
     * @return map The key of the map is a word and the value is the number of occurrences of this word in the string
     */
    public HashMap<String, Integer> calculateWordCount(String content) {
        HashMap<String, Integer> result = new LinkedHashMap<>();
        String delim = String.valueOf(separators);
        StringTokenizer st = new StringTokenizer(content, delim);

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            String word = token.toUpperCase();
            if (!result.containsKey(word))
                result.put(word, 1);
            else
                result.put(word, result.get(word) + 1);
        }
        return result;
    }
}
