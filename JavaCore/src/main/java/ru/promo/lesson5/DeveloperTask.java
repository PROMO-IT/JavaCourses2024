package ru.promo.lesson5;

public class DeveloperTask extends Task {
    private String repositoryUrl;

    public DeveloperTask(String description, String repositoryUrl) {
        super(description);
        this.repositoryUrl = repositoryUrl;
    }

    @Override
    public String addTask(Employee employee) {
        String status = super.addTask(employee);
        System.out.println("Notify repository url: " + repositoryUrl);
        return status;
    }

    @Override
    public void close() {
        setStatus("Closed");
        System.out.println("Task merged to url: " + repositoryUrl);
    }
}
