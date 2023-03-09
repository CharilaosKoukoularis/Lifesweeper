package minesweeper.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Minesweeper extends Application {

    private Stage mainStage;
    private GameScene gameScene = new GameScene(640);
    private RoundsPopup roundsPopup;

    private ArrayList<RecentGame> recentGamesList = new ArrayList<RecentGame>();

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        mainStage.setTitle("Medialab Minesweeper");
        mainStage.setResizable(false);
        mainStage.setScene(gameScene);
        mainStage.show();

        // new GameLoadPopup().show(mainStage);

        
        GameCreationPopup gameCreationPopup = new GameCreationPopup();

        gameCreationPopup.numberOfMinesSelectionBox.textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldString, String newString) {

            }
        });

        gameCreationPopup.completionButtonBox.button.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    FileWriter fileWriter = new FileWriter("Created_Scenario.txt");
                    String difficulty = "1";
                    if (gameCreationPopup.difficultySelectionBox.difficultyButton2.isSelected()) {
                        difficulty = "2";
                    } 
                    String hyperBomb = "0";
                    if (gameCreationPopup.hyperMineSelectionBox.toggleButton.isSelected()) {
                        hyperBomb = "1";
                    } 
                    fileWriter.write(difficulty + "\n" 
                            + gameCreationPopup.numberOfMinesSelectionBox.textField.getText() + "\n"
                            + gameCreationPopup.timeLimitSelectionBox.textField.getText() + "\n"
                            + hyperBomb + "\n");
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Minesweeper.start(...).new EventHandler() {...}.handle()");
                    e.printStackTrace();
                }

                gameCreationPopup.hide();
            }
        });
        
        getMenuButton(GameScene.APPLICATION, GameScene.CREATE).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameCreationPopup.show(mainStage);
            }
        });

        GameLoadChoiceDialog gameLoadChoiceDialog = new GameLoadChoiceDialog();

        getMenuButton(GameScene.APPLICATION, GameScene.LOAD).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                GameLoadPopup gameLoadPopup = new GameLoadPopup();
                // ScenarioLoadDialog scenarioLoadChoiceDialog = new ScenarioLoadDialog();
                gameLoadChoiceDialog.show();
                
                gameLoadChoiceDialog.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent event) {
                        if (gameLoadChoiceDialog.getResult() != null) {
                            System.out.println("Chosen File: " + gameLoadChoiceDialog.getResult());

                            gameScene.changeGame(new Game());
                            mainStage.setScene(new GameScene(new Game(new File(gameLoadChoiceDialog.getResult())), 640));//new File("SCENARIO-ID.txt")
                            // mainStage.setScene(gameScene);
                            mainStage.show();
                            
            
                            SceneRoot sceneRoot = (SceneRoot) mainStage.getScene().getRoot();
                            Grid grid = sceneRoot.mineGrid;
                            Timer timer = sceneRoot.informationRibbon.timer;
                            grid.setDisable(true);
                            grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent me) {
                                    double posX = me.getX();
                                    double posY = me.getY();
                        
                                    int colX = (int)(posX / grid.cellWidth);
                                    int colY = (int)(posY / grid.cellWidth);
                        
                                    if (me.getButton() == MouseButton.PRIMARY) {
                                        if (grid.cell[colX][colY].content == Cell.EMPTY) {
                                                grid.openAdjacent(colX, colY);
                                                if (grid.cellsOpened == (grid.size * grid.size - grid.game.scenario.numberOfMines)) {
                                                    grid.revealAll(Game.FINISHED_WIN);
                                                    timer.getTimeline().stop();
                                                }
                
                                        } else if (grid.cell[colX][colY].content == Cell.MINE && grid.cell[colX][colY].status != Cell.OPENED) {
                                            grid.cell[colX][colY].setFill(Color.RED);
                                            grid.cell[colX][colY].status = Cell.OPENED;
                                            grid.revealAll(Game.FINISHED_LOSS);
                                            timer.getTimeline().stop();
                                        }
                
                                    } else if (me.getButton() == MouseButton.SECONDARY) { System.out.println(colX);System.out.println(colY);
                                        if ((grid.cell[colX][colY].getFill() != Color.ORANGE) && (grid.cell[colX][colY].status != Cell.OPENED) && (grid.cellsMarked != grid.game.scenario.numberOfMines)) {
                                            if (grid.cell[colX][colY].content == Cell.MINE) {
                                                if ((grid.markedMines <= 4) && grid.cell[colX][colY].hyperMine) {
                                                    grid.defuseHyperMine();
                                                    System.out.println("Hypermine");
                                                }
                                                grid.markedMines++;
                                            }
                                            grid.cell[colX][colY].setFill(Color.ORANGE);
                                            grid.cellsMarked++;
                                        } else if ((grid.cell[colX][colY].getFill() == Color.ORANGE) && (grid.cell[colX][colY].status != Cell.OPENED)) {
                                            grid.cell[colX][colY].setFill(Color.GRAY);
                                            grid.cellsMarked--;
                                        }
                                    }
                                    Label markedLabel = (Label) sceneRoot.informationRibbon.getChildren().get(2);
            
                                    if (grid.cellsMarked != grid.game.scenario.numberOfMines) {
                                        markedLabel.setStyle("-fx-text-fill: black;");
                                    } else {
                                        markedLabel.setStyle("-fx-text-fill: red;");
                                    } 
                                    markedLabel.setText("Marked Cells: " + grid.cellsMarked.toString());
                                }
                            });
                        
                        }
                    }   
                });
                
                // gameLoadPopup.show(mainStage);
                // gameLoadPopup.getContent().get(0).setOnMouseClicked(new EventHandler<Event>() {
                //     @Override
                //     public void handle(Event event) {
                //         mainStage.setScene(new GameScene(new Game(new File("SCENARIO-ID.txt")), 640));
                //         mainStage.show();
                //     }
                // });
            }
        });

        getMenuButton(GameScene.APPLICATION, GameScene.START).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneRoot sceneRoot = (SceneRoot) mainStage.getScene().getRoot();
                HBox hBox = (HBox) sceneRoot.informationRibbon.getChildren().get(InformationRibbon.HBOX);
                Label nameLabel = (Label) hBox.getChildren().get(InformationRibbon.TIMELABEL);
                Label secondsLabel = (Label) hBox.getChildren().get(InformationRibbon.SECONDSLABEL);
                Grid grid = sceneRoot.mineGrid;
                Timer timer = sceneRoot.informationRibbon.timer;

                timer.label.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue <? extends String> ov, String oldProperty, String newProperty) {
                        if (Integer.parseInt(newProperty) <= 10) {
                            nameLabel.setStyle("-fx-text-fill: red;");
                            timer.label.setStyle("-fx-text-fill: red;");
                            secondsLabel.setStyle("-fx-text-fill: red;");
                        }
                        if (Integer.parseInt(newProperty) == 0) {
                            grid.revealAll(Game.FINISHED_LOSS);
                        }
                    }
                });        
                
                if (grid.game.status == Game.NOT_STARTED) {
                    if (timer.remainingTime.intValue() == timer.startingTime) {// Not needed anymore
                        timer.button.getOnAction().handle(event);
                    }
                    grid.game.status = Game.ONGOING;
                    grid.setDisable(false);
                } else {//if (grid.game.status != Game.ONGOING) {
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


        Stage stage2 = new Stage();
        getMenuButton(GameScene.DETAILS, GameScene.ROUNDS).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                roundsPopup = new RoundsPopup();
                roundsPopup.show(mainStage);
                // RoundsScene roundsScene = new RoundsScene();
                // stage2.setScene(roundsScene);
                // stage2.show();
            }
        });

        getMenuButton(GameScene.DETAILS, GameScene.SOLUTION).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneRoot sceneRoot = (SceneRoot) mainStage.getScene().getRoot();
                Grid grid = sceneRoot.mineGrid;
                Timer timer = sceneRoot.informationRibbon.timer;

                grid.revealAll(Game.FINISHED_LOSS);
                if (timer.timeline != null) {
                    timer.getTimeline().stop();
                }
            }
        });
       

        mainStage.sceneProperty().addListener(new ChangeListener<Scene>() {
          
            @Override
            public void changed(ObservableValue<? extends Scene> ov, Scene oldScene, Scene newScene) {
                SceneRoot sceneRoot = (SceneRoot) oldScene.getRoot();
                Grid grid = (Grid) sceneRoot.getCenter();

                if (grid != null) {
                    if (recentGamesList.size() == 5) {
                        recentGamesList.remove(0);
                    }
                    recentGamesList.add(new RecentGame(grid));

                    try {
                        FileWriter myWriter = new FileWriter("Recent_Games.txt");
                        RecentGame recentGame;
                        for (int i = 0; i < recentGamesList.size(); i++) {
                            recentGame = (RecentGame) recentGamesList.get(i);
                            myWriter.write(recentGame.scenario.numberOfMines.toString() + ", " 
                                    + recentGame.cellsOpened.toString() + ", "
                                    + recentGame.scenario.timeLimit.toString() + ", "
                                    + recentGame.outcome + "\n");
                        }
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                }
            }
        });

        SceneRoot sceneRoot = (SceneRoot) mainStage.getScene().getRoot();
        Grid grid;
        sceneRoot.centerProperty().addListener(new ChangeListener<Node>() {
            @Override
            public void changed(ObservableValue<? extends Node> ov, Node oldNode, Node newNode) {
                
                System.out.println("Hello World!");
                if (newNode != null) {
                    System.out.println("New Grid.");
                }
            }

        });
        
        sceneRoot.getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Node> c) {
                
                System.out.println("Hello World");
                
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

