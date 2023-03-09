package minesweeper.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
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
        Button closeButton = new Button("Close");
        Label title = new Label("Top 5 Most Recent Games");
        // closeButton.setPadding(new Insets());
        // closeButton.setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {

        //     }
        // });
        BorderPane borderPane = new BorderPane(listView, title, null, closeButton, null);
        borderPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), null)));
        borderPane.setBorder(new Border(new BorderStroke(Color.BLACK, null, null, null)));
        borderPane.setPadding(new Insets(10));
        BorderPane.setAlignment(closeButton, Pos.CENTER_RIGHT);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(closeButton, new Insets(10, 0, 0, 0));
        getContent().add(borderPane);
    }
}
