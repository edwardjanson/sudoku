import java.util.Arrays;

public class GameRunner {

    public static void main(String[] args) {

        Sudoku sudoku = new Sudoku();

        GameLogic.terminalOutput(sudoku.getPlayerSudoku(), sudoku.getUnsolvedSudoku());
    }
}
