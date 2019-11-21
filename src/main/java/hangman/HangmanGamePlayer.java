package hangman;

import java.util.HashSet;
import java.util.TreeMap;

public class HangmanGamePlayer {

    private HashSet<String> guessedLetters;
    private int wordLength;
    private DictService dictService;

    public HangmanGamePlayer(int wordLength) {
        this.dictService = DictService.getInstance();
        this.wordLength = wordLength;
        this.guessedLetters = new HashSet<String>();
    }

    public int getWordLength() { // for testing
        return wordLength;
    }

    public String reactToGuess(boolean guessResult, String letter) {
        if (guessResult) {
            dictService.restrictDictionary(letter);
        } else {
            dictService.reduceDictionary(letter);
        }

        if (dictService.getDictionary().size() == 1) { //narrowed down to one word
            return dictService.getDictionary().get(0);
        }
        return null;

    }

    public String guessLetter() {
        TreeMap<Long,String> letterMap = dictService.getLetterFrequencies();
        int i = 0;
        for (String letter : letterMap.descendingMap().values()) {
            if (!guessedLetters.contains(letter)) {
                guessedLetters.add(letter);
                return letter;
            }
        }
        for (char letter = 'a'; letter <= 'z'; letter++) { // fail safe, in case of map key collisions with low numbers of letters
            if (!guessedLetters.contains(letter)) {
                return String.valueOf(letter);
            }
        }
        return null;
    }

}
