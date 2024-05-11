package com.example.task_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class HelloController {

    @FXML
    private Button addTaskButton;

    @FXML
    private Button clearAllButton;

    @FXML
    private Button deleteTaskButton;

    @FXML
    private Button editTaskButton;

    @FXML
    private Button removeTaskButton;

    @FXML
    void addButtonAction(ActionEvent event) {
        try {
            // Load the FXML file for the dialog window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("task-details.fxml"));
            Parent root = fxmlLoader.load();
            TaskDetailsController controller = fxmlLoader.getController();

            // Create a new dialog window
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Add Task");
            dialog.getDialogPane().setContent(root);

            // Add OK and Cancel buttons to the dialog
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            // Show the dialog window and wait for user input
            Optional<ButtonType> result = dialog.showAndWait();

            // Handle the OK button action
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Add code here to handle OK button action, if needed
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}