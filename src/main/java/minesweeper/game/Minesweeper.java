package minesweeper.game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * JavaFX App
 */
public class Minesweeper extends Application {

    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Medialab Minesweeper");
        stage.setScene(createScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public MenuBar menuRibbon() {
        // MenuBar menuBar = new 
        return new MenuBar(DropdownMenu("Application", "Create", "Load", "Start", "Exit"), DropdownMenu("Details", "Rounds", "Solution"));
    }

    public HBox informationRibbon() {
        return new HBox(5, new Text("Time"), new Text("Bombs"), new Text("Marked"));
    }

    public Scene createScene() {
        return new Scene(menuRibbon());
    }
}
