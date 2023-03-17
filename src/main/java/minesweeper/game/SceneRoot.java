package minesweeper.game;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SceneRoot extends BorderPane {
   
    protected static final MenuRibbon menuRibbon = new MenuRibbon();
    protected InformationRibbon informationRibbon;
    protected Grid mineGrid = new Grid(new Game(), 640);

    SceneRoot(double size) {
        setTop(new HBox(menuRibbon));
        //                                 setCenter(mineGrid);

        // menuRibbon.getOption(MenuRibbon.APPLICATION, MenuRibbon.START).setOnAction(new EventHandler<ActionEvent>() {

        //     @Override
        //     public void handle(ActionEvent event) {
        //         File [] controlDirectory = new File("./control/").listFiles();
        //         for (File controlFile : controlDirectory) {
        //             if (controlFile.getName() == "loadedFile.txt") {
        //                 try {
        //                     mineGrid = new Grid(new Game(controlFile), 640);
        //                     Grid grid = mineGrid;
        //                     Timer timer = grid.game.timer;
        //                     grid.setDisable(false);
        //                     grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
        //                         @Override
        //                         public void handle(MouseEvent me) {
        //                             double posX = me.getX();
        //                             double posY = me.getY();
                        
        //                             int colX = (int)(posX / grid.cellWidth);
        //                             int colY = (int)(posY / grid.cellWidth);
                        
        //                             if (me.getButton() == MouseButton.PRIMARY) {
        //                                 if (grid.cell[colX][colY].content == Cell.EMPTY) {
        //                                         grid.openAdjacent(colX, colY);
        //                                         if (grid.cellsOpened == (grid.size * grid.size - grid.game.scenario.numberOfMines)) {
        //                                             grid.revealAll(Game.FINISHED_WIN);
        //                                             timer.stop();
        //                                         }
                
        //                                 } else if (grid.cell[colX][colY].content == Cell.MINE && grid.cell[colX][colY].status != Cell.OPENED) {
        //                                     grid.cell[colX][colY].setFill(Color.RED);
        //                                     grid.cell[colX][colY].status = Cell.OPENED;
        //                                     grid.revealAll(Game.FINISHED_LOSS);
        //                                     timer.stop();
        //                                 }
                
        //                             } else if (me.getButton() == MouseButton.SECONDARY) { // System.out.println(colX);System.out.println(colY);
        //                                 if ((grid.cell[colX][colY].getFill() != Color.ORANGE) && (grid.cell[colX][colY].status != Cell.OPENED) && (grid.cellsMarked != grid.game.scenario.numberOfMines)) {
        //                                     if (grid.cell[colX][colY].content == Cell.MINE) {
        //                                         if ((grid.markedMines < 4) && grid.cell[colX][colY].hyperMine) {
        //                                             grid.defuseHyperMine();
        //                                             // System.out.println("Hypermine");
        //                                         }
        //                                         grid.markedMines++;
        //                                     }
        //                                     grid.cell[colX][colY].setFill(Color.ORANGE);
        //                                     grid.cellsMarked++;
        //                                 } else if ((grid.cell[colX][colY].getFill() == Color.ORANGE) && (grid.cell[colX][colY].status != Cell.OPENED)) {
        //                                     grid.cell[colX][colY].setFill(Color.GRAY);
        //                                     grid.cellsMarked--;
        //                                 }
        //                             }
        //                             Label markedLabel = (Label) informationRibbon.getChildren().get(2);
            
        //                             if (grid.cellsMarked != grid.game.scenario.numberOfMines) {
        //                                 markedLabel.setStyle("-fx-text-fill: black;");
        //                             } else {
        //                                 markedLabel.setStyle("-fx-text-fill: red;");
        //                             } 
        //                             markedLabel.setText("Marked Cells: " + grid.cellsMarked.toString());
        //                         }
        //                     });
    
        //                     // HBox hBox = (HBox) informationRibbon.getChildren().get(InformationRibbon.HBOX);
        //                     // Label nameLabel = (Label) hBox.getChildren().get(InformationRibbon.TIMELABEL);
        //                     // Label secondsLabel = (Label) hBox.getChildren().get(InformationRibbon.SECONDSLABEL);
        //                     // timer.textProperty().addListener(new ChangeListener<String>() {
        //                     //     @Override
        //                     //     public void changed(ObservableValue <? extends String> ov, String oldProperty, String newProperty) {
        //                     //         if (Integer.parseInt(newProperty) <= 10) {
        //                     //             nameLabel.setStyle("-fx-text-fill: red;");
        //                     //             timer.setStyle("-fx-text-fill: red;");
        //                     //             secondsLabel.setStyle("-fx-text-fill: red;");
        //                     //         }
        //                     //         if (Integer.parseInt(newProperty) == 0) {
        //                     //             grid.revealAll(Game.FINISHED_LOSS);
        //                     //         }
        //                     //     }
        //                     // });        
                            
        //                     // if (grid.game.status == Game.NOT_STARTED) {
        //                     //     if (timer.remainingTime.intValue() == timer.startingTime) {// Not needed anymore
        //                     //         timer.button.getOnAction().handle(event);
        //                     //     }
        //                     //     grid.game.status = Game.ONGOING;
        //                     //     grid.setDisable(false);
        //                     // } else {//if (grid.game.status != Game.ONGOING) {
        //                     //     getMenuButton(GameScene.APPLICATION, GameScene.LOAD).getOnAction().handle(event);
        //                     // } 
                            
        //                 } catch (InvalidScenarioException e) {
        //                     System.out.println("Minesweeper.start(...).new EventHandler() {...}.handle(...).new EventHandler() {...}.handle()");
        //                     e.printStackTrace();
        //                 }
        //                 break;
        //             }
        //         }
        //     }
        // });
    
        // menuRibbon.getOption(MenuRibbon.DETAILS, MenuRibbon.SOLUTION).setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         if (mineGrid != null) {
        //             mineGrid.revealAll(Game.FINISHED_LOSS);
        //         }
        //     }
        // });
        
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
