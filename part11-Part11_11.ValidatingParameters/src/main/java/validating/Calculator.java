package validating;

public class Calculator {

    public int factorial(int num) {
        // Rule: Parameter must be a non-negative number (0 or greater)
        if (num < 0) {
            throw new IllegalArgumentException("Parameter must be non-negative.");
        }

        int answer = 1;
        for (int i = 1; i <= num; i++) {
            answer *= i;
        }

        return answer;
    }

    public int binomialCoefficent(int setSize, int subsetSize) {
        // Rule: Parameters must be non-negative
        if (setSize < 0 || subsetSize < 0) {
            throw new IllegalArgumentException("Parameters must be non-negative.");
        }

        // Rule: Subset size cannot exceed the set size
        if (subsetSize > setSize) {
            throw new IllegalArgumentException("Subset size cannot exceed set size.");
        }

        int numerator = factorial(setSize);
        int denominator = factorial(subsetSize) * factorial(setSize - subsetSize);

        return numerator / denominator;
    }
}
