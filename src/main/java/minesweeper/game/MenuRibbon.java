package minesweeper.game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

public class MenuRibbon extends MenuBar {

    protected static final DropdownMenu APPLICATION = new DropdownMenu("Application", "Create", "Load", "Start", "Exit");
    protected static final DropdownMenu DETAILS = new DropdownMenu("Details", "Rounds", "Solution");
    
    protected final static Integer CREATE = 0;
    protected final static Integer LOAD = 1;
    protected final static Integer START = 2;
    protected final static Integer EXIT = 3;

    protected final static Integer ROUNDS = 0;
    protected final static Integer SOLUTION = 1;
    
    MenuRibbon() {
        super(APPLICATION, DETAILS);
        getOption(APPLICATION, CREATE).setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        getOption(APPLICATION, CREATE).setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                new GameCreationDialog().show();
            }
            
        });
        getOption(APPLICATION, LOAD).setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
        getOption(APPLICATION, START).setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        getOption(APPLICATION, EXIT).setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        getOption(APPLICATION, EXIT).setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                getScene().getWindow().hide();
            }
            
        });
        getOption(DETAILS, ROUNDS).setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        getOption(DETAILS, SOLUTION).setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+S"));

        
    }

    public MenuItem getOption(DropdownMenu menuName, Integer itemName) {
        return menuName.getItems().get(itemName);
    }
}
