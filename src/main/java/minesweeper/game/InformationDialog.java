package minesweeper.game;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;

public class InformationDialog extends Dialog<String> {
    
    InformationDialog(String title, String message) {
        setTitle(title);
        setContentText(message);
        getDialogPane().getButtonTypes().add(new ButtonType("Close", ButtonData.CANCEL_CLOSE));
    }
}
