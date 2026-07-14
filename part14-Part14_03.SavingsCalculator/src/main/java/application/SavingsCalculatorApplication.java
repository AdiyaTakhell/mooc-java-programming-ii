package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SavingsCalculatorApplication extends Application {

    @Override
    public void start(Stage stage) {
        // --- MAIN LAYOUT ---
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(10));

        // --- TOP AREA: TWO SLIDERS INSIDE NESTED BORDERPANES ---
        VBox topMenu = new VBox();
        topMenu.setSpacing(10);
        topMenu.setPadding(new Insets(10));

        // 1. Monthly Savings Slider Layout
        BorderPane savingsPane = new BorderPane();
        Label savingsLeftText = new Label("Monthly savings");
        Slider savingsSlider = new Slider(25, 250, 25);
        savingsSlider.setShowTickMarks(true);
        savingsSlider.setShowTickLabels(true);
        Label savingsRightText = new Label("25");
        
        savingsPane.setLeft(savingsLeftText);
        savingsPane.setCenter(savingsSlider);
        savingsPane.setRight(savingsRightText);

        // 2. Yearly Interest Rate Slider Layout
        BorderPane interestPane = new BorderPane();
        Label interestLeftText = new Label("Yearly interest rate");
        Slider interestSlider = new Slider(0, 10, 0);
        interestSlider.setShowTickMarks(true);
        interestSlider.setShowTickLabels(true);
        Label interestRightText = new Label("0");
        
        interestPane.setLeft(interestLeftText);
        interestPane.setCenter(interestSlider);
        interestPane.setRight(interestRightText);

        // Add both rows to the top container
        topMenu.getChildren().addAll(savingsPane, interestPane);
        mainLayout.setTop(topMenu);

        // --- CENTER AREA: LINE CHART CONFIGURATION ---
        NumberAxis xAxis = new NumberAxis(0, 30, 1);
        NumberAxis yAxis = new NumberAxis(); // Auto-adapting Y-Axis
        xAxis.setLabel("Years");
        yAxis.setLabel("Savings amount");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Savings Calculator");
        lineChart.setAnimated(false); // Prevents rendering glitches on sliding updates
        mainLayout.setCenter(lineChart);

        // --- DATA SERIES INITIALIZATION ---
        XYChart.Series<Number, Number> regularSavingsSeries = new XYChart.Series<>();
        regularSavingsSeries.setName("Savings without interest");
        
        XYChart.Series<Number, Number> interestSavingsSeries = new XYChart.Series<>();
        interestSavingsSeries.setName("Savings with interest");

        lineChart.getData().addAll(regularSavingsSeries, interestSavingsSeries);

        // --- RECALCULATION & LISTENER LOGIC ---
        // Helper listener lambda to trigger map re-renders on user input variations
        ChangeListener<Number> sliderChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            // Update textual label readouts
            savingsRightText.setText(String.format("%.1f", savingsSlider.getValue()));
            interestRightText.setText(String.format("%.2f", interestSlider.getValue()));

            // Reset current visual charts
            regularSavingsSeries.getData().clear();
            interestSavingsSeries.getData().clear();

            double monthlySavings = savingsSlider.getValue();
            double yearlyInterestRate = interestSlider.getValue() / 100.0;

            double currentRegularTotal = 0;
            double currentInterestTotal = 0;

            // Plot base coordinate position (0, 0)
            regularSavingsSeries.getData().add(new XYChart.Data<>(0, 0));
            interestSavingsSeries.getData().add(new XYChart.Data<>(0, 0));

            // Generate compound points incrementally over 30 loops
            for (int year = 1; year <= 30; year++) {
                // Calculation strategy without interest yields straight arithmetic growth
                currentRegularTotal += monthlySavings * 12;
                regularSavingsSeries.getData().add(new XYChart.Data<>(year, currentRegularTotal));

                // Calculation strategy with compound growth applied at each annual cycle end step
                currentInterestTotal += monthlySavings * 12;
                currentInterestTotal *= (1.0 + yearlyInterestRate);
                interestSavingsSeries.getData().add(new XYChart.Data<>(year, currentInterestTotal));
            }
        };

        // Bind the tracking listener to both input adjustment properties
        savingsSlider.valueProperty().addListener(sliderChangeListener);
        interestSlider.valueProperty().addListener(sliderChangeListener);

        // Run an initial manual assessment update to draw state map arrays on launch
        savingsSlider.setValue(50);
        interestSlider.setValue(0);

        // --- WINDOW FRAMING ENVIRONMENT INITIALIZATION ---
        Scene scene = new Scene(mainLayout, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(SavingsCalculatorApplication.class);
    }
}
