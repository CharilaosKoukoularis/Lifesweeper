package minesweeper.game;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;

public class GameCreationDialog extends Dialog<String> {

    protected final static int DIFFICULTY = 0;
    protected final static int NUMBER_OF_MINES = 1;
    protected final static int TIME_LIMIT = 2;
    protected final static int HYPERMINE = 3;
    protected final SelectionBlock scenarioNameSelectionBlock = new SelectionBlock(
        "Scenario Name", "Name Already In Use", new TextField() 
    );
    protected final SelectionBlock difficultySelectionBlock = new SelectionBlock(
        "Difficulty Selection", "Invalid Value Combination", new RadioButton("1"), new RadioButton("2")
    );
    protected SelectionBlock numberOfMinesSelectionBlock = new SelectionBlock(
        "Number Of Mines", "Value Out Of Bounds", new TextField()
    );
    protected SelectionBlock timeLimitSelectionBlock = new SelectionBlock(
        "Time Limit (seconds)", "Value Out Of Bounds", new TextField()
    );
    protected final SelectionBlock hyperMineSelectionBlock = new SelectionBlock(
        "Hyper-Mine Enable", "HyperMine Unavailable", new RadioButton()
    );
    
    GameCreationDialog() {

        VBox vBlock = new VBox(10);
        vBlock.setPadding(new Insets(15));

        // scenarioNameSelectionBlock.inputField.setTooltip(new Tooltip("e.g. scenario-id"));
        difficultySelectionBlock.radioButtons[0].setTooltip(new Tooltip("Mines: 9-11\nTime: 120-180 s\nHyper-Mine: Unavailable"));
        difficultySelectionBlock.radioButtons[1].setTooltip(new Tooltip("Mines: 35-45\nTime: 240-360 s\nHyper-Mine: Available"));
        difficultySelectionBlock.radioButtons[0].getToggleGroup().selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue <? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

                if (difficultySelectionBlock.radioButtons[0].isSelected()) {
                    SelectionBlock.setDifficulty(1);
                } else if (difficultySelectionBlock.radioButtons[1].isSelected()) {
                    SelectionBlock.setDifficulty(2);
                }
                difficultySelectionBlock.status = checkInputs();
                difficultySelectionBlock.showWarningMessage();
            }
        });

        numberOfMinesSelectionBlock.textField.setTooltip(new Tooltip("Difficulty 1: 9-11\nDifficulty 2: 35-45"));
        numberOfMinesSelectionBlock.textField.setOnKeyReleased(keyPressHandler);

        timeLimitSelectionBlock.textField.setTooltip(new Tooltip("Difficulty 1: 120-180\nDifficulty 2: 240-360"));
        timeLimitSelectionBlock.textField.setOnKeyReleased(keyPressHandler);

        hyperMineSelectionBlock.radioButtons[0].setTooltip(new Tooltip("Difficulty 1: Unavailable\nDifficulty 2: Available"));
        hyperMineSelectionBlock.radioButtons[0].setOnMouseClicked(clickHandler);
        
        vBlock.getChildren().addAll(
            scenarioNameSelectionBlock,
            difficultySelectionBlock,
            numberOfMinesSelectionBlock,
            timeLimitSelectionBlock,
            hyperMineSelectionBlock
        );

        setTitle("Create Game");
        getDialogPane().setContent(vBlock);
        getDialogPane().getButtonTypes().add(new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));
        getDialogPane().getButtonTypes().add(new ButtonType("Create", ButtonData.APPLY));
        scenarioNameSelectionBlock.textField.requestFocus();  
    }

    public int checkInputs() {

        int mode = SelectionBlock.difficulty - 1;
        if (SelectionBlock.difficulty < 1 || SelectionBlock.difficulty > 2) {
            return SelectionBlock.EMPTY;
        } else {
            numberOfMinesSelectionBlock.setBounds(Scenario.MIN_MINES[mode], Scenario.MAX_MINES[mode]);
            numberOfMinesSelectionBlock.checkStatus();
            numberOfMinesSelectionBlock.showWarningMessage();

            timeLimitSelectionBlock.setBounds(Scenario.MIN_TIME[mode], Scenario.MAX_TIME[mode]);
            timeLimitSelectionBlock.checkStatus();
            timeLimitSelectionBlock.showWarningMessage();

            hyperMineSelectionBlock.setBounds(0, Scenario.HYPER_MINE[mode]);
            hyperMineSelectionBlock.checkStatus();
            hyperMineSelectionBlock.showWarningMessage();

            if ((numberOfMinesSelectionBlock.checkStatus() == SelectionBlock.INVALID) ||
                (timeLimitSelectionBlock.checkStatus() == SelectionBlock.INVALID) ||
                (hyperMineSelectionBlock.checkStatus() == SelectionBlock.INVALID)) {
                return SelectionBlock.INVALID;
            } else {
                return SelectionBlock.VALID;
            }
        }
    }    

    public EventHandler<Event> eventHandler = new EventHandler<Event>() {

        @Override
        public void handle(Event event) {
            difficultySelectionBlock.status = checkInputs();
            difficultySelectionBlock.showWarningMessage();
        }
        
    };

    public EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            difficultySelectionBlock.status = checkInputs();
            difficultySelectionBlock.showWarningMessage();
        }
        
    };

    public EventHandler<KeyEvent> keyPressHandler = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {
            difficultySelectionBlock.status = checkInputs();
            difficultySelectionBlock.showWarningMessage();
        }
    };
}