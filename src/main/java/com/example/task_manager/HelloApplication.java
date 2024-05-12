package com.example.task_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    }

    public static void main(String[] args) {
        launch();
    }
}