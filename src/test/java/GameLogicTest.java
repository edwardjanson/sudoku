import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GameLogicTest {

    @Before
    public void before() {
    }

    @Test
    public void canGetGridLocation() {
        String testLocation = "b3";
        int[] testGridLocation = new int[2];
        testGridLocation[0] = 1;
        testGridLocation[1] = 2;
        assertArrayEquals(testGridLocation, GameLogic.gridLocation(testLocation));
    }

    @Test
    public void returnsErrorRangeIfWrongInput() {
        String testLocation = "K10";
        int[] testGridLocation = new int[2];
        testGridLocation[0] = -1;
        testGridLocation[1] = -1;
        assertArrayEquals(testGridLocation, GameLogic.gridLocation(testLocation));
    }
}