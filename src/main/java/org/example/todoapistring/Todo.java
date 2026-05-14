package org.example.todoapistring;

public class Todo {

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    // Default Constructor
    public Todo() {
    }

    // Parameterized Constructor
    public Todo(int id, boolean completed,String title,int userId) {

        this.id = id;
        this.completed = completed;
        this.title = title;

        this.userId = userId;
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}