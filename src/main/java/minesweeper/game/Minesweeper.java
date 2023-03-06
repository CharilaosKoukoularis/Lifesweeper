package minesweeper.game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
        mainStage.setScene(new GameScene(640));
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
                mainStage.setScene(new GameScene(new Game(), 640));
                mainStage.show();

                SceneRoot sceneRoot = (SceneRoot) mainStage.getScene().getRoot();
                Grid grid = sceneRoot.bombGrid;
                Timer timer = sceneRoot.informationRibbon.timer;
                grid.setDisable(true);
                grid.setOnMouseClicked(new EventHandler<MouseEvent> (){
                    @Override
                    public void handle(MouseEvent me){
                        double posX = me.getX();
                        double posY = me.getY();
            
                        int colX = (int)(posX / grid.getCellWidth());
                        int colY = (int)(posY / grid.getCellWidth());
            
                        if (me.getButton() == MouseButton.PRIMARY) {
                            if (grid.gridCell[colX][colY].getStatus() == Cell.HIDDENEMPTY) {
                                    grid.openAdjacent(colX, colY);
                                    if (grid.getOpened() == (grid.n * grid.n - grid.game.getNumberOfBombs())) {
                                        grid.revealAll();
                                        timer.getTimeline().stop();
                                    }
    
                            } else if (grid.gridCell[colX][colY].getStatus() == Cell.HIDDENBOMB){
                                grid.gridCell[colX][colY].setFill(Color.RED);
                                grid.gridCell[colX][colY].setStatus(Cell.OPENED);
                                grid.revealAll();
                                timer.getTimeline().stop();
                            }
    
                        } else if (me.getButton() == MouseButton.SECONDARY) {
                            if ((grid.gridCell[colX][colY].getFill() == Color.GRAY) && (grid.gridCell[colX][colY].getStatus() != Cell.OPENED) && (grid.getMarked() != grid.game.numberOfBombs)) {
                                grid.gridCell[colX][colY].setFill(Color.ORANGE);
                                grid.increaseMarked(1);
                            } else if ((grid.gridCell[colX][colY].getFill() == Color.ORANGE) && (grid.gridCell[colX][colY].getStatus() != Cell.OPENED)) {
                                grid.gridCell[colX][colY].setFill(Color.GRAY);
                                grid.increaseMarked(-1);
                            }
                        }
                        // if (grid.getMarked() != grid.game.numberOfBombs) {
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
                Grid grid = sceneRoot.bombGrid;
                Timer timer = sceneRoot.informationRibbon.timer;
                
                if (!grid.game.isFinished()) {
                    timer.button.getOnAction().handle(event);
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
                Grid grid = sceneRoot.bombGrid;
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

