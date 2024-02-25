package com.javafx.clock_app;
// Created: October 2021
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.time.LocalTime;
public class AnalogClock extends Application {
    private static final double WIDTH = 400;
    private static final double HEIGHT = 400;
    private static final double CENTER_X = WIDTH / 2;
    private static final double CENTER_Y = HEIGHT / 2;
    private static final double CLOCK_RADIUS = WIDTH * 0.5;
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setTitle("Clock");
        primaryStage.setScene(scene);
        primaryStage.show();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> drawClock(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private void drawClock(GraphicsContext gc) {
        LocalTime time = LocalTime.now();
        double hours = time.getHour() % 12 + time.getMinute() / 60.0;
        double minutes = time.getMinute() + time.getSecond() / 60.0;
        double seconds = time.getSecond();
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.setFill(Color.BLACK);
        gc.fillOval(0, 0, WIDTH, HEIGHT);
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeOval(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(i * 30);
            double x1 = CENTER_X + CLOCK_RADIUS * Math.cos(angle);
            double y1 = CENTER_Y + CLOCK_RADIUS * Math.sin(angle);
            double x2 = CENTER_X + (CLOCK_RADIUS - 20) * Math.cos(angle);
            double y2 = CENTER_Y + (CLOCK_RADIUS - 20) * Math.sin(angle);
            gc.strokeLine(x1, y1, x2, y2);
        }
        gc.setStroke(Color.rgb(0, 153, 255)); // Blue
        drawHand(gc, hours * 30, CLOCK_RADIUS * 0.5);
        gc.setStroke(Color.rgb(0, 255, 0)); // Green
        drawHand(gc, minutes * 6, CLOCK_RADIUS * 0.7);
        gc.setStroke(Color.rgb(255, 0, 0)); // Red
        drawHand(gc, seconds * 6, CLOCK_RADIUS * 0.8);
    }
    private void drawHand(GraphicsContext gc, double angle, double length) {
        double x = CENTER_X + length * Math.cos(Math.toRadians(angle - 90));
        double y = CENTER_Y + length * Math.sin(Math.toRadians(angle - 90));
        gc.strokeLine(CENTER_X, CENTER_Y, x, y);
    }
    public static void main(String[] args) {
        launch(args);
    }
}