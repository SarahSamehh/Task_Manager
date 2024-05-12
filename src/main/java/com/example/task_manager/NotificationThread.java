package com.example.task_manager;
import java.time.LocalDate;

import javafx.application.Platform;
import org.controlsfx.control.Notifications;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class NotificationThread extends Thread {
    ArrayList<Task> tasksCopy = HelloController.tasks;
    @Override
    public void run() {
        for (Task task : tasksCopy) {
            if (task.getDeadline().isEqual(LocalDate.now()) && task.getStatus() != Task.Status.DONE) {
                Platform.runLater(() -> {
                    Notifications.create()
                            .title("DEADLINE")
                            .text(task.getTitle() + " deadline is TODAY")
                            .showWarning();
                });
            } else if(task.getDeadline().isBefore(LocalDate.now()) && task.getStatus() != Task.Status.DONE){
                Platform.runLater(() -> {
                    Notifications.create()
                            .title("TASK IS OVERDUE")
                            .text(task.getTitle() + " deadline passed")
                            .showError();
                });
            }
        }
    }
    public static void startNotifications(long delay, TimeUnit timeUnit) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new NotificationThread(), delay, delay, timeUnit);
    }
}
