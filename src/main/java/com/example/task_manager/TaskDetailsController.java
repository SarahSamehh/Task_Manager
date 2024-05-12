package com.example.task_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class TaskDetailsController {

    @FXML
    private Label StatusLabel;

    @FXML
    private ComboBox<String> StausComboBox;

    @FXML
    private Label PriorityLabel;

    @FXML
    private ComboBox<String> PriorityComboBox;

    @FXML
    private Label deadlineLabel;

    @FXML
    private DatePicker deadlinePicker;

    @FXML
    private Label detailsLabel;

    @FXML
    private TextArea detailsText;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField titleText;

    @FXML
    public void initialize() {
        ObservableList<String> statusOptions = FXCollections.observableArrayList("To Do", "In Progress", "Done");
        StausComboBox.setItems(statusOptions);

        // Populating priority ComboBox
        ObservableList<String> priorityOptions = FXCollections.observableArrayList("High", "Medium", "Low");
        PriorityComboBox.setItems(priorityOptions);
    }

    // process the data..
    public Task getTask() {
        String title = titleText.getText(); // Using titleText for the title
        String description = detailsText.getText(); // Using detailsText for the description
        String priority = PriorityComboBox.getValue(); // Using PriorityComboBox for the priority
        String status = StausComboBox.getValue(); // Using StausComboBox for the status
        LocalDate dueDate = deadlinePicker.getValue(); // Using deadlinePicker for the due date

        // Create and return a new Task object
        return new Task(title, description, priority, status, dueDate);
    }
}
