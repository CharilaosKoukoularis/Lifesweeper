package minesweeper.game;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SceneRoot extends BorderPane {
   
    protected static final MenuRibbon menuRibbon = new MenuRibbon();
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

    public void changeGame(Game game, double size) {
        mineGrid = new Grid(game, size);
        informationRibbon = new InformationRibbon(mineGrid);
        informationRibbon.setAlignment(Pos.CENTER);
        setTop(new HBox(menuRibbon, informationRibbon));
        setCenter(mineGrid);
    }
}
