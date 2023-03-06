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
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

public class GameCreationPopup extends Popup {

    protected DifficultySelectionBox difficultySelectionBox = new DifficultySelectionBox();//5, difficultyLabel, difficultyButtonsBox);
    protected final SelectionBox numberOfBombsSelectionBox = new SelectionBox("Number Of Bombs", new TextField(), "Value out of bounds", "Difficulty 1: 9-11\nDifficulty 2: 35-45", "9 - 11 or 35 - 45");
    protected final SelectionBox timeLimitSelectionBox = new SelectionBox("Time Limit (seconds)", new TextField(), "Value out of bounds", "Difficulty 1: 120-180\nDifficulty 2: 240-360", "120 - 180 or 240 - 360");
    protected SelectionBox hyperBombSelectionBox = new SelectionBox("HyperBomb Enable", new RadioButton(), "HyperBomb Unavailable", "Difficulty 1: Unavailable\nDifficulty 2: Available");
    protected SelectionBox completionButtonBox = new SelectionBox(new Button("Complete"));
    
    GameCreationPopup() {
        // creationPopup.setX(stage.getX() + stage.getWidth() / 2);
        // creationPopup.setY(stage.getY() + stage.getHeight() / 2);
        centerOnScreen();
        getContent().add(new Circle(50, Color.AQUAMARINE));

        GridPane popupPane = new GridPane();
        popupPane.setPadding(new Insets(15));
        popupPane.setVgap(10);
        popupPane.setHgap(10);

        // numberOfBombsSelectionBox.textField.setTooltip(new Tooltip("Difficulty 1: 9-11\nDifficulty 2: 35-45"));
    
        // completionButton.setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         try {
        //             FileWriter myWriter = new FileWriter("Created Scenario.txt");
        //             String difficulty = "1";
        //             if (difficultyButton2.isSelected()) {
        //                 difficulty = "2";
        //             } 
        //             String hyperBomb = "0";
        //             if (hyperBombEnableButton.isSelected()) {
        //                 hyperBomb = "1";
        //             } 
        //             myWriter.write(difficulty + "\n" 
        //                     + numberOfBombsField.getText() + "\n"
        //                     + timeLimitField.getText() + "\n"
        //                     + hyperBomb + "\n");
        //             myWriter.close();
        //             difficultyButton1.setSelected(false);
        //             difficultyButton2.setSelected(false);
        //             numberOfBombsField.setText(null);
                    
        //             System.out.println("Successfully wrote to the file.");
        //         } catch (IOException e) {
        //             System.out.println("An error occurred.");
        //             e.printStackTrace();
        //         }
        //         // creationPopup.hide();
        //     }
        // });
    
        popupPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(3), null)));
        // popupPane.getChildren().addAll(difficultyLabel, difficultyButtonsBox, numberOfBombsLabel, numberOfBombsField, timeLimitLabel, timeLimitField, hyperBombEnableLabel, hyperBombEnableButton);
        // popupPane.getChildren().addAll(difficultySelectionBox, difficultyError, numberOfBombsSelectionBox, numberOfBombsError, timeLimitSelectionBox, timeLimitError, hyperBombSelectionBox, hyperBombError, completionButtonBox);
        popupPane.getChildren().addAll(difficultySelectionBox,  numberOfBombsSelectionBox, timeLimitSelectionBox, hyperBombSelectionBox, completionButtonBox);
        // popupPane.setFocusTraversable(true);

        GridPane.setConstraints(difficultySelectionBox, 0, 0);
        GridPane.setConstraints(numberOfBombsSelectionBox, 0, 1);
        GridPane.setConstraints(timeLimitSelectionBox, 0, 2);
        GridPane.setConstraints(hyperBombSelectionBox, 0, 3);
        GridPane.setConstraints(completionButtonBox, 0, 4);
        
        getContent().add(popupPane);
    }
}