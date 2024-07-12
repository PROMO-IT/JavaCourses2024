package ru.promo.lesson5;

public abstract class Task implements Closable {
    private int number;
    private String status;
    private String description;
    private Employee employee;

    static int taskNumberCount = 0;

    public Task(String description) {
        taskNumberCount++;
        number = taskNumberCount;
        this.description = description;
    }

    public String addTask(Employee employee) {
        this.employee = employee;
        this.status = "Created";
        return status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "number=" + number +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", employee=" + employee +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public static int getTaskNumberCount() {
        return taskNumberCount;
    }

    public static void setTaskNumberCount(int taskNumberCount) {
        Task.taskNumberCount = taskNumberCount;
    }
}
