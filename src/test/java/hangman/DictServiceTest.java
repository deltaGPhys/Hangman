package hangman;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class DictServiceTest {

    private DictService dictService;

    @Before
    public void setUp() throws Exception {
        dictService = DictService.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        dictService.clear();
    }

    @Test
    public void importJson() {
        dictService.importJson(2);
        Assert.assertEquals(96,dictService.size());
    }

    @Test
    public void importJson2() {
        dictService.importJson(3);
        Assert.assertEquals(972,dictService.size());
    }

    @Test
    public void reduceDict() {
        dictService.importJson(2);
        dictService.reduceDictionary("z");
        Assert.assertEquals(96,dictService.size());
    }

    @Test
    public void reduceDict2() {
        dictService.importJson(2);
        dictService.reduceDictionary("x");
        Assert.assertEquals(91,dictService.size());
    }

    @Test
    public void reduceDict3() {
        dictService.importJson(2);
        dictService.reduceDictionary("a");
        Assert.assertEquals(70,dictService.size());
    }

    @Test
    public void countInstances() {
        dictService.importJson(2);
        long count = dictService.countLetterInstances("z");
        Assert.assertEquals(0,count);
    }

    @Test
    public void countInstance2() {
        dictService.importJson(2);
        long count = dictService.countLetterInstances("x");
        Assert.assertEquals(5,count);
    }

    @Test
    public void countInstance3() {
        dictService.importJson(2);
        long count = dictService.countLetterInstances("a");
        Assert.assertEquals(26,count);
    }

    @Test
    public void countAndReduce() {
        dictService.importJson(6);
        long initialSize = dictService.size();
        long expected = initialSize - dictService.countLetterInstances("t");
        dictService.reduceDictionary("t");
        long actual = dictService.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void restrictBeforeAndAfter() {
        dictService.importJson(6);
        long initialSize = dictService.size();
        int counter = 0;
        long numWords = dictService.getDictionary().stream().filter( x -> x.contains("a")).count();
        Assert.assertTrue(numWords < initialSize);
        dictService.restrictDictionary("a");
        long newCount = dictService.size();
        Assert.assertEquals(numWords, newCount);
    }

    @Test
    public void treeMapTest() {
        dictService.importJson(6);
        long initialSize = dictService.size();
        TreeMap<Long,String> map = dictService.getLetterFrequencies();
        String firstLetter = map.lastEntry().getValue();
        long firstLetterCount = map.lastKey();
        dictService.reduceDictionary(firstLetter);
        long newSize = dictService.size();
        Assert.assertEquals(initialSize-firstLetterCount,newSize);

        map = dictService.getLetterFrequencies();
        long count = Long.MAX_VALUE;
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue().equals(firstLetter)) {
                count = (long) entry.getKey();
                break;
            }
        }
        Assert.assertEquals(0,count);
    }

    @Test
    public void getRandomWord() {
        dictService.importJson(2);
        String randomWord = dictService.getRandomWord();
        Assert.assertTrue(randomWord.length() == 2);
    }

    @Test
    public void getRandomWord2() {
        dictService.importJson(6);
        dictService.reduceDictionary("e");
        String randomWord = dictService.getRandomWord();
        Assert.assertTrue(randomWord.length() == 6);
        Assert.assertTrue(dictService.getDictionary().contains(randomWord));
        Assert.assertFalse(randomWord.contains("e"));
    }
}