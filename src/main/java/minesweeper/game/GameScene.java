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

    GameScene(double size) {
        super(new SceneRoot(size), size, size);
    }

    GameScene(Game game, double size) {
        super(new SceneRoot(game, size));
    }

    public void changeGame(Game game) {
        SceneRoot sceneRoot = (SceneRoot) getRoot();
        sceneRoot.changeGame(game, dimensions);
        setRoot(sceneRoot);
        // // sceneRoot = new SceneRoot(game, dimensions);
        // sceneRoot.mineGrid = new Grid(game, dimensions);
        // sceneRoot.setCenter(sceneRoot.mineGrid);
        // sceneRoot.informationRibbon = new InformationRibbon(sceneRoot.mineGrid);
        // setRoot(sceneRoot);
        // // setRoot(new SceneRoot(game, dimensions));
    }
}
