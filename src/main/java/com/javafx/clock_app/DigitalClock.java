package com.javafx.clock_app;
// Created: October 2021
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class DigitalClock extends Application {
    private Text timeText;
    private Text dateText;
    private Text dayText;
    private boolean is24HourFormat = true;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Clock");
        primaryStage.setResizable(false);
        timeText = new Text();
        timeText.setFont(Font.font(30));
        timeText.setFill(Color.WHITE);
        dateText = new Text();
        dateText.setFont(Font.font(20));
        dateText.setFill(Color.WHITE);
        dayText = new Text();
        dayText.setFont(Font.font(20));
        dayText.setFill(Color.WHITE);
        Button toggleFormatButton = new Button("Toggle Format");
        toggleFormatButton.setOnAction(event -> toggleTimeFormat());
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setStyle("-fx-background-color: black;");
        gridPane.add(timeText, 0, 0, 2, 1);
        gridPane.add(dateText, 0, 1, 2, 1);
        gridPane.add(dayText, 0, 2, 2, 1);
        gridPane.add(toggleFormatButton, 0, 5);
        Scene scene = new Scene(gridPane, 200, 200);
        primaryStage.setScene(scene);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateClock())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        primaryStage.show();
    }
    private void updateClock() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat(is24HourFormat ? "HH:mm:ss" : "hh:mm:ss a");
        String time = timeFormat.format(calendar.getTime());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        String day = new SimpleDateFormat("EEEE").format(calendar.getTime());
        timeText.setText(time);
        dateText.setText(date);
        dayText.setText(day);
    }
    private void toggleTimeFormat() {
        is24HourFormat = !is24HourFormat;
        updateClock();
    }
    public static void main(String[] args) {
        launch(args);
    }
}