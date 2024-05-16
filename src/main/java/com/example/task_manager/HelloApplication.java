package com.example.task_manager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try (FileInputStream fileIn = new FileInputStream("objects.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
             HelloController.tasks = (ArrayList<Task>) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Task Manager!");
        Image iconImage = new Image(getClass().getResourceAsStream("icon.png"));
        stage.getIcons().add(iconImage);
        stage.setScene(scene);
        stage.show();
        NotificationThread.startNotifications(10, TimeUnit.SECONDS);
        stage.setOnCloseRequest(event -> {
            try (FileOutputStream fileOut = new FileOutputStream("objects.ser");
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                 out.writeObject(HelloController.tasks); // yourArray is your array of objects
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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