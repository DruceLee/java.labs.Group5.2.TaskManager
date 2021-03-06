package com.company.lab2.controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.company.lab2.model.Task;

/**
 * Class controller for Confirm.fxml view
 */
public class ConfirmController {

    @FXML
    private Button Deny;
    @FXML
    private Button Confirm;
    @FXML
    private Label label;
    private ObservableList<Task> taskList;
    private Stage confirmStage;
    private static boolean exit;

    @FXML
    void initialize() {
        confirmStage = WindowMaker.getStage();
        if (confirmStage.getTitle().matches("Confirm exit")) {
            label.setText("Exit without saving changes?");
            Confirm.setOnAction(event -> {
                exit = true;
                WindowMaker.closeWindow(confirmStage);
            });
        } else if (confirmStage.getTitle().matches("Confirm deleting")){
            taskList = MainController.getTaskList();
            label.setText("Remove " + MainController.getTask().getTitle());
            Confirm.setOnAction(event -> {
                taskList.remove(MainController.getTask());
                MainController.setSaved(false);
                WindowMaker.closeWindow(confirmStage);
                Platform.runLater(() -> WindowMaker.closeWindow(TaskController.getStage()));
            });
        }
        Deny.setOnAction(event -> WindowMaker.closeWindow(confirmStage));
    }

    static boolean isExit() {
        return exit;
    }
}