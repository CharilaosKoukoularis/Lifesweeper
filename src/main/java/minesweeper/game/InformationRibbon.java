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

    protected Timer timer;

    InformationRibbon(Grid grid) {
        super(new Label("Hidden Mines: " + grid.game.scenario.numberOfMines.toString()),
                new Separator(Orientation.VERTICAL),
                new Label("Marked Cells: " + grid.cellsMarked.toString()),
                new Separator(Orientation.VERTICAL)); 
                
        timer = new Timer(grid.game.scenario.timeLimit);
        HBox hBox = new HBox(new Label("Remaining Time: "), timer.label, new Label(" s"));
        hBox.setAlignment(Pos.CENTER);
        setSpacing(5);
        getChildren().add(hBox);
    }
}
