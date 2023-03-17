package minesweeper.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Scenario {

    protected final static int [] MAX_MINES = {11, 45};
    protected final static int [] MIN_MINES = {9, 35};
    protected final static int [] MAX_TIME = {180, 360};
    protected final static int [] MIN_TIME = {120, 240};
    protected final static int [] HYPER_MINE = {0, 1};

    protected Integer difficulty;
    protected Integer numberOfMines;
    protected Integer timeLimit;
    protected Integer hyperMineExistence;
    protected File file;

                    Scenario() {

                        //- Game Mode -\\
                        int mode = new Random().nextInt(2);
                        difficulty = mode + 1;
                
                        //- Random Number Of Mines -\\
                        // numberOfMines = new Random().nextInt(MIN_MINES[mode], MAX_MINES[mode] + 1);
                        final int mineBound = MAX_MINES[mode] - MIN_MINES[mode] + 1;
                        numberOfMines = new Random().nextInt(mineBound) + MIN_MINES[mode];
                
                        //- Random Number Of Seconds -\\
                        // timeLimit = new Random().nextInt(MIN_TIME[mode], MAX_TIME[mode] + 1);
                        final int timeBound = MAX_TIME[mode] - MIN_TIME[mode] + 1;
                        timeLimit = new Random().nextInt(timeBound) + MIN_TIME[mode];
                        
                        // //- -\\
                        hyperMineExistence = new Random().nextInt(HYPER_MINE[mode] + 1);
                    }


    //- Scenario: Constructor From Text File -\\
    Scenario(File scenarioFile) throws InvalidScenarioException {

        try {
            Scanner scanner = new Scanner(scenarioFile);
            file = scenarioFile;
            try {
                difficulty = Integer.parseInt(scanner.nextLine());
                numberOfMines = Integer.parseInt(scanner.nextLine());
                timeLimit = Integer.parseInt(scanner.nextLine());
                hyperMineExistence = Integer.parseInt(scanner.nextLine());
                scanner.close();
                
                int mode = difficulty - 1;

                //- Invalid Value Exceptions -\\
                if (difficulty != 1 && difficulty != 2) throw(new InvalidValueException("DIFFICULTY"));
                if (numberOfMines < MIN_MINES[mode] || numberOfMines > MAX_MINES[mode]) throw(new InvalidValueException("BOMBS"));
                if (timeLimit < MIN_TIME[mode] || timeLimit > MAX_TIME[mode]) throw(new InvalidValueException("TIME"));
                if (hyperMineExistence == 1 && difficulty == 1) throw(new InvalidValueException("HYPERBOMB"));

            } catch (NumberFormatException e) {
                scanner.close();
                System.out.println("An error occured.");
                e.printStackTrace();
                throw(new InvalidDescriptionException("Invalid Description"));
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
}