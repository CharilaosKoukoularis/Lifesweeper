module minesweeper.game {
    requires javafx.controls;
    requires javafx.fxml;

    opens minesweeper.game to javafx.fxml;
    exports minesweeper.game;
}
