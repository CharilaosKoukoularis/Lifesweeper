package minesweeper.game;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

public class SceneRoot extends BorderPane {
   
    protected TilePane informationRibbon = new TilePane(new Text("Hidden Bombs: 45"), new Separator(Orientation.VERTICAL), new Text("Marked Cells: 45"), new Separator(Orientation.VERTICAL), new Text("Time Left: 360 s"));
    protected MenuBar menuRibbon = new MenuBar(new DropdownMenu("Application", "Create", "Load", "Start", "Exit"), new DropdownMenu("Details", "Rounds", "Solution"));
    protected Grid bombGrid;

    SceneRoot(Game game, double size) {
        bombGrid = new Grid(game, size);
        informationRibbon.setAlignment(Pos.CENTER);
        setTop(new HBox(menuRibbon, informationRibbon));
        setCenter(bombGrid);
    }
}
