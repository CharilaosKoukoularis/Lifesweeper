package minesweeper.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Cell extends Rectangle {

    protected final static int MINE = 1;
    protected final static int EMPTY = 0;

    protected final static int FLAGGED = 1;
    protected final static int INITIAL = 0;
    protected final static int OPENED = -1;
    
    protected int content;
    protected int status = INITIAL;
    protected int neighbors;
    protected boolean hyperMine = false;
    protected Text hint;

    Cell(double width) {
        setWidth(width);
        setHeight(width);
        setFill(Color.GRAY);
        setStroke(Color.BLACK);
    }
}
