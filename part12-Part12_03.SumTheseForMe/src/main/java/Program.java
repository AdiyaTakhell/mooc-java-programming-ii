import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // test your method here
        int[] numbers = {3, -1, 8, 4};

        System.out.println(sum(numbers, 0, 0, 0, 0));
        System.out.println(sum(numbers, 0, 0, 0, 10));
        System.out.println(sum(numbers, 0, 1, 0, 10));
        System.out.println(sum(numbers, 0, 1, -10, 10));
        System.out.println(sum(numbers, -1, 999, -10, 10));
    }

    public static int sum(int[] array, int fromWhere, int toWhere, int smallest, int largest) {
        // Adjust the lower limit if it is smaller than 0
        if (fromWhere < 0) {
            fromWhere = 0;
        }

        // Adjust the upper limit if it is larger than the last valid index
        if (toWhere >= array.length) {
            toWhere = array.length - 1;
        }

        int totalSum = 0;

        // Loop through the array from the adjusted starting index to the ending index (inclusive)
        for (int i = fromWhere; i <= toWhere; i++) {
            int currentNumber = array[i];

            // Only add numbers that are smaller/equal to largest AND larger/equal to smallest
            if (currentNumber >= smallest && currentNumber <= largest) {
                totalSum += currentNumber;
            }
        }

        return totalSum;
    }
}
