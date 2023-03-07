package minesweeper.game;

import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SceneRoot extends BorderPane {
   
    protected static MenuBar menuRibbon = new MenuBar(new DropdownMenu("Application", "Create", "Load", "Start", "Exit"), new DropdownMenu("Details", "Rounds", "Solution"));
    
    // protected TilePane informationRibbon = new TilePane(new Label("Hidden Mines: 45"), new Separator(Orientation.VERTICAL), new Text("Marked Cells: 45"), new Separator(Orientation.VERTICAL), new Text("Time Left: 360 s"));
    protected InformationRibbon informationRibbon;
    protected Grid mineGrid;

    SceneRoot(double size) {
        setTop(new HBox(menuRibbon));
    }

    SceneRoot(Game game, double size) {
        mineGrid = new Grid(game, size);
        informationRibbon = new InformationRibbon(mineGrid);
        informationRibbon.setAlignment(Pos.CENTER);
        setTop(new HBox(menuRibbon, informationRibbon));
        setCenter(mineGrid);
    }
}
