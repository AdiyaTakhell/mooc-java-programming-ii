package application;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class PartiesApplication extends Application {

    @Override
    public void start(Stage stage) {
        // 1. Configure the X and Y axes
        NumberAxis xAxis = new NumberAxis(1968, 2008, 4);
        NumberAxis yAxis = new NumberAxis(0, 30, 5);

        xAxis.setLabel("Year");
        yAxis.setLabel("Support (%)");

        // 2. Instantiate the LineChart component
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Relative support of the parties"); // Sets the requested title template

        // 3. Process the dataset file dynamically
        List<Integer> years = new ArrayList<>();

        try (Scanner fileReader = new Scanner(Paths.get("partiesdata.tsv"))) {
            // Read the header row containing year sequences
            if (fileReader.hasNextLine()) {
                String headerRow = fileReader.nextLine();
                String[] pieces = headerRow.split("\t");
                for (int i = 1; i < pieces.length; i++) {
                    years.add(Integer.valueOf(pieces[i].trim()));
                }
            }

            // Read the subsequent rows containing party specific data rows
            while (fileReader.hasNextLine()) {
                String dataRow = fileReader.nextLine();
                String[] pieces = dataRow.split("\t");
                
                String partyName = pieces[0].trim();
                XYChart.Series<Number, Number> partySeries = new XYChart.Series<>();
                partySeries.setName(partyName); // Assigns the series label property safely

                // Map data data points for the party across active column variables
                for (int i = 1; i < pieces.length; i++) {
                    String dataValue = pieces[i].trim();
                    
                    // Skip empty fields or missing indicators (e.g. represented by a hyphen or blank)
                    if (!dataValue.equals("-") && !dataValue.isEmpty()) {
                        double supportValue = Double.valueOf(dataValue);
                        int targetYear = years.get(i - 1);
                        partySeries.getData().add(new XYChart.Data<>(targetYear, supportValue));
                    }
                }

                // Add the populated party data series directly into the line chart data pool
                lineChart.getData().add(partySeries);
            }

        } catch (Exception e) {
            System.out.println("Error reading dataset file: " + e.getMessage());
        }

        // 4. Initialize layout viewing window frame configuration directly using the line chart node
        Scene view = new Scene(lineChart, 800, 600);
        stage.setScene(view);
        stage.show();
    }

    public static void main(String[] args) {
        launch(PartiesApplication.class);
    }
}
