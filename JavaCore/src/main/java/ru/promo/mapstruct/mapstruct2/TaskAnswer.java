package ru.promo.mapstruct.mapstruct2;

import java.time.LocalDateTime;

public class TaskAnswer {

     private int number;

     private String status;

     private String description;

     private EmployeeAnswer employee;

     private LocalDateTime createDate;

     private LocalDateTime updateDate;


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

    public EmployeeAnswer getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeAnswer employee) {
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
