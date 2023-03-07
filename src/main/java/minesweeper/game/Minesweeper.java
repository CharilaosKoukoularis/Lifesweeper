package minesweeper.game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


/**
 * JavaFX App
 */
public class Minesweeper extends Application {

    
    private Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        mainStage.setTitle("Medialab Minesweeper");
        mainStage.setResizable(false);
        mainStage.setScene(new GameScene(new Game(), 640));
        mainStage.show();

        
        GameCreationPopup gameCreationPopup = new GameCreationPopup();
        gameCreationPopup.completionButtonBox.button.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                gameCreationPopup.hide();
            }
        });
        
        getMenuButton(GameScene.APPLICATION, GameScene.CREATE).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameCreationPopup.show(mainStage);
            }
        });

        getMenuButton(GameScene.APPLICATION, GameScene.LOAD).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.setScene(new GameScene(new Game(), 640));//new File("SCENARIO-ID.txt")
                mainStage.show();

                SceneRoot sceneRoot = (SceneRoot) mainStage.getScene().getRoot();
                Grid grid = sceneRoot.mineGrid;
                Timer timer = sceneRoot.informationRibbon.timer;
                grid.setDisable(true);
                grid.setOnMouseClicked(new EventHandler<MouseEvent> (){
                    @Override
                    public void handle(MouseEvent me){
                        double posX = me.getX();
                        double posY = me.getY();
            
                        int colX = (int)(posX / grid.cellWidth);
                        int colY = (int)(posY / grid.cellWidth);
            
                        if (me.getButton() == MouseButton.PRIMARY) {
                            if (grid.cell[colX][colY].content == Cell.EMPTY) {
                                    grid.openAdjacent(colX, colY);
                                    if (grid.cellsOpened == (grid.size * grid.size - grid.game.scenario.numberOfMines)) {
                                        grid.revealAll();
                                        timer.getTimeline().stop();
                                    }
    
                            } else if (grid.cell[colX][colY].content == Cell.MINE) {
                                grid.cell[colX][colY].setFill(Color.RED);
                                grid.cell[colX][colY].status = Cell.OPENED;
                                grid.revealAll();
                                timer.getTimeline().stop();
                            }
    
                        } else if (me.getButton() == MouseButton.SECONDARY) { System.out.println(colX);System.out.println(colY);
                            if ((grid.cell[colX][colY].getFill() == Color.GRAY) && (grid.cell[colX][colY].status != Cell.OPENED) && (grid.cellsMarked != grid.game.scenario.numberOfMines)) {
                                if (grid.cell[colX][colY].content == Cell.MINE) {
                                    if ((grid.markedMines <= 4) && (colX == grid.game.hyperMinePosition / grid.size) && (colY == grid.game.hyperMinePosition % grid.size)) {
                                        grid.diffuseHyperMine();
                                        System.out.println("Hypermine");
                                    }
                                    grid.markedMines++;
                                }
                                grid.cell[colX][colY].setFill(Color.ORANGE);
                                grid.increaseMarked(1);
                            } else if ((grid.cell[colX][colY].getFill() == Color.ORANGE) && (grid.cell[colX][colY].status != Cell.OPENED)) {
                                grid.cell[colX][colY].setFill(Color.GRAY);
                                grid.increaseMarked(-1);
                            }
                        }
                        // if (grid.getMarked() != grid.game.numberOfMines) {
                        //     markedLabel.setStyle("-fx-text-fill: black;");
                        // } else {
                        //     markedLabel.setStyle("-fx-text-fill: red;");
                        // } 
                        // markedLabel.setText("Marked Cells: " + grid.getMarked().toString());
                    }
                });
            }
        });

        getMenuButton(GameScene.APPLICATION, GameScene.START).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneRoot sceneRoot = (SceneRoot) mainStage.getScene().getRoot();
                Grid grid = sceneRoot.mineGrid;
                Timer timer = sceneRoot.informationRibbon.timer;
                
                if (!grid.game.finished) {
                    if (timer.remainingTime.intValue() == timer.startingTime) {
                        timer.button.getOnAction().handle(event);
                    }
                    grid.setDisable(false);
                } else {
                    getMenuButton(GameScene.APPLICATION, GameScene.LOAD).getOnAction().handle(event);
                } 
            }
        });

        getMenuButton(GameScene.APPLICATION, GameScene.EXIT).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });

        getMenuButton(GameScene.DETAILS, GameScene.ROUNDS).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });

        getMenuButton(GameScene.DETAILS, GameScene.SOLUTION).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneRoot sceneRoot = (SceneRoot) mainStage.getScene().getRoot();
                Grid grid = sceneRoot.mineGrid;
                Timer timer = sceneRoot.informationRibbon.timer;

                grid.revealAll();
                if (timer.timeline != null) {
                    timer.getTimeline().stop();
                }
            }
        });


    }

    public static void main(String[] args) {
        launch();
    }    

    public MenuItem getMenuButton(Integer menuName, Integer itemName) {
        DropdownMenu dropdownMenu = (DropdownMenu) SceneRoot.menuRibbon.getMenus().get(menuName);
        MenuItem menuButton = dropdownMenu.getItems().get(itemName);
        return menuButton;
    }
}

