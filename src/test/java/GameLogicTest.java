import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GameLogicTest {

    int[][] matrix;
    int[] range;

    @Before
    public void before() {
        matrix = new int[9][9];
        range = new int[2];
        range[0] = 3;
        range[1] = 6;
    }

    @Test
    public void checkBox() {
        matrix[0][3] = 3;
        matrix[0][4] = 6;
        List<Integer> testNumbers = List.of(3,6);
        assertEquals(testNumbers.toString(), GameLogic.checkBox(matrix, range).toString());
    }

    @Test
    public void boxRange() {
        assertArrayEquals(range, GameLogic.boxRange(0, 3));
    }

    @Test
    public void checkRow() {
        matrix[0][3] = 3;
        matrix[0][4] = 6;
        List<Integer> testNumbers = List.of(3,6);
        assertEquals(testNumbers.toString(), GameLogic.checkRow(matrix, 0).toString());
    }

    @Test
    public void checkColumn() {
        matrix[0][3] = 3;
        matrix[1][3] = 6;
        List<Integer> testNumbers = List.of(3,6);
        assertEquals(testNumbers.toString(), GameLogic.checkColumn(matrix, 3).toString());
    }

    @Test
    public void possibleNumbers() {
        matrix[2][2] = 3;
        matrix[4][0] = 5;
        matrix[8][0] = 4;
        matrix[0][3] = 7;
        matrix[0][7] = 8;
        List<Integer> testNumbers = List.of(1,2,6,9);
        assertEquals(testNumbers.toString(), GameLogic.possibleNumbers(matrix,0,0).toString());
    }

    @Test
    public void randomNumber() {
    }
}