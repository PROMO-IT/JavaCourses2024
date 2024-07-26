package ru.promo.mapstruct.mapstruct2;

public class Task {

    private int number;

    private String status;

    private String description;

    private Employee employee;

    public Task(int number, String status, String description, Employee employee) {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
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
