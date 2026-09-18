package baseball;

public class Game {

    private final String question;

    public Game(String question) {
        this.question = question;
    }

    public GuessResult guess(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (input.length() != 3) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch < '0' || ch > '9') {
                throw new IllegalArgumentException();
            }
        }

        if (input.charAt(0) == input.charAt(1)
                || input.charAt(0) == input.charAt(2)
                || input.charAt(1) == input.charAt(2)) {
            throw new IllegalArgumentException();
        }

        if (isSolved(input)) {
            return createSolvedResult();
        }

        return createUnsolvedResult(input);
    }

    private boolean isSolved(String input) {
        return input.equals(question);
    }

    private GuessResult createSolvedResult() {
        return new GuessResult(true, 3, 0);
    }

    private GuessResult createUnsolvedResult(String input) {
        int strikes = 0;
        int balls = 0;

        for (int i = 0; i < question.length(); i++) {
            if (question.charAt(i) == input.charAt(i)) {
                strikes++;
            } else if (question.indexOf(input.charAt(i)) >= 0) {
                balls++;
            }
        }

        return new GuessResult(false, strikes, balls);
    }
}