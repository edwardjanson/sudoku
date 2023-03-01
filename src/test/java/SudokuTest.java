import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SudokuTest {

        int[][] matrix;
        int[] range;
        Sudoku sudoku;

        @Before
        public void before() {
            matrix = new int[9][9];
            range = new int[2];
            range[0] = 3;
            range[1] = 6;
            sudoku = new Sudoku();
        }

        @Test
        public void canCheckBox() {
            matrix[0][3] = 3;
            matrix[0][4] = 6;
            List<Integer> testNumbers = List.of(3,6);
            assertEquals(testNumbers.toString(), sudoku.checkBox(matrix, range).toString());
        }

        @Test
        public void canGetBoxRange() {
            assertArrayEquals(range, sudoku.boxRange(0, 3));
        }

        @Test
        public void canCheckRow() {
            matrix[0][3] = 3;
            matrix[0][4] = 6;
            List<Integer> testNumbers = List.of(3,6);
            assertEquals(testNumbers.toString(), sudoku.checkRow(matrix, 0).toString());
        }

        @Test
        public void canCheckColumn() {
            matrix[0][3] = 3;
            matrix[1][3] = 6;
            List<Integer> testNumbers = List.of(3,6);
            assertEquals(testNumbers.toString(), sudoku.checkColumn(matrix, 3).toString());
        }

        @Test
        public void canFindPossibleNumbers() {
            matrix[2][2] = 3;
            matrix[4][0] = 5;
            matrix[8][0] = 4;
            matrix[0][3] = 7;
            matrix[0][7] = 8;
            List<Integer> testNumbers = List.of(1,2,6,9);
            assertEquals(testNumbers.toString(), sudoku.possibleNumbers(matrix,0,0).toString());
        }

        @Test
        public void canGetRandomNumber() {
            matrix[2][2] = 1;
            matrix[4][0] = 2;
            matrix[8][0] = 3;
            matrix[0][3] = 4;
            matrix[0][1] = 5;

            int testNum = sudoku.randomNumber(sudoku.possibleNumbers(matrix,0,0));

            assertTrue(testNum >= 6 && testNum <= 9);
        }

        @Test
        public void canGetGridLocation() {
            String testLocation = "b3";
            int[] testGridLocation = new int[2];
            testGridLocation[0] = 1;
            testGridLocation[1] = 2;
            assertArrayEquals(testGridLocation, sudoku.gridLocation(testLocation));
        }

        @Test
        public void returnsErrorRangeIfWrongInput() {
            String testLocation = "K10";
            int[] testGridLocation = new int[2];
            testGridLocation[0] = -1;
            testGridLocation[1] = -1;
            assertArrayEquals(testGridLocation, sudoku.gridLocation(testLocation));
        }

        @Test
        public void matrixHasUniqueValues(){
            int[][] matrix= sudoku.initiateGameGrid();
            for (int[] row: matrix){
                assertEquals(9, Arrays.stream(row).distinct().count() );
            }
        }

        @Test
        public void canRemoveMatrixNumbers(){
            int[][] testMatrix = sudoku.initiateGameGrid();
            int[][] testRemovedMatrix = sudoku.removeNumbers(40);
            int zeroCounter= 0;
            for (int[] row : testRemovedMatrix) {
                for(int number : row){
                    if(number == 0){
                        zeroCounter++;
                    }
                }
            }
            assertTrue(zeroCounter == 40);
        }

        @Test
        public void canUpdatePlayerMatrix(){
            Sudoku emptySudoku = new Sudoku();

            int[][] testMatrixUnsolved = new int[9][9];
            int[][] testMatrixPlayer = new int[9][9];

            testMatrixUnsolved[0][1] = 5;
            testMatrixPlayer[0][1] = 5;

            emptySudoku.setUnsolvedSudoku(testMatrixUnsolved);
            emptySudoku.setPlayerSudoku(testMatrixPlayer);

            String testLocation = "b3";
            int[] testGridLocation = emptySudoku.gridLocation(testLocation);

            String testLocation2 = "a2";
            int[] testGridLocation2 = emptySudoku.gridLocation(testLocation2);

            emptySudoku.updateMatrix(testGridLocation, 6);
            emptySudoku.updateMatrix(testGridLocation2, 3);

            GameLogic.terminalOutput(testMatrixPlayer, testMatrixUnsolved);

            assertTrue(testMatrixPlayer[1][2] == 6);
            assertTrue(testMatrixPlayer[0][1] == 5);
        }
}
