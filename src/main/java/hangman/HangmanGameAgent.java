package hangman;

import java.util.Random;

public class HangmanGameAgent {

    private String word;
    private int wordLength;

    public HangmanGameAgent() {
        this.wordLength = new Random().nextInt(10) + 2;
        this.word = getTargetWord();
    }

    private String getTargetWord() {
        DictService.getInstance().importJson(this.wordLength);
        return DictService.getInstance().getRandomWord();
    }

    public String getWord() {
        return word;
    }

    public int getWordLength() {
        return wordLength;
    }

    public boolean evaluateLetterGuess(String letter) {
        return (this.word.contains(letter));
    }

    public boolean evaluateWordGuess(String word) {
        return (this.word.equals(word));
    }
}
