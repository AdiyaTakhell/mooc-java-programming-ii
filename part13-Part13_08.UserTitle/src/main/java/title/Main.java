package title;

import java.util.Scanner;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Ask the user for the title in the console
        System.out.print("Please enter a title: ");
        String userTitle = scanner.nextLine();
        
        // 2. Format it as a named application parameter
        String argument = "--title=" + userTitle;
        
        // 3. Launch the JavaFX application with the argument array
        Application.launch(UserTitle.class, argument);
    }
}
