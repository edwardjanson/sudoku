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

//    public static boolean playerTurn(int[][] matrixPlayer, int[][] matrixToSolve ){
//
//    }
}
