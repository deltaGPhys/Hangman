package hangman;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Repeatable;

import static org.junit.Assert.*;

public class HangmanMainTest {

    private DictService dictService;
    private HangmanGameAgent hangmanGameAgent;
    private HangmanGamePlayer hangmanGamePlayer;
    private HangmanMain game;

    @Before
    public void setUp() throws Exception {
        dictService = DictService.getInstance();
        game = new HangmanMain();
        hangmanGameAgent = game.getHangmanGameAgent();
        hangmanGamePlayer = game.getHangmanGamePlayer();
    }

    @After
    public void tearDown() throws Exception {
        dictService.clear();
    }

    @Test
    public void constructor() {
        Assert.assertEquals(hangmanGameAgent.getWord().length(),hangmanGamePlayer.getWordLength());
    }

    @Test
    public void checkWordDeductionNull() {
        Assert.assertFalse(game.checkForWordDeduction(null));
    }

    @Test
    public void checkWordDeductionBadGuess() {
        Assert.assertFalse(game.checkForWordDeduction("wosadasdrd"));
    }

    @Test
    public void checkWordDeductionGoodGuess() {
        Assert.assertTrue(game.checkForWordDeduction(hangmanGameAgent.getWord()));
    }

    @Test
    public void playTest() {
        for (int i = 0; i < 100; i++) {
            dictService.clear();
            game = new HangmanMain();
            hangmanGameAgent = game.getHangmanGameAgent();
            hangmanGamePlayer = game.getHangmanGamePlayer();
            Assert.assertTrue(game.play()<=26);
            Assert.assertTrue(hangmanGameAgent.getCorrectlyGuessedLetters().size() <= hangmanGameAgent.getNumUniqueLetters());
        }
    }

}