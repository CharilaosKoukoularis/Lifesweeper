package minesweeper.game;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;

public class InformationRibbon extends HBox {

    protected final static int HBOX = 4;

    protected final static int TIMELABEL = 0;
    protected final static int SECONDSLABEL = 2;

    protected Grid grid;

    InformationRibbon(Grid newGrid) {
        grid = newGrid;
        Label markedLabel = new Label("Marked Cells: " + getMarkedCells().toString());
        HBox hBox = new HBox(new Label("Remaining Time: "), getTimer(), new Label(" s"));
        hBox.setAlignment(Pos.CENTER);
        setSpacing(5);
        setAlignment(Pos.CENTER);
        getChildren().addAll(
            new Label("Hidden Mines: " + getNumberOfMines().toString()),
            new Separator(Orientation.VERTICAL),
            markedLabel,
            new Separator(Orientation.VERTICAL),
            hBox
        );
    }

    public Timer getTimer() {
        return grid.game.timer;
    }

    public Integer getNumberOfMines() {
        return grid.game.scenario.numberOfMines;
    }

    public Integer getMarkedCells() {
        return grid.cellsMarked;
    }
}
