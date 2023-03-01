import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GameLogic {

    public static void terminalOutput(int[][] matrixPlayer, int[][] matrixToSolve) {
        String blue = "\u001B[34m";
        String colourReset = "\u001B[0m";

        String[] columnIndex = {"1", "2", "3", " ", "4", "5", "6", " ", "7", "8", "9"};
        System.out.println("    " + Arrays.toString(columnIndex)
                .replace(",", "")
                .replace("[", "")
                .replace("]", ""));

        System.out.println();

        String[] split = {"–", "–", "–", "–", "–", "–", "–", "–", "–", "–", "–"};

        String[] rowLetters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};

        int rowCount = 0;
        int columnCount = 0;

        for (int[] row : matrixPlayer) {
            String[] line = {" ", " ", " ", "|", " ", " ", " ", "|", " ", " ", " "};
            int lineIndex = 0;
            for (int i = 0; i < line.length ; i++) {
                if (line[i] != "|"){
                    line[i] = Integer.toString(row[lineIndex]);
                    lineIndex++;
                }
            }

            if (rowCount % 3 == 0 && rowCount != 0) {
                System.out.println("    " + Arrays.toString(split)
                                        .replace(",", "")
                                        .replace("[", "")
                                        .replace("]", ""));
            }

            for (int column = 0; column < 12; column++) {
                if (column == 0) {
                    System.out.print(rowLetters[rowCount] + "   ");
                } else if (column % 4 == 0) {
                    System.out.print("| ");
                } else {
                    if (matrixPlayer[rowCount][columnCount] == 0) {
                        System.out.print("  ");
                    } else if (matrixPlayer[rowCount][columnCount] != 0 && matrixToSolve[rowCount][columnCount] == 0) {
                        System.out.print(matrixPlayer[rowCount][columnCount] + " ");
                    } else {
                        System.out.print(blue + matrixPlayer[rowCount][columnCount] + " " + colourReset);
                    }
                    columnCount += 1;
                }
            }

            columnCount = 0;
            rowCount += 1;

            System.out.println();
        }
    }

    public static int[][] initiateGameGrid() {
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

    public static ArrayList<Integer> checkBox(int[][] matrix, int[] boxRange) {
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

    public static int[] boxRange(int numberRow, int numberColumn) {
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

    public static ArrayList<Integer> checkRow(int[][] matrix, int row) {
        ArrayList<Integer> usedNumbers = new ArrayList<>();

        for (int column = 0; column < 9; column++) {
                if(matrix[row][column] != 0) {
                    usedNumbers.add(matrix[row][column]);
                }
            }

        return usedNumbers;

    }

    public static ArrayList<Integer> checkColumn(int[][] matrix, int column) {
        ArrayList<Integer> usedNumbers = new ArrayList<>();

        for (int row = 0; row < 9; row++) {
            if(matrix[row][column] != 0) {
                usedNumbers.add(matrix[row][column]);
            }
        }

        return usedNumbers;
    }

    public static List<Integer> possibleNumbers(int[][] matrix, int row, int column) {
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

    public static int randomNumber(List<Integer> possibleNumbers) {
        Random rand = new Random();
        if (possibleNumbers.size() != 0 ) {
            return possibleNumbers.get(rand.nextInt(possibleNumbers.size()));
        }

        return 0;
    }

    public static int[] gridLocation(String userInput) {
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

    public static int[][] removeNumbers(int[][] matrix, int numberCount) {
        int[][] removedMatrix = new int[9][9];
        removedMatrix = matrix;
        Random rand = new Random();
        for (int i = 0; i < numberCount; i++) {
            int randomRowNumber = rand.nextInt(9);
            int randomColumnNumber = rand.nextInt(9);
            if(matrix[randomRowNumber][randomColumnNumber] == 0) {
                i--;
                continue;
            }
            removedMatrix[randomRowNumber][randomColumnNumber] = 0;
        }

        return removedMatrix;
    }

    public static boolean updateMatrix(int[][] matrixPlayer, int[][] matrixToSolve, int[] gridLocation, int userInput) {
        if (matrixToSolve[gridLocation[0]][gridLocation[1]] != 0) {
            return false;
        } else if (gridLocation[0] != -1 || gridLocation[1] != -1) {
            matrixPlayer[gridLocation[0]][gridLocation[1]] = userInput;
            return true;
        }
        return false;
    }

}
