package minesweeper.game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {

    protected final static int [] gridSize = {9, 16};

    protected final static int FAILURE = -1;
    protected final static int ONGOING = 0;
    protected final static int SUCCESS = 1;

    protected Integer status = ONGOING;

    protected Scenario scenario;

    protected List<Integer> minesList;
    protected Integer hyperMinePosition;


    Game() {

        scenario = new Scenario();

        int mode = scenario.difficulty - 1;

        //- Number Of Tiles -\\
        final int numberOfTiles = gridSize[mode] * gridSize[mode];

        //- Random Mine Locations -\\
        List<Integer> range = IntStream.range(0, numberOfTiles).boxed().collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(range);
        minesList = range.subList(0, scenario.numberOfMines);
        Collections.sort(minesList);
        hyperMinePosition = minesList.get(new Random().nextInt(scenario.numberOfMines));

        //- Scenario-ID Text File -\\ 
        try {
            FileWriter myWriter = new FileWriter("SCENARIO-ID.txt");
            myWriter.write(scenario.difficulty.toString() + "\n" 
                    + scenario.numberOfMines.toString() + "\n"
                    + scenario.timeLimit.toString() + "\n"
                    + scenario.hyperMineExistence.toString() + "\n");
            myWriter.write(minesList.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();  
  
        for (Integer mine : minesList) {  
            builder.append(((Integer)(mine / gridSize[mode])).toString() + ", " 
            + ((Integer)(mine % gridSize[mode])).toString() + ", "
            + (((scenario.hyperMineExistence == 1) && (mine == hyperMinePosition)) ? "1" : "0") + "\n");
        }

        //- Mine Positions Text File -\\ 
        try {
            FileWriter myWriter = new FileWriter("mines.txt");
            myWriter.write(builder.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        File scenarioFile;
        scenarioFile = new File("SCENARIO-ID.txt");
        Scenario savedScenario;
        try {
            savedScenario = new Scenario(scenarioFile);
            System.out.println(savedScenario.difficulty);
            System.out.println(savedScenario.numberOfMines);
            System.out.println(savedScenario.timeLimit);
            System.out.println(savedScenario.hyperMineExistence);
        } catch (InvalidScenarioException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        
    }


    Game(File scenarioFile) {

        try {
            scenario = new Scenario(scenarioFile);
            int mode = scenario.difficulty - 1;

            //- Number Of Tiles -\\
            final int numberOfTiles = gridSize[mode] * gridSize[mode];

            //- Random Mine Locations -\\
            List<Integer> range = IntStream.range(0, numberOfTiles).boxed().collect(Collectors.toCollection(ArrayList::new));
            Collections.shuffle(range);
            minesList = range.subList(0, scenario.numberOfMines);
            Collections.sort(minesList);

            //- Random HyperMine Location -\\
            hyperMinePosition = minesList.get(new Random().nextInt(scenario.numberOfMines));

            //- Scenario-ID Text File -\\ 
            try {
                FileWriter myWriter = new FileWriter("SCENARIO-ID.txt");
                myWriter.write(scenario.difficulty.toString() + "\n" 
                        + scenario.numberOfMines.toString() + "\n"
                        + scenario.timeLimit.toString() + "\n"
                        + scenario.hyperMineExistence.toString() + "\n");
                myWriter.write(minesList.toString());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            StringBuilder builder = new StringBuilder();  
    
            for (Integer mine : minesList) {  
                builder.append(((Integer)(mine / gridSize[mode])).toString() + ", " 
                + ((Integer)(mine % gridSize[mode])).toString() + ", "
                + (((scenario.hyperMineExistence == 1) && (mine == hyperMinePosition)) ? "1" : "0") + "\n");
            }

            //- Mine Positions Text File -\\ 
            try {
                FileWriter myWriter = new FileWriter("mines.txt");
                myWriter.write(builder.toString());
                myWriter.close();

                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (InvalidScenarioException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // //- Game: Save Current Scenario -\\
    // public void saveScenario(String filename) {
    //     try {
    //         FileWriter myWriter = new FileWriter(filename);
    //         myWriter.write(scenario.getDifficulty().toString() + "\n" 
    //                 + numberOfMines.toString() + "\n"
    //                 + timeLimit.toString() + "\n"
    //                 + hyperMineExistence.toString() + "\n");
    //         myWriter.write(minesList.toString());
    //         myWriter.close();
    //         System.out.println("Successfully wrote to the file.");
    //     } catch (IOException e) {
    //         System.out.println("An error occurred.");
    //         e.printStackTrace();
    //     }
    // }
    
}