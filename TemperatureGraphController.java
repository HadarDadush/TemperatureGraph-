// This file defines the controller for handling user interactions with the temperature graph.
// It draws the graph, updates it with temperature data for each year, and allows switching between years.

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TemperatureGraphController {

    @FXML
    private Canvas canvas;
    private int yearIdx = 0; 

    // Temperature data
    private int[][] temps = {
        {9, 10, 13, 22, 28, 29, 31, 33, 29, 24, 19, 12},  // 2018
        {10, 12, 14, 23, 26, 28, 32, 35, 31, 25, 20, 13},  // 2019
        {11, 12, 15, 24, 25, 28, 33, 35, 32, 26, 21, 14},  // 2020
        {8, 9, 12, 15, 23, 25, 30, 34, 30, 23, 18, 10},    // 2021
        {7, 8, 11, 14, 22, 24, 31, 33, 30, 22, 17, 9}      // 2022
    };

    private static final int MAX_TEMP = 40;
    private static final int MIN_TEMP = 0;
    private static final double Y_MARGIN = 40;

    @FXML
    public void initialize() {
        drawGraph();
    }

    @FXML
    public void drawGraph() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int[] currentTemps = temps[yearIdx];

        // Bar width
        double barWidth = (canvas.getWidth() - Y_MARGIN) / 12;

        // Y-axis step
        double yStep = (canvas.getHeight() - 80) / 8;

        // Y-axis labels
        for (int i = 0; i <= 8; i++) {
            int tempValue = MIN_TEMP + (MAX_TEMP - MIN_TEMP) * (8 - i) / 8;
            double yPos = yStep * i + Y_MARGIN;

            // Draw Y label
            gc.setFill(Color.BLACK);
            gc.fillText(String.format("%d", tempValue), Y_MARGIN - 30, yPos);
        }

        // Find min and max temperatures
        int minTemp = Integer.MAX_VALUE;
        int maxTemp = Integer.MIN_VALUE;
        for (int t : currentTemps) {
            minTemp = Math.min(minTemp, t);
            maxTemp = Math.max(maxTemp, t);
        }

        // Bar spacing
        double barSpacing = 5;
        for (int i = 0; i < currentTemps.length; i++) {
            double barHeight = (double) currentTemps[i] / MAX_TEMP * (canvas.getHeight() - 80);
            double xPos = Y_MARGIN + i * barWidth;
            double yPos = canvas.getHeight() - barHeight - 40;

            // Color bars based on temperature
            gc.setFill(currentTemps[i] == maxTemp ? Color.RED : (currentTemps[i] == minTemp ? Color.BLUE : Color.GRAY));

            // Draw the bar
            gc.fillRect(xPos, yPos, barWidth - barSpacing, barHeight);

            // Month label
            gc.setFill(Color.BLACK);
            gc.fillText(String.valueOf(i + 1), xPos + barWidth / 2 - 5, canvas.getHeight() - 10);

            // Temp label
            gc.fillText(String.format("%d", currentTemps[i]), xPos + barWidth / 2 - 5, yPos - 5);
        }

        // Draw title
        gc.setFill(Color.BLACK);
        double titleX = (canvas.getWidth() - gc.getFont().getSize() * 22) / 2 + 20;

        // Display title
        gc.fillText("Average Monthly Temperatures in " + (2018 + yearIdx), titleX, 20);
    }

    // Change year
    @FXML
    public void nextYear() {
        yearIdx = (yearIdx + 1) % temps.length;
        drawGraph();
    }

    @FXML
    public void prevYear() {
        yearIdx = (yearIdx - 1 + temps.length) % temps.length;
        drawGraph();
    }
}