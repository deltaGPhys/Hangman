package hangman;

public class HangmanMain {

    private HangmanGamePlayer hangmanGamePlayer;
    private HangmanGameAgent hangmanGameAgent;
    private DictService dictService;
    private int numGuesses;

    public HangmanMain() {
        dictService = DictService.getInstance();
        hangmanGameAgent = new HangmanGameAgent();
        hangmanGamePlayer = new HangmanGamePlayer(hangmanGameAgent.getWordLength());
    }

    public HangmanGamePlayer getHangmanGamePlayer() { // for testing
        return hangmanGamePlayer;
    }

    public HangmanGameAgent getHangmanGameAgent() { // for testing
        return hangmanGameAgent;
    }

    public static void main(String[] args) {
        HangmanMain game = new HangmanMain();
        int numGuesses = game.play();
        System.out.println(String.format("Player guessed \"%s\" in %d guesses",game.hangmanGameAgent.getWord(),numGuesses));
    }

    public int play() {
        while (!this.hangmanGameAgent.evaluateWinByLetters()) {
            this.numGuesses++;
            String letter = this.hangmanGamePlayer.guessLetter();
            boolean result = this.hangmanGameAgent.evaluateLetterGuess(letter);

            String reaction = this.hangmanGamePlayer.reactToGuess(result, letter);

            if (checkForWordDeduction(reaction)) {
                return this.numGuesses;
            }
        }
        System.out.println("That's all of the letters!");
        return this.numGuesses;
    }

    public boolean checkForWordDeduction(String reaction) {
        if (reaction != null && this.hangmanGameAgent.evaluateWinByGuess(reaction) && !this.hangmanGameAgent.evaluateWinByLetters()) {
            System.out.println("Aha! There's only one word it can be!");
            return true;
        }
        return false;
    }


}
