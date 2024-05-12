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
    private ComboBox<Task.Status> StatusComboBox;

    @FXML
    private Label PriorityLabel;

    @FXML
    private ComboBox<Task.Priority> PriorityComboBox;

    @FXML
    private Label deadlineLabel;

    @FXML
    private DatePicker deadlinePicker;

    @FXML
    private Label descriptionLabel;

    @FXML
    private TextArea descriptionText;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField titleText;

    @FXML
    public void initialize() {
        // Populating Status ComboBox
        ObservableList<Task.Status> statusOptions = FXCollections.observableArrayList(Task.Status.values());
        StatusComboBox.setItems(statusOptions);

        // Populating Priority ComboBox
        ObservableList<Task.Priority> priorityOptions = FXCollections.observableArrayList(Task.Priority.values());
        PriorityComboBox.setItems(priorityOptions);
    }

    // process the data..
    public Task getTask() {
        String title = titleText.getText(); // Using titleText for the title
        String description = descriptionText.getText(); // Using detailsText for the description
        Task.Priority priority = PriorityComboBox.getValue(); //Using PriorityComboBox for the priority
        Task.Status status = StatusComboBox.getValue(); //Using StatusComboBox for the status
        LocalDate deadline = deadlinePicker.getValue(); // Using deadlinePicker for the due date

        // Create and return a new Task object
        return new Task(title, description, priority, status, deadline);
    }
}
