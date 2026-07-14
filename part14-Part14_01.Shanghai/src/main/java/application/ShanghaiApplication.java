package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ShanghaiApplication extends Application {

    @Override
    public void start(Stage stage) {
        // 1. Create the X and Y numerical axes
        NumberAxis xAxis = new NumberAxis(2006, 2018, 2);
        NumberAxis yAxis = new NumberAxis(0, 100, 10);

        // Set labels for the chart axes
        xAxis.setLabel("Year");
        yAxis.setLabel("Ranking");

        // 2. Instantiate the LineChart directly using the configured axes
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("University of Helsinki Shanghai Ranking (2007-2017)");

        // 3. Create the data series dataset
        XYChart.Series<Number, Number> helsinkiData = new XYChart.Series<>();
        helsinkiData.setName("University of Helsinki");

        // Populate the series with the provided historical ranking pairs
        helsinkiData.getData().add(new XYChart.Data<>(2007, 73));
        helsinkiData.getData().add(new XYChart.Data<>(2008, 68));
        helsinkiData.getData().add(new XYChart.Data<>(2009, 72));
        helsinkiData.getData().add(new XYChart.Data<>(2010, 72));
        helsinkiData.getData().add(new XYChart.Data<>(2011, 74));
        helsinkiData.getData().add(new XYChart.Data<>(2012, 73));
        helsinkiData.getData().add(new XYChart.Data<>(2013, 76));
        helsinkiData.getData().add(new XYChart.Data<>(2014, 73));
        helsinkiData.getData().add(new XYChart.Data<>(2015, 67));
        helsinkiData.getData().add(new XYChart.Data<>(2016, 56));
        helsinkiData.getData().add(new XYChart.Data<>(2017, 56));

        // 4. Attach the data series to the chart object node
        lineChart.getData().add(helsinkiData);

        // 5. Pass the chart directly to the Scene alongside structural dimensions
        Scene scene = new Scene(lineChart, 640, 480);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(ShanghaiApplication.class);
    }
}
