package ru.promo.mapstruct.mapstruct0;

public class Task {

    private int number;

    private String status;

    private String description;

    private String employee;

    public Task(int number, String status, String description, String employee) {
        this.number = number;
        this.status = status;
        this.description = description;
        this.employee = employee;
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

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
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
}
