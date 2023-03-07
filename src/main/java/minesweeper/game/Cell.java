package minesweeper.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Cell extends Rectangle {

    protected final static int MINE = 1;
    protected final static int EMPTY = 0;

    protected final static int FLAGGED = 1;
    protected final static int INITIAL = 0;
    protected final static int OPENED = -1;
    
    protected int content;
    protected int status = INITIAL;
    protected int neighbors = 0;
    protected boolean hyperMine = false;
    protected Text hint;

    Cell(double width) {
        setWidth(width);
        setHeight(width);
        setFill(Color.GRAY);
        setStroke(Color.BLACK);
        hint = new Text(getX() + getWidth() / 2, getY() + getHeight() / 2, "");  
        hint.fontProperty().set(Font.font(20.0));    
    }
}
