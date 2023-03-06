package minesweeper.game;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

public class SelectionBox extends VBox {
    
    private Label label;
    protected Control selectiondMethod;
    protected TextField textField;
    protected ToggleButton toggleButton;
    protected Button button;
    protected Label errorMessage = new Label();
    private String errorString;
    protected int status = 0;

    // SelectionBox(String name, Control selectionMethod, String helpMessage) {
    //     label = new Label(name);
    //     errorMessage.setStyle("-fx-text-fill: red;");
    //     if (selectionMethod.getClass().equals(TextField.class)) {
    //         textField = (TextField)selectionMethod;
    //     } else if (selectionMethod.getClass().equals(RadioButton.class)) {
    //         radioButton = (RadioButton)selectionMethod;
    //     }
    //     selectionMethod.setTooltip(new Tooltip(helpMessage));
    //     // textField.setPromptText(prompt);//"9 - 11 or 35 - 45");
    //     this.getChildren().addAll(label, textField, errorMessage);
    // }

    SelectionBox(String name, Control control) {//}, String helpMessage) {
        label = new Label(name);
        errorMessage.setStyle("-fx-text-fill: red;");
        selectiondMethod = control;
        // if (selectionMethod.getClass().equals(TextField.class)) {
        //     textField = (TextField)selectionMethod;
        // } else if (selectionMethod.getClass().equals(RadioButton.class)) {
        //     radioButton = (RadioButton)selectionMethod;
        // }
        // selectionMethod.setTooltip(new Tooltip(helpMessage));
        // textField.setPromptText(prompt);//"9 - 11 or 35 - 45");
        getChildren().addAll(label, selectiondMethod, errorMessage);
    }

    // SelectionBox(String name, TextField tField, String... helpMessages) {
    //     label = new Label(name);
    //     errorMessage.setStyle("-fx-text-fill: red;");
    //     textField = tField; 
    //     textField.setTooltip(new Tooltip(helpMessages[0]));
    //     textField.setPromptText(helpMessages[1]);//"9 - 11 or 35 - 45");
    //     this.getChildren().addAll(label, textField, errorMessage);
    //     radioButton.setTooltip(new Tooltip(helpMessages[0]));
    //     radioButton.setPadding(new Insets(0, 0, 0, 5));
    //     this.getChildren().addAll(label, radioButton, errorMessage);
    // }

    SelectionBox(String name, TextField tField, String error, String... helpMessages) {
        label = new Label(name);
        textField = tField; 
        textField.setTooltip(new Tooltip(helpMessages[0]));
        textField.setPromptText(helpMessages[1]);
        errorString = error;
        errorMessage.setStyle("-fx-text-fill: red;");
        getChildren().addAll(label, textField, errorMessage);
    }

    SelectionBox(String name, ToggleButton button, String error, String... helpMessages) {
        label = new Label(name);
        toggleButton = button;
        toggleButton.setTooltip(new Tooltip(helpMessages[0]));
        toggleButton.setPadding(new Insets(0, 0, 0, 5));
        errorString = error;
        errorMessage.setStyle("-fx-text-fill: red;");
        getChildren().addAll(label, toggleButton, errorMessage);
    }

    SelectionBox(Button newButton) {
        button = newButton;
        errorMessage.setStyle("-fx-text-fill: red;");
        getChildren().addAll(button, errorMessage);
    }

    SelectionBox(String name, ToggleGroup toggleGroup, String... helpMessages) {
        label = new Label(name);
        errorMessage.setStyle("-fx-text-fill: red;");
        // this.getC
    }

    public void showErrorMessage() {
        errorMessage.setText(errorString);
    }
}