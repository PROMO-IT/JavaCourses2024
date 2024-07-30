package ru.promo.stream;

public class Task<T> {

    private Integer id;
    private String title;
    private String description;
    private Employee employee;
    private T taskType;

    public Task(Integer id, String title, String description, T taskType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskType = taskType;
    }

    public Task(Integer id, String title, String description, Employee employee, T taskType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.employee = employee;
        this.taskType = taskType;
    }

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                '}';
    }
}
