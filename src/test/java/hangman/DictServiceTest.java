package hangman;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertEquals(96,dictService.getDictionary().size());
    }

    @Test
    public void importJson2() {
        dictService.importJson(3);
        Assert.assertEquals(972,dictService.getDictionary().size());
    }

    @Test
    public void reduceDict() {
        dictService.importJson(2);
        dictService.reduceDictionary("z");
        Assert.assertEquals(96,dictService.getDictionary().size());
    }

    @Test
    public void reduceDict2() {
        dictService.importJson(2);
        dictService.reduceDictionary("x");
        Assert.assertEquals(91,dictService.getDictionary().size());
    }

    @Test
    public void reduceDict3() {
        dictService.importJson(2);
        dictService.reduceDictionary("a");
        Assert.assertEquals(70,dictService.getDictionary().size());
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
        long initialSize = dictService.getDictionary().size();
        long expected = initialSize - dictService.countLetterInstances("t");
        dictService.reduceDictionary("t");
        long actual = dictService.getDictionary().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void mostCommonLetter() {
        dictService.importJson(6);
        String letter = dictService.mostCommonLetter();
        Assert.assertEquals("e",letter);
    }

    @Test
    public void mostCommonLetter2() {
        dictService.importJson(6);
        dictService.reduceDictionary("e");
        String letter = dictService.mostCommonLetter();
        Assert.assertEquals("a",letter);
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