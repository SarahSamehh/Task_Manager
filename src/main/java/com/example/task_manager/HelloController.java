package com.example.task_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;




public class HelloController {
    static ArrayList<Task> tasks = new ArrayList<>();
    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, LocalDate> deadlineColumn;

    @FXML
    private Pane MainPane;

    @FXML
    private ComboBox<Task.Status> ChangeStatus;


//    @FXML
//   // private ComboBox<Task.Status> ChangeStatusComboBox;

    @FXML
    private Button addTaskButton;

    @FXML
    private Button editTaskButton;

    @FXML
    private Button clearAllButton;


    @FXML
    void addButtonAction(ActionEvent eventt) {
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
            Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
            Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);


            // Handle the OK button action
            okButton.addEventFilter(ActionEvent.ACTION, event ->  {
                // Retrieve the task from TaskDetailsController
                Task newTask = controller.getTask();

                // Check if all required fields are filled
                if (newTask.getTitle() != null && !newTask.getTitle().isEmpty() &&
                        newTask.getDescription() != null && !newTask.getDescription().isEmpty() &&
                        newTask.getPriority() != null && newTask.getStatus() != null && newTask.getDeadline() != null) {
                    // Add the task to the table
                    taskTable.getItems().add(newTask);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all required fields.");
                    alert.show();
                    event.consume();
                }
            });

            dialog.show();

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
    void editButtonAction(ActionEvent eventt) {
        // Get the selected task from the table
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {
            try {
                // Load the FXML file for the dialog window
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("task-details.fxml"));
                Parent root = fxmlLoader.load();
                TaskDetailsController controller = fxmlLoader.getController();

                // Pass the selected task to the controller
                controller.initData(selectedTask);

                // Create a new dialog window
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.setTitle("Edit Task");
                dialog.getDialogPane().setContent(root);

                // Add OK and Cancel buttons to the dialog
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
                Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
                Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);

                // Handle the OK button action
                okButton.addEventFilter(ActionEvent.ACTION, event ->  {
                    Task newTask = controller.getTask();
                    for (int i = 0; i< tasks.size();i++) {
                        if(tasks.get(i).getID() == newTask.getID()){
                            tasks.set(i,newTask);
                        }
                    }

                    // Check if all required fields are filled
                    if (newTask.getTitle() != null && !newTask.getTitle().isEmpty() &&
                            newTask.getDescription() != null && !newTask.getDescription().isEmpty() &&
                            newTask.getPriority() != null && newTask.getStatus() != null && newTask.getDeadline() != null) {
                        // Update the task in the table
                        int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
                        taskTable.getItems().set(selectedIndex, newTask);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill all required fields.");
                        alert.show();
                        event.consume();
                    }
                });

                dialog.show();

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
    @FXML
    public void onDeleteButtonClick(ActionEvent event)  {
        //Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        int taskIndex = taskTable.getSelectionModel().getSelectedIndex();
        if (taskIndex >= 0 ) {
            taskTable.getItems().remove(taskIndex);
        }
//        else if (selectedTask==null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Please select a task");
//            alert.showAndWait();
//        }
        //taskDetailsController.set
    }

    @FXML
    public void onDarkModeButtonClick(ActionEvent event) throws IOException {
        BackgroundFill backgroundFill=new BackgroundFill(Color.rgb(0,0,0),new CornerRadii(10),new Insets(10));
        Background background=new Background(backgroundFill);
        MainPane.setBackground(background);
        taskTable.setBackground(background);
        setRowBackground(Color.GRAY);

        // controller.setGridPaneBackground(background);
    }

    @FXML
    public void onLightModeButtonClick(ActionEvent event) throws IOException {
        BackgroundFill backgroundFill=new BackgroundFill(Color.rgb(255,255,255),new CornerRadii(10),new Insets(10));
        Background background=new Background(backgroundFill);
        MainPane.setBackground(background);
        taskTable.setBackground(background);
        setRowBackground(Color.WHITE);

        //contoller.setGridPaneBackground(background);

    }

    // Method to set row background color
    private void setRowBackground(Color color) {
        taskTable.setRowFactory(tv -> {
            TableRow<Task> row = new TableRow<>();
            row.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
            return row;
        });
    }
    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public void onChangeStatusClick(ActionEvent event) {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        int taskIndex = taskTable.getSelectionModel().getSelectedIndex();
        if (selectedTask != null && ChangeStatus.getValue() != null) {
            Task.Status status = ChangeStatus.getValue();
            selectedTask.setStatus(status);
            taskTable.getItems().set(taskIndex, selectedTask);
        }  else if (selectedTask==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a task");
            alert.showAndWait();
        }
        else if (ChangeStatus.getSelectionModel().isEmpty()) {
            // Show an error message if no status is selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a status");
            alert.showAndWait();
        }

    }










    public void initialize() {

        ObservableList<Task.Status> statusOptions = FXCollections.observableArrayList(Task.Status.values());
        ChangeStatus.setItems(statusOptions);
        // Set up due date column
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        deadlineColumn.setCellFactory(column -> new TableCell<Task, LocalDate>() {
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
