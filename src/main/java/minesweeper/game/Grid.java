package minesweeper.game;

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
    protected double width;
    protected int size;

    public Cell [][] cell;
    public Integer [][] neighborMatrix;

    protected Game game;


    Grid(Game gameName, double gridWidth) {

        game = gameName;

        width = gridWidth;
        size = Game.gridSize[game.scenario.difficulty - 1];
        cell = new Cell [size][size];
        cellWidth = width / size;

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
        cell[game.hyperMinePosition % size][game.hyperMinePosition / size].hyperMine = true;

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
                if (neighborMatrix[i][j] != 0) {
                    cell[i][j].hint.setText(neighborMatrix[i][j].toString());
                    if (neighborMatrix[i][j] == 1) {
                        cell[i][j].hint.fillProperty().set(Color.BLUE);
                    } else if (neighborMatrix[i][j] == 2) {
                        cell[i][j].hint.fillProperty().set(Color.GREEN);
                    } else if (neighborMatrix[i][j] == 3) {
                        cell[i][j].hint.fillProperty().set(Color.PURPLE);
                    } else if (neighborMatrix[i][j] >= 4) {
                        cell[i][j].hint.fillProperty().set(Color.RED);
                    }
                }
                cell[i][j].hint.fontProperty().set(Font.font(20.0));
            }
        }
    }

    public void defuseHyperMine() {

        Integer x = game.hyperMinePosition % size;
        Integer y = game.hyperMinePosition / size;

        for (int i = 0; i < size; i++) {
            defuseCell(x, i);
            defuseCell(i, y);
        }
    } 

    public void defuseCell(int x, int y) {

        if (cell[x][y].status != Cell.OPENED) {

            if (cell[x][y].content == Cell.MINE) {
                if (cell[x][y].getFill() != Color.ORANGE) {
                    cell[x][y].setFill(Color.BLACK);
                }
            } else if (cell[x][y].content == Cell.EMPTY) {
                
                if (cell[x][y].getFill() == Color.ORANGE) {
                    cellsMarked--;
                }
                cell[x][y].setFill(null);
                cellsOpened++;
            }
            cell[x][y].status = Cell.OPENED;
            if (cell[x][y].content == Cell.EMPTY) {
                getChildren().add(cell[x][y].hint);
            }
        }
        
    }

    public void openAdjacent(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) return;
        if (cell[x][y].status == Cell.OPENED) return;

        if (cell[x][y].getFill() == Color.ORANGE) {
            cellsMarked--;
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

    public void revealAll(int result) {
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
        game.status = result;
    }
}