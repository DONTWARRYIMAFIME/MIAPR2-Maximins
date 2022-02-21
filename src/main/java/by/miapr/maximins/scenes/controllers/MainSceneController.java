package by.miapr.maximins.scenes.controllers;

import by.miapr.maximins.alghoritm.MaximinsAlghoritm;
import by.miapr.maximins.alghoritm.MaximinsAlghoritmConfig;
import by.miapr.maximins.alghoritm.data.Cluster;
import by.miapr.maximins.alghoritm.data.Point;
import javafx.fxml.FXML;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;

public class MainSceneController {
    public static double POINT_SCALE = 3;
    private MaximinsAlghoritm maximinsAlghoritm;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnComplete;

    @FXML
    private Button btnX1;

    @FXML
    private Button btnX10;

    @FXML
    private Button btnX100;

    @FXML
    private BubbleChart<?, ?> bubbleChart;

    @FXML
    private TextField tfNumberOfPoints;

    @FXML
    void initialize() {
        btnStart.setOnAction(event -> {
            final MaximinsAlghoritmConfig config = new MaximinsAlghoritmConfig();

            config.setNumberOfPoints(Integer.parseInt(tfNumberOfPoints.getText()));


            maximinsAlghoritm = new MaximinsAlghoritm(config);

            maximinsAlghoritm.iterate();
            drawPoints(bubbleChart);
            enableIterationButtons();
        });

        btnComplete.setOnAction(event -> {
            maximinsAlghoritm.iterate(999);
            drawPoints(bubbleChart);

            if (maximinsAlghoritm.isCompleted()) {
                disableIterationButtons();
            }
        });

        btnX1.setOnAction(event -> {
            maximinsAlghoritm.iterate();
            drawPoints(bubbleChart);

            if (maximinsAlghoritm.isCompleted()) {
                disableIterationButtons();
            }
        });

        btnX10.setOnAction(event -> {
            maximinsAlghoritm.iterate(10);
            drawPoints(bubbleChart);

            if (maximinsAlghoritm.isCompleted()) {
                disableIterationButtons();
            }
        });

        btnX100.setOnAction(event -> {
            maximinsAlghoritm.iterate(100);
            drawPoints(bubbleChart);

            if (maximinsAlghoritm.isCompleted()) {
                disableIterationButtons();
            }
        });
    }

    private void disableIterationButtons() {
        btnComplete.setDisable(true);
        btnX1.setDisable(true);
        btnX10.setDisable(true);
        btnX100.setDisable(true);
    }

    private void enableIterationButtons() {
        btnComplete.setDisable(false);
        btnX1.setDisable(false);
        btnX10.setDisable(false);
        btnX100.setDisable(false);
    }

    private void drawPoints(BubbleChart<?,?> bubbleChart) {
        bubbleChart.getData().clear();
        List<Cluster> clusterList = maximinsAlghoritm.getClusters();

        for (Cluster cluster : clusterList) {
            XYChart.Series series = new XYChart.Series();

            for (Point point : cluster.getPoints()) {
                series.getData().add(new XYChart.Data(point.getX(), point.getY(), POINT_SCALE));
            }
            bubbleChart.getData().add(series);
        }

        XYChart.Series series = new XYChart.Series();
        for (Cluster cluster : clusterList) {
            series.getData().add(new XYChart.Data(cluster.getCenterX(), cluster.getCenterY(), POINT_SCALE + 5));

        }

        bubbleChart.getData().add(series);
    }

}
