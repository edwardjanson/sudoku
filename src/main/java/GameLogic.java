import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameLogic {

    public static void terminalOutput(int matrix[][]) {
        int rowCount = 0;
        String[] split = {"_", "_", "_", "_", "_", "_", "_", "_", "_", "_", "_"};
        for (int [] row : matrix) {
            String[] line = {" ", " ", " ", "|", " ", " ", " ", "|", " ", " ", " "};
            int lineIndex = 0;
            for (int i = 0; i < line.length ; i++) {
                if (line[i] != "|"){
                    line[i] = Integer.toString(row[lineIndex]);
                    lineIndex++;
                }
            }
            if (rowCount % 3 == 0 && rowCount != 0) {
                System.out.println(Arrays.toString(split));
            }
            System.out.println(Arrays.toString(line));
            rowCount += 1;
        }
    }

    public static int[][] initiateGame() {
        int[][] matrix = new int[9][9];
        return matrix;
    }

    public static ArrayList<Integer> checkBox(int[][] matrix, int[] boxRange) {
        ArrayList<Integer> availableNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        for (int row = boxRange[0] - 3; row < boxRange[0]; row++) {
            for (int column = boxRange[1] - 3; column < boxRange[1]; column++) {
                if (matrix[row][column] != 0) {
                    availableNumbers.remove((Integer) matrix[row][column]);
                }
            }
        }
        return availableNumbers;
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
        ArrayList<Integer> availableNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        for (int column = 0; column < 9; column++) {
                if(matrix[row][column] != 0) {
                    availableNumbers.remove((Integer) matrix[row][column]);
                }
            }

        return availableNumbers;

    }

    public static ArrayList<Integer> checkColumn(int row, int column) {
        ArrayList<Integer> array = new ArrayList<>();
        return array;
    }

    public static ArrayList<Integer> possibleNumbers() {
        ArrayList<Integer> array = new ArrayList<>();
        return array;
    }

    public static int randomNumber() {
        return 0;
    }
}
