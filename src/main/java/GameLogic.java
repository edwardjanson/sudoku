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

    public static ArrayList<Integer> checkBox(int matrix[][], int[] boxRange) {
//        ArrayList<Integer> pickedNumbers = new ArrayList<>();
//
//        for (int [] row : matrix) {
//            for (int element : row) {
//            }
//        }

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

    public static ArrayList<Integer> checkRow(int row, int column) {

    }

    public static ArrayList<Integer> checkColumn(int row, int column) {

    }

    public static ArrayList<Integer> possibleNumbers() {

    }

    public static int randomNumber {

    }
}
