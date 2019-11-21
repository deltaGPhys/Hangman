package hangman;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class HangmanGamePlayerTest {

    private DictService dictService;
    private HangmanGameAgent hangmanGameAgent;
    private HangmanGamePlayer hangmanGamePlayer;

    @Before
    public void setUp() throws Exception {
        dictService = DictService.getInstance();
        hangmanGameAgent = new HangmanGameAgent();
        hangmanGamePlayer = new HangmanGamePlayer(hangmanGameAgent.getWordLength());
    }

    @After
    public void tearDown() throws Exception {
        dictService.clear();
    }

    @Test
    public void reactToGuessTrue() {
        ArrayList<String> dict = new ArrayList<String>(Arrays.asList("aard", "ark", "awl", "bird", "bin", "cane"));
        dictService.setDictionary(dict);

        Assert.assertEquals(6,dictService.size());
        hangmanGamePlayer.reactToGuess(true, "a"); //restricts to just words with a
        Assert.assertEquals(4,dictService.size());
    }

    @Test
    public void reactToGuessFalse() {
        ArrayList<String> dict = new ArrayList<String>(Arrays.asList("aard", "ark", "awl", "bird", "bin", "cane"));
        dictService.setDictionary(dict);

        Assert.assertEquals(6,dictService.size());
        hangmanGamePlayer.reactToGuess(false, "a"); //restricts to just words without a
        Assert.assertEquals(2,dictService.size());
    }

    @Test
    public void reactToGuessWin() {
        ArrayList<String> dict = new ArrayList<String>(Arrays.asList("aard", "ark", "awl", "bird", "bin", "cane"));
        dictService.setDictionary(dict);

        Assert.assertEquals(6,dictService.size());
        hangmanGamePlayer.reactToGuess(true, "c"); //restricts to just words without a
        Assert.assertEquals(1,dictService.size());
        Assert.assertEquals("cane",hangmanGamePlayer.reactToGuess(true, "c"));
    }

    @Test
    public void guessLetter() {
        dictService.importJson(6);
        Assert.assertEquals("e",hangmanGamePlayer.guessLetter());
        Assert.assertEquals("s",hangmanGamePlayer.guessLetter());
    }
}