package dao;

import model.UniqueWord;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UniqueWordDaoTest {
    @Test
    public void testDaoClass() {
        String url = "https://www.simbirsoft.com/";

        UniqueWord word1 = new UniqueWord();
        word1.setUrl(url);
        word1.setWord("word1");
        word1.setCount(1);

        UniqueWord word2 = new UniqueWord();
        word2.setUrl(url);
        word2.setWord("word2");
        word2.setCount(4);


        UniqueWordDao wordDao = new UniqueWordDao();
        wordDao.addWords(new ArrayList<>(Arrays.asList(word1, word2)));
        List<UniqueWord> result = wordDao.findAll();

        assertEquals(2, result.size());
        assertEquals(result.get(0).getId().intValue(), 1);
        assertEquals(result.get(0).getUrl(), url);
        assertEquals(result.get(0).getWord(), "word1");
        assertEquals(result.get(0).getCount(), 1);

        assertEquals(result.get(1).getId().intValue(), 2);
        assertEquals(result.get(1).getUrl(), url);
        assertEquals(result.get(1).getWord(), "word2");
        assertEquals(result.get(1).getCount(), 4);
    }
}