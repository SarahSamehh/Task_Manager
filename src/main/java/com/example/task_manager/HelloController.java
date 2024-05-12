package com.example.task_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;



public class HelloController {

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, LocalDate> dueDateColumn;

    @FXML
    private Button addTaskButton;

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
                // Retrieve the task from TaskDetailsController
                Task newTask = controller.getTask();

                // Add the task to the table
                taskTable.getItems().add(newTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clearAllButtonAction(ActionEvent event) {
        // Clear all items in the table
        taskTable.getItems().clear();
    }
    @FXML
    void editButtonAction(ActionEvent event) {
        // Get the selected task from the table
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {
            try {
                // Load the FXML file for the dialog window
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("task-details.fxml"));
                Parent root = fxmlLoader.load();

                // Access UI elements directly
                TextField taskNameField = (TextField) root.lookup("#titleText");
                TextArea taskDescriptionArea = (TextArea) root.lookup("#detailsText");

                // Pre-populate UI elements with task details
                taskNameField.setText(selectedTask.getTitle());
                taskDescriptionArea.setText(selectedTask.getDescription());

                // Create a new dialog window
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.setTitle("Edit Task");
                dialog.getDialogPane().setContent(root);

                // Add OK and Cancel buttons to the dialog
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

                // Show the dialog window and wait for user input
                Optional<ButtonType> result = dialog.showAndWait();

                // Handle the OK button action
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Update the selected task with the edited details
                    selectedTask.setTitle(taskNameField.getText());
                    selectedTask.setDescription(taskDescriptionArea.getText());

                    // Update the task in the table
                    int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
                    taskTable.getItems().set(selectedIndex, selectedTask);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Show an error message if no task is selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a task to edit.");
            alert.showAndWait();
        }
    }


    public void initialize() {
        // Set up due date column
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("duedate"));
        dueDateColumn.setCellFactory(column -> new TableCell<Task, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
    }
}
