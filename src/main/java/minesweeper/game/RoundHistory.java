package minesweeper.game;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RoundHistory extends VBox {

    private Grid grid;
    
    RoundHistory(Integer gameNumber, Integer numberOfMines, Integer openedCells, Integer totalTime, String outcome) {
        super(new Text("Game " + gameNumber.toString() + ": "),
                new Text("Number Of Mines: " + numberOfMines.toString()),
                new Text("Successful Tries: " + openedCells.toString()),
                new Text("Time Limit: " + totalTime.toString()),
                new Text("Outcome: " + outcome));
        setSpacing(5);
    }

    // RoundHistory(Integer gameNumber, Grid grid) {
    //     super(new Text("Game " + gameNumber.toString() + ": "),
    //             new Text("Number Of Mines: " + grid.game.scenario.numberOfMines.toString()),
    //             new Text("Successful Tries: " + grid.cellsOpened.toString()),
    //             new Text("Time Limit: " + grid.game.scenario.timeLimit.toString()),
    //             new Text("Outcome: " + outcome));
    //     setSpacing(5);
    // }

}
