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

public class Game extends Scenario {
    private final static int [] gridSize = {9, 16};

    private boolean finished = false;

    private File gameId;
    private Scenario scenario;
    private Grid grid;

    public List<Integer> bombsList; // Make Private
    protected Integer hyperBombPosition;

    //- Game: Random Constructor -\\
    Game() {

        scenario = new Scenario();

        mode = scenario.mode;
        numberOfBombs = scenario.numberOfBombs;
        timeLimit = scenario.timeLimit;
        hyperBombExistence = scenario.hyperBombExistence;

        //- Number Of Tiles -\\
        final int numberOfTiles = gridSize[mode] * gridSize[mode];

        //- Random Bomb Locations -\\
        List<Integer> range = IntStream.range(0, numberOfTiles).boxed().collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(range);
        bombsList = range.subList(0, numberOfBombs);
        Collections.sort(bombsList);
        hyperBombPosition = bombsList.get(new Random().nextInt(numberOfBombs));

        //- Scenario-ID Text File -\\ 
        try {
            FileWriter myWriter = new FileWriter("SCENARIO-ID.txt");
            myWriter.write(scenario.getDifficulty().toString() + "\n" 
                    + numberOfBombs.toString() + "\n"
                    + timeLimit.toString() + "\n"
                    + hyperBombExistence.toString() + "\n");
            myWriter.write(bombsList.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();  
  
        for (Integer bomb : bombsList) {  
            builder.append(((Integer)(bomb / gridSize[mode])).toString() + ", " 
            + ((Integer)(bomb % gridSize[mode])).toString() + ", "
            + (((hyperBombExistence == 1) && (bomb == hyperBombPosition)) ? "1" : "0") + "\n");
        }

        //- Bomb Positions Text File -\\ 
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
            System.out.println(savedScenario.getDifficulty());
            System.out.println(savedScenario.getNumberOfBombs());
            System.out.println(savedScenario.getTimeLimit());
            System.out.println(savedScenario.hasHyperBomb());
        } catch (InvalidScenarioException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        
    }

    Game(File scenarioFile) {

        try {
            scenario = new Scenario(scenarioFile);
        
            mode = scenario.mode;
            numberOfBombs = scenario.numberOfBombs;
            timeLimit = scenario.timeLimit;
            hyperBombExistence = scenario.hyperBombExistence;

            //- Number Of Tiles -\\
            final int numberOfTiles = gridSize[mode] * gridSize[mode];

            //- Random Bomb Locations -\\
            List<Integer> range = IntStream.range(0, numberOfTiles).boxed().collect(Collectors.toCollection(ArrayList::new));
            Collections.shuffle(range);
            bombsList = range.subList(0, numberOfBombs);
            Collections.sort(bombsList);
            hyperBombPosition = bombsList.get(new Random().nextInt(numberOfBombs));

            //- Scenario-ID Text File -\\ 
            try {
                FileWriter myWriter = new FileWriter("SCENARIO-ID.txt");
                myWriter.write(scenario.getDifficulty().toString() + "\n" 
                        + numberOfBombs.toString() + "\n"
                        + timeLimit.toString() + "\n"
                        + hyperBombExistence.toString() + "\n");
                myWriter.write(bombsList.toString());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            StringBuilder builder = new StringBuilder();  
    
            for (Integer bomb : bombsList) {  
                builder.append(((Integer)(bomb / gridSize[mode])).toString() + ", " 
                + ((Integer)(bomb % gridSize[mode])).toString() + ", "
                + (((hyperBombExistence == 1) && (bomb == hyperBombPosition)) ? "1" : "0") + "\n");
            }

            //- Bomb Positions Text File -\\ 
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

    //- Game: Grid Scale -\\
    public Integer getGridSize() {
        return gridSize[mode];
    }

    public void finish(boolean answer) {
        finished = answer;
    }

    public boolean isFinished() {
        return finished;
    }

    //- Game: Save Current Scenario -\\
    public void saveScenario(String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(scenario.getDifficulty().toString() + "\n" 
                    + numberOfBombs.toString() + "\n"
                    + timeLimit.toString() + "\n"
                    + hyperBombExistence.toString() + "\n");
            myWriter.write(bombsList.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
}