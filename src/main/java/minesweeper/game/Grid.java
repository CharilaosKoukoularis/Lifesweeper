package minesweeper.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Grid extends Pane {
    
    // private double gridWidth;
    protected double cellWidth;
    protected Integer cellsOpened = 0;    
    protected Integer cellsMarked = 0;
    protected Integer markedMines = 0;
    protected IntegerProperty markedCells = new SimpleIntegerProperty();
    protected double width;//private
    protected int size;//private

    public Cell [][] cell;//private
    public Integer [][] neighborMatrix;

    protected Game game;


    Grid(Game gameName, double gridWidth) {

        game = gameName;

        width = gridWidth;
        size = Game.gridSize[game.scenario.difficulty - 1];
        cell = new Cell [size][size];
        cellWidth = width / size;
        // Pane pane = new Pane();
        int mineCounter = 0;

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                cell[i][j] = new Cell(cellWidth);
                cell[i][j].setX(i * cellWidth);
                cell[i][j].setY(j * cellWidth);
                cell[i][j].hint = new Text(i * cellWidth + cellWidth / 2, j * cellWidth + cellWidth / 2, null); 

                if (mineCounter < game.scenario.numberOfMines) {
                    if ((j * size + i) == game.minesList.get(mineCounter)) {
                        cell[i][j].content = Cell.MINE;
                        mineCounter++;
                        // cell[i][j].setFill(Color.BLACK);
                    }
                }
                getChildren().add(cell[i][j]);  
            }
        }

        int [][] temporaryMatrix = new int [size + 2][size + 2];
        for (int i = 0; i < size + 2; i++) {
            for (int j = 0; j < size + 2; j++) {
                temporaryMatrix[i][j] = 0;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temporaryMatrix[i + 1][j + 1] = cell[i][j].content;//(1 - statusMatrix[i][j]) / 2;
            }
        }

        neighborMatrix = new Integer [size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                neighborMatrix[i][j] = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {  
                        neighborMatrix[i][j] += temporaryMatrix[i + k][j + l];
                    }
                }
                Text hint;
                if (neighborMatrix[i][j] == 0) {
                    hint = new Text();//Text(cell[i][j].getX() + cell[i][j].getWidth() / 2, cell[i][j].getY() + cell[i][j].getHeight() / 2, "0");
                } else {
                    hint = new Text(cell[i][j].getX() + cell[i][j].getWidth() / 2, cell[i][j].getY() + cell[i][j].getHeight() / 2, neighborMatrix[i][j].toString());
                    if (neighborMatrix[i][j] == 1) {
                        hint.fillProperty().set(Color.BLUE);
                    } else if (neighborMatrix[i][j] == 2) {
                        hint.fillProperty().set(Color.GREEN);
                    } else if (neighborMatrix[i][j] == 3) {
                        hint.fillProperty().set(Color.PURPLE);
                    } else if (neighborMatrix[i][j] >= 4) {
                        hint.fillProperty().set(Color.RED);
                    }
                }
                hint.fontProperty().set(Font.font(20.0));
                cell[i][j].hint = hint;
                // this.getChildren().add(hint);
            }
        }
    }

    // public void notifyNeighbors() {

    // }

    public void increaseMarked(int increment) {
        cellsMarked = cellsMarked + increment;
        markedCells = new SimpleIntegerProperty(cellsMarked);
        markedCells.set(cellsMarked);
    }

    public void diffuseHyperMine() {

        Integer x = game.hyperMinePosition % size;
        Integer y = game.hyperMinePosition / size;

        for (int i = 0; i < size; i++) {
            if (cell[x][i].status != Cell.OPENED) {
                if (cell[x][i].content == Cell.MINE) {

                    if (cell[x][i].getFill() != Color.ORANGE) {
                        cell[x][i].setFill(Color.BLACK);
                    }
                } else if (cell[x][i].content == Cell.EMPTY) {
                    
                    if (cell[x][i].getFill() == Color.ORANGE) {
                        increaseMarked(-1);
                    }
                    cell[x][i].setFill(null);
                    cellsOpened++;
                }
                cell[x][i].status = Cell.OPENED;
                getChildren().add(cell[x][i].hint);
            }
            if (cell[i][y].status != Cell.OPENED) {
                if (cell[i][y].content == Cell.MINE) {

                    if (cell[i][y].getFill() != Color.ORANGE) {
                        cell[i][y].setFill(Color.BLACK);
                    }
                } else if (cell[i][y].content == Cell.EMPTY) {
                    
                    if (cell[i][y].getFill() == Color.ORANGE) {
                        increaseMarked(-1);
                    }
                    cell[i][y].setFill(null);
                    cellsOpened++;
                }
                cell[i][y].status = Cell.OPENED;
                getChildren().add(cell[i][y].hint);
            }
        }
    } 

    public void openAdjacent(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) return;
        if (cell[x][y].status == Cell.OPENED) return;

        if (cell[x][y].getFill() == Color.ORANGE) {
            increaseMarked(-1);
        }

        cell[x][y].setFill(null);
        cell[x][y].status = Cell.OPENED;
        cellsOpened++;
        getChildren().add(cell[x][y].hint);
        
        if (neighborMatrix[x][y] == 0) {
            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                    openAdjacent(x + k, y + l);
                }
            }
        }
    } 

    public void revealAll() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cell[i][j].status != Cell.OPENED) {
                    if (cell[i][j].content == Cell.EMPTY) {
                        // cell[i][j].setFill(null);
                        // this.getChildren().add(cell[i][j].getHint());
                        cell[i][j].setFill(Color.GRAY);
                        cell[i][j].status = Cell.OPENED ;
                        
                    } else if (cell[i][j].content == Cell.MINE){
                        if (cell[i][j].getFill() != Color.ORANGE) cell[i][j].setFill(Color.BLACK);
                        cell[i][j].status = Cell.OPENED;
                    }       
                }
            }
        }
        game.finished = true;
    }
}