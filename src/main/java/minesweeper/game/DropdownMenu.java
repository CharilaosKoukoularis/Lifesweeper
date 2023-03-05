package minesweeper.game;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class DropdownMenu extends Menu {
    
    DropdownMenu(String name) {
        super(name);
    }

    DropdownMenu(String name, String... items) {
        this(name);
        int length = items.length;
        for (int i = 0; i < length; i++) {
            getItems().add(new MenuItem(items[i]));
        }
    }
}
