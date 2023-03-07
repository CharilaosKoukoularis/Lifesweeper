package minesweeper.game;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

public class GameScene extends Scene {
    
    protected final static Integer APPLICATION = 0;
    protected final static Integer DETAILS = 1;

    protected final static Integer CREATE = 0;
    protected final static Integer LOAD = 1;
    protected final static Integer START = 2;
    protected final static Integer EXIT = 3;

    protected final static Integer ROUNDS = 0;
    protected final static Integer SOLUTION = 1;

    protected double dimensions;
    protected TilePane informationRibbon = new TilePane(new Text("Hidden Mines: 45"), new Separator(Orientation.VERTICAL), new Text("Marked Cells: 45"), new Separator(Orientation.VERTICAL), new Text("Time Left: 360 s"));
    protected MenuBar menuRibbon = new MenuBar(new DropdownMenu("Application", "Create", "Load", "Start", "Exit"), new DropdownMenu("Details", "Rounds", "Solution"));
    protected Grid mineGrid;
    protected BorderPane borderPane;

    GameScene(double size) {
        super(new SceneRoot(size), size, size);
    }

    GameScene(Game game, double size) {
        super(new SceneRoot(game, size));//, size, size);
    }

    public void changeGame(Game game) {
        setRoot(new SceneRoot(game, dimensions));
    }
}
