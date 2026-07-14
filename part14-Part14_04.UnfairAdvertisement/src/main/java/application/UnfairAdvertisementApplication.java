package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class UnfairAdvertisementApplication extends Application {

    @Override
    public void start(Stage window) {
        CategoryAxis xAxis = new CategoryAxis();
        
        // FIX 1: Start the Y-axis at 0.0 to prevent misleading visual scaling.
        // Set upper bound to 100.0 with tick increments of 10.0 for a realistic scale.
        NumberAxis yAxis = new NumberAxis(0.0, 100.0, 10.0);
        
        // FIX 2: Make the numbers visible so the consumer can see the actual values.
        yAxis.setTickLabelsVisible(true);
        yAxis.setLabel("Speed (Mbps)"); // Renamed from biased marketing text to standard units

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        barChart.setTitle("Internet Speed Comparison");
        barChart.setLegendVisible(false);

        // Fix 3: Added proper generic types to clear compiler warnings
        XYChart.Series<String, Number> speeds = new XYChart.Series<>();
        speeds.getData().add(new XYChart.Data<>("NDA", 77.4));
        speeds.getData().add(new XYChart.Data<>("Fastie", 77.2));
        speeds.getData().add(new XYChart.Data<>("SuperNet", 77.1));
        speeds.getData().add(new XYChart.Data<>("Meganet", 77.1));

        barChart.getData().add(speeds);
        Scene view = new Scene(barChart, 500, 400); // Expanded slightly for better text breathing room
        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(UnfairAdvertisementApplication.class);
    }
}
