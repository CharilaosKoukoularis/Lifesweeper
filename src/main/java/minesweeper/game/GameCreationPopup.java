package minesweeper.game;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class GameCreationPopup extends Popup {

    protected final static int DIFFICULTY = 0;
    protected final static int NUMBER_OF_MINES = 1;
    protected final static int TIME_LIMIT = 2;
    protected final static int HYPERMINE = 3;
    protected final static int COMPLETION = 4;
    protected final static int CANCELATION = 5;

    protected DifficultySelectionBox difficultySelectionBox = new DifficultySelectionBox();//5, difficultyLabel, difficultyButtonsBox);
    protected final SelectionBox numberOfMinesSelectionBox = new SelectionBox("Number Of Mines", new TextField(), "Value out of bounds", "Difficulty 1: 9-11\nDifficulty 2: 35-45", "9 - 11 or 35 - 45");
    protected final SelectionBox timeLimitSelectionBox = new SelectionBox("Time Limit (seconds)", new TextField(), "Value out of bounds", "Difficulty 1: 120-180\nDifficulty 2: 240-360", "120 - 180 or 240 - 360");
    protected SelectionBox hyperMineSelectionBox = new SelectionBox("HyperMine Enable", new RadioButton(), "HyperMine Unavailable", "Difficulty 1: Unavailable\nDifficulty 2: Available");
    protected SelectionBox completionButtonBox = new SelectionBox(new Button("Complete"));
    
    GameCreationPopup() {
        // creationPopup.setX(stage.getX() + stage.getWidth() / 2);
        // creationPopup.setY(stage.getY() + stage.getHeight() / 2);
        centerOnScreen();
        // getContent().add(new Circle(50, Color.AQUAMARINE));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // numberOfMinesSelectionBox.textField.setTooltip(new Tooltip("Difficulty 1: 9-11\nDifficulty 2: 35-45"));
    
        // completionButton.setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         try {
        //             FileWriter myWriter = new FileWriter("Created Scenario.txt");
        //             String difficulty = "1";
        //             if (difficultyButton2.isSelected()) {
        //                 difficulty = "2";
        //             } 
        //             String hyperMine = "0";
        //             if (hyperMineEnableButton.isSelected()) {
        //                 hyperMine = "1";
        //             } 
        //             myWriter.write(difficulty + "\n" 
        //                     + numberOfMinesField.getText() + "\n"
        //                     + timeLimitField.getText() + "\n"
        //                     + hyperMine + "\n");
        //             myWriter.close();
        //             difficultyButton1.setSelected(false);
        //             difficultyButton2.setSelected(false);
        //             numberOfMinesField.setText(null);
                    
        //             System.out.println("Successfully wrote to the file.");
        //         } catch (IOException e) {
        //             System.out.println("An error occurred.");
        //             e.printStackTrace();
        //         }
        //         // creationPopup.hide();
        //     }
        // });
    
        gridPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(3), null)));
        // gridPane.getChildren().addAll(difficultyLabel, difficultyButtonsBox, numberOfMinesLabel, numberOfMinesField, timeLimitLabel, timeLimitField, hyperMineEnableLabel, hyperMineEnableButton);
        // gridPane.getChildren().addAll(difficultySelectionBox, difficultyError, numberOfMinesSelectionBox, numberOfMinesError, timeLimitSelectionBox, timeLimitError, hyperMineSelectionBox, hyperMineError, completionButtonBox);
        gridPane.getChildren().addAll(difficultySelectionBox,  numberOfMinesSelectionBox, timeLimitSelectionBox, hyperMineSelectionBox, completionButtonBox);
        // gridPane.setFocusTraversable(true);

        GridPane.setConstraints(difficultySelectionBox, 0, 0);
        GridPane.setConstraints(numberOfMinesSelectionBox, 0, 1);
        GridPane.setConstraints(timeLimitSelectionBox, 0, 2);
        GridPane.setConstraints(hyperMineSelectionBox, 0, 3);
        GridPane.setConstraints(completionButtonBox, 0, 4);
        
        getContent().add(gridPane);
    }
}