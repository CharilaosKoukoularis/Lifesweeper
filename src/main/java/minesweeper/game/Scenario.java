package minesweeper.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Scenario {

    private final static int [] maxMines = {11, 45};
    private final static int [] minMines = {9, 35};
    private final static int [] maxTime = {180, 360};
    private final static int [] minTime = {120, 240};
    private final static int [] hyperMine = {0, 1};

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
                        // numberOfMines = new Random().nextInt(minMines[mode], maxMines[mode] + 1);
                        final int mineBound = maxMines[mode] - minMines[mode] + 1;
                        numberOfMines = new Random().nextInt(mineBound) + minMines[mode];
                
                        //- Random Number Of Seconds -\\
                        // timeLimit = new Random().nextInt(minTime[mode], maxTime[mode] + 1);
                        final int timeBound = maxTime[mode] - minTime[mode] + 1;
                        timeLimit = new Random().nextInt(timeBound) + minTime[mode];
                        
                        // //- -\\
                        hyperMineExistence = new Random().nextInt(hyperMine[mode] + 1);
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
                if (numberOfMines < minMines[mode] || numberOfMines > maxMines[mode]) throw(new InvalidValueException("BOMBS"));
                if (timeLimit < minTime[mode] || timeLimit > maxTime[mode]) throw(new InvalidValueException("TIME"));
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