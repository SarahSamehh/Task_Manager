package com.example.task_manager;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private int ID;
    private String Title;
    private String Description;
    private String Status;
    private String EstimatedTime;
    private String Priority;
    private LocalDate Duedate;
    private LocalDateTime CreationDate;
    private LocalDateTime CompletionDate;
    private String Assignee;

    public Task(int ID, String Title, String Description) {
        this.ID = ID;
        this.Title = Title;
        this.Description = Description;
        this.Status = "Uncompleted";
    }

    public Task(String Title, String description, String priority, String status, LocalDate dueDate) {
        this.Title = Title;
        this.Description = description;
        this.Status = status;
        this.Priority = priority;
        this.Duedate = dueDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getEstimatedTime() {
        return EstimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        EstimatedTime = estimatedTime;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public LocalDate getDuedate() {
        return Duedate;
    }

    public void setDuedate(LocalDate duedate) {
        Duedate = duedate;
    }

    public LocalDateTime getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        CreationDate = creationDate;
    }

    public LocalDateTime getCompletionDate() {
        return CompletionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        CompletionDate = completionDate;
    }

    public String getAssignee() {
        return Assignee;
    }

    public void setAssignee(String assignee) {
        Assignee = assignee;
    }
}
