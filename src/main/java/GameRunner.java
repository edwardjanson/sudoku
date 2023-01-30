import java.util.Arrays;

public class GameRunner {

    public static void main(String[] args) {

        int[][] matrixSolved = GameLogic.initiateGameGrid();
        int[][] matrixToSolve = GameLogic.removeNumbers(matrixSolved, 56);
        int[][] matrixPlayer = Arrays.stream(matrixToSolve).map(int[]::clone).toArray(int[][]::new);

        GameLogic.terminalOutput(matrixPlayer, matrixToSolve);

    }
}
