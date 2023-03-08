package minesweeper.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.control.ListView;
import javafx.stage.Popup;

public class RoundsPopup extends Popup {
    
    protected ListView<RoundHistory> listView = new ListView<RoundHistory>();

    RoundsPopup() {
        try {
            File file = new File("Recent_Games.txt");
            Scanner scanner = new Scanner(file);
            String values [];
            int game = 1;
            while (scanner.hasNextLine()) {
                values = scanner.nextLine().split(", ");
                if (values.length == 4) {
                    listView.getItems().add(new RoundHistory(game++, Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), values[3]));
                }
            }
            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        getContent().add(listView);
    }
}
