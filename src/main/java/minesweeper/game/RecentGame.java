package minesweeper.game;

public class RecentGame {
    
    protected Scenario scenario;
    protected Integer cellsOpened;
    protected String outcome;
    
    protected final static String [] STATUS_TO_OUTCOME = {"Loss", "Win"};

    RecentGame(Grid grid) {
        scenario = grid.game.scenario;
        cellsOpened = grid.cellsOpened;
        if (grid.game.status <= 0) {
            outcome = "Loss";
        } else {
            outcome = "Win";
        }
    }
}
