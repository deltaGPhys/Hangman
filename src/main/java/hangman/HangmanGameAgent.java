package hangman;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class HangmanGameAgent {

    private String word;
    private int wordLength;
    private int numUniqueLetters;
    private HashSet<String> correctlyGuessedLetters;

    public HangmanGameAgent() {
        this.wordLength = new Random().nextInt(10) + 2;
        this.word = getTargetWord();
        this.numUniqueLetters = new HashSet<String>(Arrays.asList(this.word.split(""))).size();
        this.correctlyGuessedLetters = new HashSet<>();
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

    public int getNumUniqueLetters() {
        return numUniqueLetters;
    }

    public HashSet<String> getCorrectlyGuessedLetters() {
        return correctlyGuessedLetters;
    }

    public boolean evaluateLetterGuess(String letter) {
        if (this.word.contains(letter)) {
            this.correctlyGuessedLetters.add(letter);
        }

        boolean result = this.word.contains(letter);

        System.out.println(String.format("Guess: %s (%s)",letter, (result) ? "correct" : "incorrect"));
        return result;
    }

    public boolean evaluateWinByLetters() {
        return (this.correctlyGuessedLetters.size() == this.numUniqueLetters);
    }

    public boolean evaluateWinByGuess(String string) {
        return (this.word.equals(string));
    }
}
