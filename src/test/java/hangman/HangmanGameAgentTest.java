package hangman;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HangmanGameAgentTest {

    private DictService dictService;
    private HangmanGameAgent hangmanGameAgent;

    @Before
    public void setUp() throws Exception {
        dictService = DictService.getInstance();
        hangmanGameAgent = new HangmanGameAgent();
    }

    @After
    public void tearDown() throws Exception {
        dictService.clear();
    }

    @Test
    public void wordSelectionTest() {
        int expected, actual;
        for (int i = 0; i < 100; i++) {
            expected = hangmanGameAgent.getWordLength();
            actual = hangmanGameAgent.getWord().length();
            Assert.assertEquals(expected,actual);
        }
    }

    @Test
    public void evaluateLetterGuess() {
        for (int i = 0; i < 100; i++) {
            String word = hangmanGameAgent.getWord();
            String guess = "a";
            Assert.assertEquals(word.contains(guess), hangmanGameAgent.evaluateLetterGuess(guess));
        }
    }

    @Test
    public void evaluateWordGuess() {
        String word = hangmanGameAgent.getWord();
        String guess = word.charAt(word.length()-1) + word.substring(1,word.length()-1) + word.charAt(0);
        Assert.assertFalse(hangmanGameAgent.evaluateWordGuess(guess));
        Assert.assertTrue(hangmanGameAgent.evaluateWordGuess(word));
    }
}