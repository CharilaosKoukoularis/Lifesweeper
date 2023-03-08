package minesweeper.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.ListView;

public class RoundsScene extends Scene {

    protected ListView<RoundHistory> listView = new ListView<RoundHistory>();

    RoundsScene() {
        super(new ListView<RoundHistory>());
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
        setRoot(listView);
        // getContent().add(listView);
    }
}
