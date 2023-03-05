package minesweeper.game;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

public class GameScene extends Scene {
    
    protected TilePane informationRibbon = new TilePane(new Text("Hidden Bombs: 45"), new Separator(Orientation.VERTICAL), new Text("Marked Cells: 45"), new Separator(Orientation.VERTICAL), new Text("Time Left: 360 s"));
    protected MenuBar menuRibbon = new MenuBar(new DropdownMenu("Application", "Create", "Load", "Start", "Exit"), new DropdownMenu("Details", "Rounds", "Solution"));
    protected Grid bombGrid;
    protected BorderPane borderPane;

    GameScene(Game game, double size) {
        super(new BorderPane(null, null, null, null, null), size, size);
        bombGrid = new Grid(game, size);
        borderPane = new BorderPane(bombGrid, new HBox(menuRibbon, informationRibbon), null, null, null);
        setRoot(borderPane);
    }
}
