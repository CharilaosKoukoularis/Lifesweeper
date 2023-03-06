package minesweeper.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Grid extends Pane {
    
    // private double gridWidth;
    private double cellWidth;
    private Integer opened = 0;    
    private Integer marked = 0;
    private IntegerProperty markedCells = new SimpleIntegerProperty();
    public int n;//private

    public Cell [][] gridCell;//private
    public Integer [][] neighborMatrix;

    public Game game;

    Grid(Game game, double gridWidth) {

        this.game = game;
        // final int 
        n = game.getGridSize();//gridSize[difficulty];

        cellWidth = gridWidth / game.getGridSize();
        
        // Cell [][] 
        gridCell = new Cell [n][n];
        
        // Pane pane = new Pane();
        int bombCounter = 0;

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                gridCell[i][j] = new Cell(cellWidth);
                gridCell[i][j].setX(i * cellWidth);
                gridCell[i][j].setY(j * cellWidth);
                gridCell[i][j].setHint(new Text(i * cellWidth + cellWidth / 2, j * cellWidth + cellWidth / 2, null)); 

                if (bombCounter < game.getNumberOfBombs()) {
                    if ((j * n + i) == game.bombsList.get(bombCounter)) {
                        gridCell[i][j].setStatus(1);
                        bombCounter++;
                        // gridCell[i][j].setFill(Color.BLACK);
                    }
                }
                getChildren().add(gridCell[i][j]);  
            }
        }

        int [][] temporaryMatrix = new int [n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                temporaryMatrix[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temporaryMatrix[i + 1][j + 1] = gridCell[i][j].getStatus();//(1 - statusMatrix[i][j]) / 2;
            }
        }

        neighborMatrix = new Integer [n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                neighborMatrix[i][j] = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {  
                        neighborMatrix[i][j] += temporaryMatrix[i + k][j + l];
                    }
                }
                Text hint;
                if (neighborMatrix[i][j] == 0) {
                    hint = new Text();//Text(gridCell[i][j].getX() + gridCell[i][j].getWidth() / 2, gridCell[i][j].getY() + gridCell[i][j].getHeight() / 2, "0");
                } else {
                    hint = new Text(gridCell[i][j].getX() + gridCell[i][j].getWidth() / 2, gridCell[i][j].getY() + gridCell[i][j].getHeight() / 2, neighborMatrix[i][j].toString());
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
                gridCell[i][j].setHint(hint);
                // this.getChildren().add(hint);
            }
        }
    }

    public void increaseMarked(int increment) {
        marked = marked + increment;
        markedCells = new SimpleIntegerProperty(marked);
        markedCells.set(marked);
    }

    public double getCellWidth() {
        return cellWidth;
    }

    public Integer getOpened() {
        return opened;
    }

    public Integer getMarked() {
        return marked;
    }

    public IntegerProperty getMarkedCells() {
        return markedCells;
    }

    public void openAdjacent(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= n) return;
        if (gridCell[x][y].getStatus() == Cell.OPENED) return;

        if (gridCell[x][y].getFill() == Color.ORANGE){
            increaseMarked(-1);
        }

        gridCell[x][y].setFill(null);
        gridCell[x][y].setStatus(Cell.OPENED);
        opened++;
        getChildren().add(gridCell[x][y].getHint());
        
        if (neighborMatrix[x][y] == 0) {
            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                    openAdjacent(x + k, y + l);
                }
            }
        }
    } 

    public void revealAll() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (gridCell[i][j].getStatus() == Cell.HIDDENEMPTY) {
                    // gridCell[i][j].setFill(null);
                    gridCell[i][j].setFill(Color.GRAY);
                    gridCell[i][j].setStatus(Cell.OPENED);
                    
                    // this.getChildren().add(gridCell[i][j].getHint());
                } else if (gridCell[i][j].getStatus() == Cell.HIDDENBOMB){
                    if (gridCell[i][j].getFill() != Color.ORANGE) gridCell[i][j].setFill(Color.BLACK);
                    gridCell[i][j].setStatus(Cell.OPENED);
                }       
            }
        }
        game.finish(true);
    }
}