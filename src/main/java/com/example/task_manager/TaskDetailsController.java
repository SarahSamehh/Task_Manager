package com.example.task_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import com.example.task_manager.HelloController;

import java.io.IOException;
import java.time.LocalDate;

//import static javax.swing.text.StyleConstants.setBackground;

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
    private RadioButton IndividualRadioButton;

    @FXML
    private RadioButton TeamRadioButton;

    @FXML
    private ToggleGroup g1;

    public boolean darkmode;


    @FXML
    public void initialize(boolean darkmode) {
       if (darkmode){
            BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(0, 0, 0), new CornerRadii(10), new Insets(10));
            Background background = new Background(backgroundFill);
            dialogPane.setBackground(background);

           // Set label text color to white or any other suitable color for dark mode
           for (Node node : gridPane.getChildren()) {
               if (node instanceof Label || node instanceof RadioButton) {
                   ((Labeled) node).setTextFill(Color.rgb(163, 124, 240));
               }
           }
        }
       else
           dialogPane.setBackground(null);


        // Populating Status ComboBox
        ObservableList<Task.Status> statusOptions = FXCollections.observableArrayList(Task.Status.values());
        StatusComboBox.setItems(statusOptions);

        // Populating Priority ComboBox
        ObservableList<Task.Priority> priorityOptions = FXCollections.observableArrayList(Task.Priority.values());
        PriorityComboBox.setItems(priorityOptions);

        // Initialize ToggleGroup
        TeamRadioButton.setToggleGroup(g1);
        IndividualRadioButton.setToggleGroup(g1);

    }



    // process the data..
    public Task getTask() {
        String title = titleText.getText(); // Using titleText for the title
        String description = descriptionText.getText(); // Using detailsText for the description
        Task.Priority priority = PriorityComboBox.getValue(); //Using PriorityComboBox for the priority
        Task.Status status = StatusComboBox.getValue(); //Using StatusComboBox for the status
        LocalDate deadline = deadlinePicker.getValue(); // Using deadlinePicker for the due date
        String assignee = assignText.getText();
        Task.AssigneeType assigneeType;

        // Determine assignee type based on selected RadioButton
        RadioButton selectedRadioButton = (RadioButton) g1.getSelectedToggle();
        if (selectedRadioButton != null) {
            String radioButtonValue = selectedRadioButton.getText();
            if (radioButtonValue.equals("Team")) {
                assigneeType = Task.AssigneeType.TEAM;
            } else {
                assigneeType = Task.AssigneeType.INDIVIDUAL;
            }
        }
        else {
            // Default to some value if no RadioButton is selected
            assigneeType = Task.AssigneeType.UNKNOWN;
        }

        // Create and return a new Task object
        return new Task(title, description, priority, status, deadline, assignee, assigneeType);
    }

    public void initData(Task task) {
        // Set the initial values of the fields based on the selected task
        titleText.setText(task.getTitle());
        descriptionText.setText(task.getDescription());
        PriorityComboBox.setValue(task.getPriority());
        StatusComboBox.setValue(task.getStatus());
        deadlinePicker.setValue(task.getDeadline());
        assignText.setText(task.getAssignee());

        // Determine and set the appropriate RadioButton based on the assignee type
        Task.AssigneeType assigneeType = task.getAssigneeType();
        if (assigneeType != null) {
            if (assigneeType == Task.AssigneeType.TEAM) {
                TeamRadioButton.setSelected(true);
            } else {
                IndividualRadioButton.setSelected(true);
            }
        }

    }

}