package minesweeper.game;

import java.io.File;
import javafx.scene.control.ListView;
import javafx.stage.Popup;

public class GameLoadPopup extends Popup {
    
    GameLoadPopup() {

        ListView<String> listView = new ListView<String>();

        try {
            File[] files = new File("./").listFiles();
            String fileName;
            for (File file : files) {
                if (file.isFile()) {
                    fileName = file.getName();
                    if (fileName.contains(".txt")) {
                        listView.getItems().add(fileName);
                    }
                }
            }
            centerOnScreen();
            getContent().add(listView);

        } catch (NullPointerException e) {
            System.out.println("GameLoadPopup.GameLoadPopup()");
            e.printStackTrace();
        }
    }
}
