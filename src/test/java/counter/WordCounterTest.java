package counter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class WordCounterTest {
    private WordCounter counter;

    @Before
    public void initTest() {
        counter = new WordCounter();
    }

    @Test
    public void calculateCount() {
        String s = "word wOrd Word world";
        HashMap<String, Integer> res = counter.calculateWordCount(s);

        assertEquals(2, res.size());
        assertEquals(3, res.get("WORD").intValue());
        assertEquals(1, res.get("WORLD").intValue());
        assertNull(res.get("FALSE"));
    }
}