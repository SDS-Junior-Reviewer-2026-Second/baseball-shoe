package baseball;

public class Game {

    private final String question;

    public Game(String question) {
        this.question = question;
    }

    public void guess(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}