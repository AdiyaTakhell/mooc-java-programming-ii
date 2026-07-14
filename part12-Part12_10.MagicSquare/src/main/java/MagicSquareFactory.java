
public class MagicSquareFactory {

    public MagicSquare createMagicSquare(int size) {

        MagicSquare square = new MagicSquare(size);

        // The Siamese method only works for odd matrix sizes
        if (size % 2 == 0) {
            return square;
        }

        // Initialize positions: x is column, y is row
        // Rule: Start at the centermost column of the top row (row 0)
        int y = 0;
        int x = size / 2;

        int maxValue = size * size;

        for (int number = 1; number <= maxValue; number++) {
            // Write to the square using the class's native helper method
            square.placeValue(x, y, number);

            // Calculate potential next position: one row up (-1) and one column right (+1)
            int nextY = (y - 1 + size) % size;
            int nextX = (x + 1) % size;

            // Check if the target cell is already occupied using readValue(x, y)
            if (square.readValue(nextX, nextY) > 0) {
                // Rule 2: If occupied, stay in the same column and drop exactly one row down
                y = (y + 1) % size;
            } else {
                // If empty, accept the diagonal transition coordinates
                y = nextY;
                x = nextX;
            }
        }

        return square;
    }
}
