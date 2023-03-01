import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Sudoku {
    private int[][] solvedSudoku;
    private int[][] unsolvedSudoku;
    private int[][] playerSudoku;

    public Sudoku() {
        this.solvedSudoku = initiateGameGrid();
        this.unsolvedSudoku = removeNumbers(56);
        this.playerSudoku = Arrays.stream(unsolvedSudoku).map(int[]::clone).toArray(int[][]::new);
    }


    public int[][] initiateGameGrid() {
        int[][] matrix = new int[9][9];

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                List<Integer> possibleNumbers = possibleNumbers(matrix, row, column);

                int randomNumber = randomNumber(possibleNumbers);
                if (randomNumber == 0) {
                    int[] reinitialise = {0, 0, 0, 0, 0, 0, 0, 0, 0};
                    matrix[row] = reinitialise;
                    column = -1;
                } else {
                    matrix[row][column] = randomNumber;
                }
            }
        }

        return matrix;
    }

    public List<Integer> possibleNumbers(int[][] matrix, int row, int column) {
        ArrayList<Integer> availableNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        ArrayList<Integer> columnNumbers = checkColumn(matrix, column);
        ArrayList<Integer> rowNumbers = checkRow(matrix, row);
        int[] boxRange = boxRange(row, column);
        ArrayList<Integer> boxNumbers = checkBox(matrix, boxRange);

        ArrayList<ArrayList<Integer>> combinedNumbers = new ArrayList<>(Arrays.asList(columnNumbers, rowNumbers, boxNumbers));
        List<Integer> finalNumbers = combinedNumbers.stream().flatMap(List::stream).distinct().toList();

        for (int number : finalNumbers) {
            availableNumbers.remove((Integer) number);
        }

        return availableNumbers;
    }

    public ArrayList<Integer> checkBox(int[][] matrix, int[] boxRange) {
        ArrayList<Integer> usedNumbers = new ArrayList<>();

        for (int row = boxRange[0] - 3; row < boxRange[0]; row++) {
            for (int column = boxRange[1] - 3; column < boxRange[1]; column++) {
                if (matrix[row][column] != 0) {
                    usedNumbers.add(matrix[row][column]);
                }
            }
        }

        return usedNumbers;
    }

    public int[] boxRange(int numberRow, int numberColumn) {
        int[] boxRange = new int[2];
        if (numberRow < 3) {
            boxRange[0] = 3;
        } else if (numberRow < 6) {
            boxRange[0] = 6;
        } else {
            boxRange[0] = 9;
        }

        if (numberColumn < 3) {
            boxRange[1] = 3;
        } else if (numberColumn < 6) {
            boxRange[1] = 6;
        } else {
            boxRange[1] = 9;
        }

        return boxRange;
    }

    public ArrayList<Integer> checkRow(int[][] matrix, int row) {
        ArrayList<Integer> usedNumbers = new ArrayList<>();

        for (int column = 0; column < 9; column++) {
            if(matrix[row][column] != 0) {
                usedNumbers.add(matrix[row][column]);
            }
        }

        return usedNumbers;

    }

    public ArrayList<Integer> checkColumn(int[][] matrix, int column) {
        ArrayList<Integer> usedNumbers = new ArrayList<>();

        for (int row = 0; row < 9; row++) {
            if(matrix[row][column] != 0) {
                usedNumbers.add(matrix[row][column]);
            }
        }

        return usedNumbers;
    }

    public int randomNumber(List<Integer> possibleNumbers) {
        Random rand = new Random();
        if (possibleNumbers.size() != 0 ) {
            return possibleNumbers.get(rand.nextInt(possibleNumbers.size()));
        }

        return 0;
    }

    public int[] gridLocation(String userInput) {
        int[] gridLocation = new int[2];
        char[] input = userInput.toUpperCase().toCharArray();

        int tempRow = (int) input[0];
        if (tempRow >= 65 && tempRow <= 73) {
            gridLocation[0] = tempRow - 65;
        } else {
            gridLocation[0] = -1;
            gridLocation[1] = -1;
            return gridLocation;
        }

        int tempColumn = Character.getNumericValue(input[1]);
        if (tempColumn >= 1 &&  tempColumn <= 9) {
            gridLocation[1] = tempColumn - 1;
        } else {
            gridLocation[0] = -1;
            gridLocation[1] = -1;
            return gridLocation;
        }

        return gridLocation;
    }

    public int[][] removeNumbers(int numberCount) {
        int[][] removedMatrix = new int[9][9];
        removedMatrix = this.solvedSudoku;
        Random rand = new Random();
        for (int i = 0; i < numberCount; i++) {
            int randomRowNumber = rand.nextInt(9);
            int randomColumnNumber = rand.nextInt(9);
            if(this.solvedSudoku[randomRowNumber][randomColumnNumber] == 0) {
                i--;
                continue;
            }
            removedMatrix[randomRowNumber][randomColumnNumber] = 0;
        }

        return removedMatrix;
    }

    public boolean updateMatrix(int[] gridLocation, int userInput) {
        if (this.unsolvedSudoku[gridLocation[0]][gridLocation[1]] != 0) {
            return false;
        } else if (gridLocation[0] != -1 || gridLocation[1] != -1) {
            this.playerSudoku[gridLocation[0]][gridLocation[1]] = userInput;
            return true;
        }
        return false;
    }

    public int[][] getSolvedSudoku() {
        return solvedSudoku;
    }

    public int[][] getUnsolvedSudoku() {
        return unsolvedSudoku;
    }

    public int[][] getPlayerSudoku() {
        return playerSudoku;
    }

    public void setSolvedSudoku(int[][] solvedSudoku) {
        this.solvedSudoku = solvedSudoku;
    }

    public void setUnsolvedSudoku(int[][] unsolvedSudoku) {
        this.unsolvedSudoku = unsolvedSudoku;
    }

    public void setPlayerSudoku(int[][] playerSudoku) {
        this.playerSudoku = playerSudoku;
    }
}
