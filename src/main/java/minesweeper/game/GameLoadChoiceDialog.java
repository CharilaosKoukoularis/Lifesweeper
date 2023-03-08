package minesweeper.game;

import java.io.File;

import javafx.scene.control.ChoiceDialog;

public class GameLoadChoiceDialog extends ChoiceDialog<String> {
    
    GameLoadChoiceDialog() {
        setGraphic(null);
        setHeaderText(null);
        setTitle("Load Game");
        try {
            File[] files = new File("./").listFiles();
            String fileName;
            for (File file : files) {
                if (file.isFile()) {
                    fileName = file.getName();
                    if (fileName.contains("SCENARIO-")) {
                        getItems().add(fileName);
                    }
                }
            }

        } catch (NullPointerException e) {
            System.out.println("GameLoadPopup.GameLoadPopup()");
            e.printStackTrace();
        }
    }

}
