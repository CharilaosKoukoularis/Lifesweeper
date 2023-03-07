package minesweeper.game;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DifficultySelectionBox extends VBox {
    
    private final Label difficultyLabel = new Label("Difficulty Selection");
    private Label difficultyError = new Label();
    private RadioButton difficultyButton1 = new RadioButton("1");
    private RadioButton difficultyButton2 = new RadioButton("2");
    private HBox difficultyButtonsBox = new HBox();
    
    DifficultySelectionBox() {
        difficultyError.setStyle("-fx-text-fill: red;");
        difficultyButton1.setTooltip(new Tooltip("Mines: 9-11\nTime: 120-180 s\nHyperMine: Unavailable"));
        difficultyButton2.setTooltip(new Tooltip("Mines: 35-45\nTime: 240-360 s\nHyperMine: Available"));
        difficultyButtonsBox.setSpacing(25);
        difficultyButtonsBox.getChildren().addAll(difficultyButton1, difficultyButton2);
        difficultyButtonsBox.setPadding(new Insets(0, 0, 0, 5));
        this.setSpacing(5);
        this.getChildren().addAll(difficultyLabel, difficultyButtonsBox, difficultyError);
    }

}