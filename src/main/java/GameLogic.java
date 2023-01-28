import java.util.Arrays;

public class GameLogic {

    public static void terminalOutput(int matrix[][]) {
        for (int [] row : matrix) {
            String[] line = {" ", " ", " ", "|", " ", " ", " ", "|", " ", " ", " "};
            int lineIndex = 0;
            for (int i = 0; i < line.length ; i++) {
                if (line[i] != "|"){
                    line[i] = Integer.toString(row[lineIndex]);
                    lineIndex++;
                }
            }
            System.out.println(Arrays.toString(line));
        }
    }
}
