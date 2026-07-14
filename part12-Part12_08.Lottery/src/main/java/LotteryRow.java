
import java.util.ArrayList;
import java.util.Random;

public class LotteryRow {

    private ArrayList<Integer> numbers;

    public LotteryRow() {
        this.randomizeNumbers();
    }

    public ArrayList<Integer> numbers() {
        return this.numbers;
    }

    public boolean containsNumber(int number) {
        // Checks if the array list already includes the given integer
        return this.numbers.contains(number);
    }

    public void randomizeNumbers() {
        // Initialize or reset the list for numbers
        this.numbers = new ArrayList<>();
        Random random = new Random();

        // Loop until we have successfully collected exactly 7 unique numbers
        while (this.numbers.size() < 7) {
            // random.nextInt(40) gives 0-39, adding 1 shifts the range to 1-40
            int drawnNumber = random.nextInt(40) + 1;

            // Use the containsNumber method to ensure no duplicates are added
            if (!this.containsNumber(drawnNumber)) {
                this.numbers.add(drawnNumber);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LotteryRow)) {
            return false;
        }
        LotteryRow lotteryRow = (LotteryRow) other;

        // Rows match if they contain the exact same numbers, regardless of order
        return this.numbers.containsAll(lotteryRow.numbers) && lotteryRow.numbers.containsAll(this.numbers);
    }
}
