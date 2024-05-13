package com.example.task_manager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Task Manager!");
        stage.setScene(scene);
        stage.show();
        NotificationThread.startNotifications(10, TimeUnit.SECONDS);
        stage.setOnCloseRequest(event -> {
            event.consume(); // Consume event so the window doesn't close immediately
            confirmExit(stage);
        });
    }
    private void confirmExit(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Thank you for using Task Manager! Are you sure you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) { //On user confirmation
            Platform.exit(); // Close Application
        }
    }

    public static void main(String[] args) {
        launch();
    }
}