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
    }
}