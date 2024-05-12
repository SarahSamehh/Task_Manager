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
