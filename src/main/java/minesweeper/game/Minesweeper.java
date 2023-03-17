package minesweeper.game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Minesweeper extends Application {

    private Stage mainStage;
    private GameScene gameScene = new GameScene(640);
    private String loadedFile;

    private ArrayList<RecentGame> recentGamesList = new ArrayList<RecentGame>();

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        mainStage.setTitle("Medialab Minesweeper");
        mainStage.setResizable(false);
        mainStage.setScene(gameScene);
        mainStage.show();

        // new GameLoadPopup().show(mainStage);

        // GameCreationDialog gameCreationDialog = new GameCreationDialog();

        // gameCreationDialog.setOnCloseRequest(new EventHandler<DialogEvent>() {
            
        //     @Override
        //     public void handle(DialogEvent event) {
        //         try {
        //             Integer scenarioNumber = 0;
        //             File[] files = new File("./").listFiles();
        //             for (File file : files) {
        //                 if (file.isFile()) {
        //                     if (file.getName().contains("SCENARIO-")) {
        //                         scenarioNumber++;
        //                     }
        //                 }
        //             }
        //             try {
        //                 FileWriter fileWriter = new FileWriter("SCENARIO-" + scenarioNumber.toString() + ".txt");
        //                 String difficulty = "1";
        //                 if (gameCreationDialog.difficultySelectionBox.difficultyButton2.isSelected()) {
        //                     difficulty = "2";
        //                 } 
        //                 String hyperBomb = "0";
        //                 if (gameCreationDialog.hyperMineSelectionBox.toggleButton.isSelected()) {
        //                     hyperBomb = "1";
        //                 } 
        //                 fileWriter.write(difficulty + "\n" 
        //                         + gameCreationDialog.numberOfMinesSelectionBox.textField.getText() + "\n"
        //                         + gameCreationDialog.timeLimitSelectionBox.textField.getText() + "\n"
        //                         + hyperBomb + "\n");
        //                 fileWriter.close();
        //             } catch (IOException e) {
        //                 System.out.println("Minesweeper.start(...).new EventHandler() {...}.handle()");
        //                 e.printStackTrace();
        //             }
        //         } catch (NullPointerException e) {
        //             System.out.println("GameLoadPopup.GameLoadPopup()");
        //             e.printStackTrace();
        //         }
        //     }
        // });

        

        // // gameCreationPopup.numberOfMinesSelectionBox.textField.textProperty().addListener(new ChangeListener<String>() {
        
        // //     @Override
        // //     public void changed(ObservableValue<? extends String> ov, String oldString, String newString) {

        // //     }
        // // });

        // getMenuButton(GameScene.APPLICATION, GameScene.CREATE).setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         gameCreationDialog.show();
        //     }
        // });

        
        // getMenuButton(GameScene.APPLICATION, GameScene.LOAD).setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {

        //         ScenarioLoadDialog scenarioLoadDialog = new ScenarioLoadDialog();
        //         scenarioLoadDialog.show();
                
        //         scenarioLoadDialog.setOnCloseRequest(new EventHandler<DialogEvent>() {
        //             @Override
        //             public void handle(DialogEvent event) {
        //                 loadedFile = scenarioLoadDialog.getResult();
        //                 if (loadedFile != null) {
        //                     try {
        //                         new Scenario(new File(loadedFile));
        //                     } catch (InvalidScenarioException e) {
        //                         System.out.println("Invalid Scenario");
        //                         e.printStackTrace();
        //                         loadedFile = null;
        //                     }
        //                 }
        //             }   
        //         });
        //     }
        // });

        // getMenuButton(GameScene.APPLICATION, GameScene.START).setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         if (loadedFile != null) {
        //             // System.out.println("Chosen File: " + gameLoadChoiceDialog.getResult());
                    
        //             // gameScene.changeGame(new Game());


        //             try {
        //                 mainStage.setScene(new GameScene(new Game(new File(loadedFile)), 640));//new File("SCENARIO-ID.txt")
        //                 // mainStage.setScene(gameScene);
        //                 mainStage.show();
                        
        
        //                 SceneRoot sceneRoot = (SceneRoot) mainStage.getScene().getRoot();
        //                 Grid grid = sceneRoot.mineGrid;
        //                 Timer timer = sceneRoot.informationRibbon.getTimer();

        //                 // final ImageView selectedImage = new ImageView();   
        //                 // Image image1 = new Image(getClass().getResourceAsStream("/main/resources/flag.png"));
                
        //                 // selectedImage.setImage(image1);
                

        //                 grid.setDisable(true);
        //                 grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
        //                     @Override
        //                     public void handle(MouseEvent me) {
        //                         double posX = me.getX();
        //                         double posY = me.getY();
                    
        //                         int colX = (int)(posX / grid.cellWidth);
        //                         int colY = (int)(posY / grid.cellWidth);
                    
        //                         if (me.getButton() == MouseButton.PRIMARY) {
        //                             if (grid.cell[colX][colY].content == Cell.EMPTY) {
        //                                     grid.openAdjacent(colX, colY);
        //                                     if (grid.cellsOpened == (grid.size * grid.size - grid.game.scenario.numberOfMines)) {
        //                                         grid.revealAll(Game.FINISHED_WIN);
        //                                         new InformationDialog("Game Over", "Win!\nYou found all the mines.").show();
        //                                     }
            
        //                             } else if (grid.cell[colX][colY].content == Cell.MINE && grid.cell[colX][colY].status != Cell.OPENED) {
        //                                 grid.cell[colX][colY].setFill(Color.RED);
        //                                 grid.cell[colX][colY].status = Cell.OPENED;
        //                                 grid.revealAll(Game.FINISHED_LOSS); 
        //                                 new InformationDialog("Game Over", "Loss.\nYou stepped on a mine.").show();
        //                             }
            
        //                         } else if (me.getButton() == MouseButton.SECONDARY) { // System.out.println(colX);System.out.println(colY);
        //                             if ((grid.cell[colX][colY].getFill() != Color.ORANGE) && (grid.cell[colX][colY].status != Cell.OPENED) && (grid.cellsMarked != grid.game.scenario.numberOfMines)) {
        //                                 if (grid.cell[colX][colY].content == Cell.MINE) {
        //                                     if ((grid.markedMines < 4) && grid.cell[colX][colY].hyperMine) {
        //                                         grid.defuseHyperMine();
        //                                         // System.out.println("Hypermine");
        //                                     }
        //                                     grid.markedMines++;
        //                                 }
        //                                 grid.cell[colX][colY].setFill(Color.ORANGE);
        //                                 // grid.cell[colX][colY].setFill(new ImagePattern(image1));
        //                                 grid.cellsMarked++;
        //                             } else if ((grid.cell[colX][colY].getFill() == Color.ORANGE) && (grid.cell[colX][colY].status != Cell.OPENED)) {
        //                                 grid.cell[colX][colY].setFill(Color.GRAY);
        //                                 grid.cellsMarked--;
        //                             }
        //                         }
        //                         Label markedLabel = (Label) sceneRoot.informationRibbon.getChildren().get(2);
        
        //                         if (grid.cellsMarked != grid.game.scenario.numberOfMines) {
        //                             markedLabel.setStyle("-fx-text-fill: black;");
        //                         } else {
        //                             markedLabel.setStyle("-fx-text-fill: red;");
        //                         } 
        //                         markedLabel.setText("Marked Cells: " + grid.cellsMarked.toString());
        //                     }
        //                 });

        //                 HBox hBox = (HBox) sceneRoot.informationRibbon.getChildren().get(InformationRibbon.HBOX);
        //                 Label nameLabel = (Label) hBox.getChildren().get(InformationRibbon.TIMELABEL);
        //                 Label secondsLabel = (Label) hBox.getChildren().get(InformationRibbon.SECONDSLABEL);
        //                 timer.textProperty().addListener(new ChangeListener<String>() {
        //                     @Override
        //                     public void changed(ObservableValue <? extends String> ov, String oldProperty, String newProperty) {
        //                         if (Integer.parseInt(newProperty) <= 10) {
        //                             nameLabel.setStyle("-fx-text-fill: red;");
        //                             timer.setStyle("-fx-text-fill: red;");
        //                             secondsLabel.setStyle("-fx-text-fill: red;");
        //                         }
        //                         if (Integer.parseInt(newProperty) == 0) {
        //                             grid.game.end(Game.FINISHED_LOSS);
        //                             new InformationDialog("Game Over", "Loss.\nNo time left.").show();
        //                         }
        //                     }
        //                 });        
                        
        //                 if (grid.game.status == Game.NOT_STARTED) {
        //                     grid.game.start();
        //                     grid.setDisable(false);
        //                 }
        //             } catch (InvalidScenarioException e) {
        //                 System.out.println("Minesweeper.start(...).new EventHandler() {...}.handle(...).new EventHandler() {...}.handle()");
        //                 e.printStackTrace();
        //             }
        //         } else {
        //             new InformationDialog("No loaded scenario", "Please load a scenario to continue.").show();
        //         }
        //     }
        // });

        // getMenuButton(GameScene.APPLICATION, GameScene.EXIT).setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         mainStage.close();
        //     }
        // });

        // getMenuButton(GameScene.DETAILS, GameScene.ROUNDS).setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         new RoundsDialog().show();
        //     }
        // });

        // getMenuButton(GameScene.DETAILS, GameScene.SOLUTION).setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         SceneRoot sceneRoot = (SceneRoot) mainStage.getScene().getRoot();
        //         Grid grid = sceneRoot.mineGrid;
        //         if (grid != null) {
        //             grid.revealAll(Game.FINISHED_LOSS);
        //         }
        //     }
        // });
       

        // mainStage.sceneProperty().addListener(new ChangeListener<Scene>() {
          
        //     @Override
        //     public void changed(ObservableValue<? extends Scene> ov, Scene oldScene, Scene newScene) {
        //         SceneRoot sceneRoot = (SceneRoot) oldScene.getRoot();
        //         Grid grid = (Grid) sceneRoot.getCenter();

        //         if (grid != null) {
        //             if (grid.game.status != Game.NOT_STARTED)  {
        //                 if (recentGamesList.size() == 5) {
        //                     recentGamesList.remove(0);
        //                 }
        //                 if (grid.game.status == Game.ONGOING) {
        //                     grid.game.end(Game.FINISHED_LOSS);
        //                 }
        //                 recentGamesList.add(new RecentGame(grid));
                        
        //             }
        //             try {
        //                 FileWriter myWriter = new FileWriter("Recent_Games.txt");
        //                 RecentGame recentGame;
        //                 for (int i = 0; i < recentGamesList.size(); i++) {
        //                     recentGame = (RecentGame) recentGamesList.get(i);
        //                     myWriter.write(recentGame.scenario.numberOfMines.toString() + ", " 
        //                             + recentGame.cellsOpened.toString() + ", "
        //                             + recentGame.scenario.timeLimit.toString() + ", "
        //                             + recentGame.outcome + "\n");
        //                 }
        //                 myWriter.close();
        //                 // System.out.println("Successfully wrote to the file.");
        //             } catch (IOException e) {
        //                 System.out.println("An error occurred.");
        //                 e.printStackTrace();
        //             }
        //         }
        //     }
        // });

        // mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        //     @Override
        //     public void handle(WindowEvent event) {
        //         try (FileWriter fileWriter = new FileWriter(new File("Recent_Games.txt"))) {
        //             fileWriter.flush();
        //         } catch (IOException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // });

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

