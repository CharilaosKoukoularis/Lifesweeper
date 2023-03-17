package minesweeper.game;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SelectionBlock extends VBox {

    protected final static int INITIAL = -2;
    protected final static int EMPTY = -1;
    protected final static int VALID = 1;
    protected final static int INVALID = 0;

    protected final static int TEXT = 0;
    protected final static int BUTTON = 1;
    protected final static int RADIOBUTTONS = 2;
    
    protected static int difficulty;

    private Label title;
    protected TextField textField;
    protected RadioButton [] radioButtons;
    protected Label warningMessage = new Label();
    private String warningString;

    protected int status = INITIAL;
    protected Integer selectionMethod;
    protected Integer upperBound;
    protected Integer lowerBound;

    SelectionBlock(String name, String warning, RadioButton... buttons) {
        title = new Label(name);
        radioButtons = buttons;
        selectionMethod = radioButtons.length;
        HBox hBox = new HBox(25, radioButtons);
        hBox.setPadding(new Insets(0, 0, 0, 5));
        if (selectionMethod == RADIOBUTTONS) {
            new ToggleGroup().getToggles().addAll(radioButtons);
        }
        warningString = warning;
        warningMessage.setStyle("-fx-text-fill: red;");
        getChildren().addAll(title, hBox, warningMessage);
        setSpacing(5);
    }

    SelectionBlock(String name, String warning, TextField field) {
        title = new Label(name);
        selectionMethod = TEXT;
        textField = field;
        warningString = warning;
        warningMessage.setStyle("-fx-text-fill: red;");
        getChildren().addAll(title, textField, warningMessage);
    }

    public String getInput() {
        if (selectionMethod == RADIOBUTTONS) {
            if (radioButtons[0].isSelected()) {
                return "1";
            } else if (radioButtons[1].isSelected()) {
                return "2";
            } else {
                return null;
            }
        } else if (selectionMethod == BUTTON) {
            if (radioButtons[0].isSelected()) {
                return "1";
            } else {
                return "0";
            }
        } else {
            try {
                return textField.getText();
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    public void setBounds(int lower, int upper) {
        lowerBound = lower;
        upperBound = upper;

        String text;
        if (selectionMethod == TEXT) {
            text = lowerBound.toString() + " - " + upperBound.toString();
            textField.setPromptText(text);
            textField.setTooltip(new Tooltip(text));
        } else if (selectionMethod == BUTTON) {
            if (SelectionBlock.difficulty == 1) {
                text = "Unavailable";
            } else {
                text = "Available";
            }
            radioButtons[0].setTooltip(new Tooltip(text));
        }
    }

    public static void setDifficulty(int value) {
        difficulty = value;
    }

    public int checkStatus() {
        String string = getInput();
        if (string == null) {
            if (status != INITIAL) {
                status = EMPTY;
            }
        } else {
            try {
                Integer value = Integer.valueOf(string);
                if (value < lowerBound || value > upperBound) {
                    status = INVALID;
                } else {
                    status = VALID;
                }
            } catch (NumberFormatException e) {
                File[] files = new File("./scenarios/").listFiles();
                status = VALID;
                for (File file : files) {
                    if (file.isFile()) {
                        if (file.getName().equals(string + ".txt")) {
                            status = INVALID;
                            break;
                        }
                    }
                }
            }
        }
        return status;
    }

    public void showWarningMessage() {
        if (status == EMPTY) {
            warningMessage.setText("Empty Field");
        } else if (status == INVALID) {
            warningMessage.setText(warningString);
        } else {
            warningMessage.setText(null);
        }
    }
}