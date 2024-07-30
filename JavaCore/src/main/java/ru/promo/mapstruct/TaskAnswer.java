package ru.promo.mapstruct;

import java.time.LocalDateTime;

public class TaskAnswer {

    private int number;

    private String status;

    private String description;

    private String employee;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public TaskAnswer(int number, String status, String description, String employee, LocalDateTime createDate, LocalDateTime updateDate) {
        this.number = number;
        this.status = status;
        this.description = description;
        this.employee = employee;
        this.createDate = createDate;
        this.updateDate = updateDate;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "TaskAnswer{" +
                "number=" + number +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", employee='" + employee + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
