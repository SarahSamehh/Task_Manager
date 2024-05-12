package com.example.task_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

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
    private GridPane gridPane;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField titleText;

    @FXML
    private TextField assignText;




    @FXML
    public void initialize() {
        // Populating Status ComboBox
        ObservableList<Task.Status> statusOptions = FXCollections.observableArrayList(Task.Status.values());
        StatusComboBox.setItems(statusOptions);

        // Populating Priority ComboBox
        ObservableList<Task.Priority> priorityOptions = FXCollections.observableArrayList(Task.Priority.values());
        PriorityComboBox.setItems(priorityOptions);
    }

    public void setStatusComboBox(Task.Status status) {
        StatusComboBox.setValue(status);
    }

    public void setPriorityComboBox(Task.Priority priority) {
        PriorityComboBox.setValue(priority);
    }

    public void setDeadlinePicker(LocalDate date) {
        deadlinePicker.setValue(date);
    }

    public void setDescriptionText(String text) {
        descriptionText.setText(text);
    }

    public void setTitleText(String text) {
        titleText.setText(text);
    }

    // Getters for UI components

    public Task.Status getStatusComboBoxValue() {
        return StatusComboBox.getValue();
    }

    public Task.Priority getPriorityComboBoxValue() {
        return PriorityComboBox.getValue();
    }

    public LocalDate getDeadlinePickerValue() {
        return deadlinePicker.getValue();
    }

    public String getDescriptionText() {
        return descriptionText.getText();
    }

    public String getTitleText() {
        return titleText.getText();
    }

    // process the data..
    public Task getTask() {
        String title = titleText.getText(); // Using titleText for the title
        String description = descriptionText.getText(); // Using detailsText for the description
        Task.Priority priority = PriorityComboBox.getValue(); //Using PriorityComboBox for the priority
        Task.Status status = StatusComboBox.getValue(); //Using StatusComboBox for the status
        LocalDate deadline = deadlinePicker.getValue(); // Using deadlinePicker for the due date
        String assignee = assignText.getText();

        // Create and return a new Task object
        return new Task(title, description, priority, status, deadline, assignee);
    }

    public void initData(Task task) {
        // Set the initial values of the fields based on the selected task
        titleText.setText(task.getTitle());
        descriptionText.setText(task.getDescription());
        PriorityComboBox.setValue(task.getPriority());
        StatusComboBox.setValue(task.getStatus());
        deadlinePicker.setValue(task.getDeadline());
    }

    public void setGridPaneBackground(Background background) {
        gridPane.setBackground(background);
    }
}