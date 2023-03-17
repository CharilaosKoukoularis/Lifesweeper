package minesweeper.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ButtonBar.ButtonData;

public class RoundsDialog extends Dialog<String> {
    
    protected ListView<RoundHistory> listView = new ListView<RoundHistory>();

    RoundsDialog() {
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
        } finally {
            setTitle("Top 5 Most Recent Rounds");
            if (listView.getItems().isEmpty()) {
                getDialogPane().setContent(new Label("No Recent Games"));
            }
            else {
                getDialogPane().setContent(listView);
            }
            getDialogPane().setMinSize(250, 100);
            getDialogPane().setPadding(new Insets(5));
            getDialogPane().getButtonTypes().add(new ButtonType("Close", ButtonData.CANCEL_CLOSE));
        }
        
    }
}
