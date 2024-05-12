package com.example.task_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddNotesDialogController {

    @FXML
    private TextArea notesTextArea;

    private String notes;

    @FXML
    void onOKButtonClick(ActionEvent event) {
        // Retrieve the notes from the TextArea
        notes = notesTextArea.getText();

        // Close the dialog window
        Stage stage = (Stage) notesTextArea.getScene().getWindow();
        stage.close();
    }

    public String getNotes() {
        return notes;
    }

}