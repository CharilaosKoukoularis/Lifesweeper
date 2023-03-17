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
    protected Integer neighbors = 0;
    protected boolean hyperMine = false;
    protected Text hint;

    Cell(double width) {
        setWidth(width);
        setHeight(width);
        setFill(Color.GRAY);
        setStroke(Color.BLACK);
    }

    public int open() {
        if (status == OPENED) {
            return OPENED;
        } else {
            if (content == MINE) {
                setFill(Color.RED);
            } else {
                setFill(null);
            }
            status = OPENED;
            return content;
        }
    }

    public int flag() {
        if (status == OPENED) {
            return OPENED;
        } else {
            if (status == INITIAL) {
                setFill(Color.ORANGE);
                status = FLAGGED;
            } else if (status == FLAGGED) {
                setFill(Color.GRAY);
                status = INITIAL;
            }
            return content;
        }
    }
}
