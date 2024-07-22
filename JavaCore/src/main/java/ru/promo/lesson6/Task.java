package ru.promo.lesson6;

public class Task<T> implements Comparable<Task<T>> {

    private Integer id;
    private String title;
    private String description;
    private T taskType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getTaskType() {
        return taskType;
    }

    public void setTaskType(T taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                '}';
    }

    @Override
    public int compareTo(Task<T> o) {
        return o.getId() - id;
    }
}
