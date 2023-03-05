package minesweeper.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Scenario {
  // private final static int [] gridSize = {9, 16};
  private final static int [] maxBombs = {11, 45};
  private final static int [] minBombs = {9, 35};
  private final static int [] maxTime = {180, 360};
  private final static int [] minTime = {120, 240};
  private final static int [] hyperBomb = {0, 1};

  protected Integer mode;
  protected Integer numberOfBombs;
  protected Integer timeLimit;
  protected Integer hyperBombExistence;
  
  // private Integer hyperBombLocation;

  // public List<Integer> bombsList; // Make Private

  //- Scenario: Random Constructor -\\
  Scenario() {

      //- Game Mode -\\
      mode = new Random().nextInt(2);

      //- Random Number Of Bombs -\\
      // numberOfBombs = new Random().nextInt(minBombs[mode], maxBombs[mode] + 1);
      final int bombBound = maxBombs[mode] - minBombs[mode] + 1;
      numberOfBombs = new Random().nextInt(bombBound) + minBombs[mode];

      //- Random Number Of Seconds -\\
      // timeLimit = new Random().nextInt(minTime[mode], maxTime[mode] + 1);
      final int timeBound = maxTime[mode] - minTime[mode] + 1;
      timeLimit = new Random().nextInt(timeBound) + minTime[mode];
      
      // //- -\\
      hyperBombExistence = new Random().nextInt(hyperBomb[mode] + 1);
  }

  //- Scenario: Constructor From Text File -\\
  Scenario(File scenarioFile) throws InvalidScenarioException {
      try {
          Scanner scanner = new Scanner(scenarioFile);
          try {
              mode = Integer.parseInt(scanner.nextLine()) - 1;
              numberOfBombs = Integer.parseInt(scanner.nextLine());
              timeLimit = Integer.parseInt(scanner.nextLine());
              hyperBombExistence = Integer.parseInt(scanner.nextLine());
              scanner.close();
              
              //- Invalid Value Exceptions -\\
              if (mode < 0 || mode > 1) throw(new InvalidValueException("DIFFICULTY"));
              if (numberOfBombs < minBombs[mode] || numberOfBombs > maxBombs[mode]) throw(new InvalidValueException("BOMBS"));
              if (timeLimit < minTime[mode] || timeLimit > maxTime[mode]) throw(new InvalidValueException("TIME"));
              if (hyperBombExistence == 1 && mode == 0) throw(new InvalidValueException("HYPERBOMB"));

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

  //- Scenario: Difficulty -\\ 
  public Integer getDifficulty() {
      return mode + 1;
  }

  //- Scenario: Number Of Bombs -\\
  public Integer getNumberOfBombs() {
      return numberOfBombs;
  }

  //- Scenario: Time Limit -\\
  public Integer getTimeLimit() {
      return timeLimit;
  }

  //- Scenario: Hyper Bomb Existence -\\
  public Integer hasHyperBomb() {
      return hyperBombExistence;
  }
}