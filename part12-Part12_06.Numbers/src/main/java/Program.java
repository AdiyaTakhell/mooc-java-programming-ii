
import java.util.Random;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Random r = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many random numbers should be printed?");
        int number = scanner.nextInt();
        while (number--> 0) {
            System.out.println(r.nextInt(11));

        }

    }

}
