package minesweeper.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Cell extends Rectangle {

    protected final static int HIDDENBOMB = 1;
    protected final static int HIDDENEMPTY = 0;
    protected final static int OPENED = -1;
    
    private int status;
    private Text hint;

    Cell(double width) {
        status = HIDDENEMPTY;
        this.setWidth(width);
        this.setHeight(width);
        this.setFill(Color.GRAY);
        this.setStroke(Color.BLACK);
    }

    public void setStatus(int newStatus) {
        status = newStatus;
    }

    public void setHint(Text newHint) {
        hint = newHint;
    }

    public int getStatus() {
        return status;
    }

    public Text getHint() {
        return hint;
    }

    public boolean isBomb() {
        if (status < 0) return true;
        return false;
    }
    
}
