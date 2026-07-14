
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MagicSquare {

    private int[][] square;

    // ready constructor
    public MagicSquare(int size) {
        if (size < 2) {
            size = 2;
        }

        this.square = new int[size][size];
    }

    // implement these three methods
    public ArrayList<Integer> sumsOfRows() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int rows = 0; rows < square.length; rows++) {
            int sum = 0;
            for (int columns = 0; columns < square[rows].length; columns++) {
                int number = square[rows][columns];
                sum += number;
            }
            result.add(sum);
            sum = 0;

        }
        return result;
    }

    public ArrayList<Integer> sumsOfColumns() {
        ArrayList<Integer> result = new ArrayList<>();

        for (int columns = 0; columns < square.length; columns++) {
            int sum = 0;
            for (int rows = 0; rows < square[columns].length; rows++) {
                int number = square[rows][columns];
                sum += number;

            }
            result.add(sum);
            sum = 0;

        }
        return result;
    }

    public ArrayList<Integer> sumsOfDiagonals() {
        ArrayList<Integer> result = new ArrayList<>();

        int mainDiagonalSum = 0;
        int antiDiagonalSum = 0;
        int n = square.length; // Assuming a square matrix (N x N)

        for (int i = 0; i < n; i++) {
            // Main diagonal coordinates: [0][0], [1][1], [2][2]...
            mainDiagonalSum += square[i][i];

            // Anti-diagonal coordinates: [0][n-1], [1][n-2], [2][n-3]...
            antiDiagonalSum += square[i][n - 1 - i];
        }

        // Add both totals to your result array list
        result.add(mainDiagonalSum);
        result.add(antiDiagonalSum);

        return result;
    }

    // ready-made helper methods -- don't touch these
    public boolean isMagicSquare() {
        return sumsAreSame() && allNumbersDifferent();
    }

    public ArrayList<Integer> giveAllNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int row = 0; row < square.length; row++) {
            for (int col = 0; col < square[row].length; col++) {
                numbers.add(square[row][col]);
            }
        }

        return numbers;
    }

    public boolean allNumbersDifferent() {
        ArrayList<Integer> numbers = giveAllNumbers();

        Collections.sort(numbers);
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i - 1) == numbers.get(i)) {
                return false;
            }
        }

        return true;
    }

    public boolean sumsAreSame() {
        ArrayList<Integer> sums = new ArrayList<>();
        sums.addAll(sumsOfRows());
        sums.addAll(sumsOfColumns());
        sums.addAll(sumsOfDiagonals());

        if (sums.size() < 3) {
            return false;
        }

        for (int i = 1; i < sums.size(); i++) {
            if (sums.get(i - 1) != sums.get(i)) {
                return false;
            }
        }

        return true;
    }

    public int readValue(int x, int y) {
        if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
            return - 1;
        }

        return this.square[y][x];
    }

    public void placeValue(int x, int y, int value) {
        if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
            return;
        }

        this.square[y][x] = value;
    }

    public int getWidth() {
        return this.square.length;
    }

    public int getHeight() {
        return this.square.length;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < square.length; row++) {
            for (int col = 0; col < square[row].length; col++) {
                result.append(square[row][col]).append("\t");
            }

            result.append("\n");
        }

        return result.toString();
    }
}
