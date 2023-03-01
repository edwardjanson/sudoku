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

//    public static boolean playerTurn(int[][] matrixPlayer, int[][] matrixToSolve ){
//
//    }
}
