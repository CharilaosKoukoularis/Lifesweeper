package minesweeper.game;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ScenarioLoadDialog extends Dialog<String> {

    protected ListView<String> listView = new ListView<String>();

    ScenarioLoadDialog() {
        
        try {
            File[] files = new File("./scenarios/").listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    listView.getItems().add(file.getName());
                }
            }
            setTitle("Load Game");
            getDialogPane().setContent(listView);
            getDialogPane().setPadding(new Insets(5));
            getDialogPane().getButtonTypes().addAll(
                new ButtonType("Cancel", ButtonData.CANCEL_CLOSE), 
                new ButtonType("Load", ButtonData.APPLY)
            );
            
            setResultConverter(new Callback<ButtonType, String>() {
                @Override
                public String call(ButtonType button) {
                    if (button.getButtonData() == ButtonData.APPLY) {
                        return listView.getSelectionModel().getSelectedItem();
                    } else {
                        return null;
                    }
                }
            });

        } catch (NullPointerException e) {
            System.out.println("GameLoadPopup.GameLoadPopup()");
            e.printStackTrace();
        }
    }

}