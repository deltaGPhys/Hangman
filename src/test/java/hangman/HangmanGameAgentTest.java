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
        String word = hangmanGameAgent.getWord();
        String guess = "a";
        Assert.assertEquals(word.contains(guess));
    }

    @Test
    public void evaluateWordGuess() {
    }
}