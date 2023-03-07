package minesweeper.game;

public class InvalidValueException extends InvalidScenarioException {

    protected final static int DIFFICULTY = 0;
    protected final static int MINES = 1;
    protected final static int TIME = 2;
    protected final static int HYPERMINE = 3;

    InvalidValueException(String str) {
        super(str);
    }
}
