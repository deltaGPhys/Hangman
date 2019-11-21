package hangman;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

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
    public void evaluateWinByLetters() {
        String word = hangmanGameAgent.getWord();
        HashSet<String> guessedLetters = hangmanGameAgent.getCorrectlyGuessedLetters();

        for (String letter : word.split("")) {
            Assert.assertTrue(hangmanGameAgent.evaluateLetterGuess(letter));
            Assert.assertTrue(hangmanGameAgent.evaluateWinByLetters() ^ (hangmanGameAgent.getCorrectlyGuessedLetters().size() != hangmanGameAgent.getNumUniqueLetters()));
        }
    }

    @Test
    public void evaluateWinByGuess() {
        String word = hangmanGameAgent.getWord();
        Assert.assertFalse(hangmanGameAgent.evaluateWinByGuess("lforp"));
        Assert.assertTrue(hangmanGameAgent.evaluateWinByGuess(word));
    }
}