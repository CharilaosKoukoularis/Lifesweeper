package minesweeper.game;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RoundHistory extends VBox {

    // private Grid grid;
    
    RoundHistory(Integer gameNumber, Integer numberOfMines, Integer openedCells, Integer totalTime, String outcome) {
        Text text = new Text("Game " + gameNumber.toString() + ":\n"
            + "Number Of Mines: " + numberOfMines.toString() + "\n" 
            + "Successful Tries: " + openedCells.toString() + "\n"
            + "Time Limit: " + totalTime.toString() + "\n"
            + "Outcome: " + outcome
        );
        if (outcome.equals("Win")) {
            text.setFill(Color.GREEN);
        } else {
            text.setFill(Color.RED);
        }
        getChildren().add(text);
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
