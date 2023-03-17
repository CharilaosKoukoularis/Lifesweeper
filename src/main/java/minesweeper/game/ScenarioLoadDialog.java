package minesweeper.game;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import javafx.util.Callback;

public class ScenarioLoadDialog extends Dialog<String> {

    protected ListView<String> listView = new ListView<String>();

    ScenarioLoadDialog() {
        setTitle("Load Game");
        try {
            File[] files = new File("./").listFiles();
            String fileName;
            for (File file : files) {
                if (file.isFile()) {
                    fileName = file.getName();
                    if (fileName.contains("SCENARIO")) {
                        listView.getItems().add(fileName);
                    }
                }
            }

            ButtonType loadButtonType = new ButtonType("Load", ButtonData.APPLY);

            getDialogPane().setContent(listView);
            getDialogPane().setPadding(new Insets(5));
            getDialogPane().getButtonTypes().add(new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));
            getDialogPane().getButtonTypes().add(loadButtonType);
            
            setResultConverter(new Callback<ButtonType, String>() {
                @Override
                public String call(ButtonType type) {
                    if (type == loadButtonType) {
                        return listView.getSelectionModel().getSelectedItem();
                    }
                    return null;
                }
            });

            Window window = getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(event -> window.hide());

        } catch (NullPointerException e) {
            System.out.println("GameLoadPopup.GameLoadPopup()");
            e.printStackTrace();
        }
    }

}