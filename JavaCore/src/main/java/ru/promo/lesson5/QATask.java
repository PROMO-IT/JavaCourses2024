package ru.promo.lesson5;

public class QATask extends Task {
    private String priority;
    private boolean needFix;

    public QATask(String description) {
        super(description);
    }

    @Override
    public String addTask(Employee employee) {
        String status = super.addTask(employee);
        System.out.println("QA need to set priority to task: " + getNumber());
        return status;
    }

    @Override
    public void close() {
        setStatus("Closed");
        if (needFix) {
            System.out.println("Task " + getNumber() + " need fixes");
        } else {
            System.out.println("Task finished");
        }
    }

    public void reportBug(String priority) {
        needFix = true;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "QATask{" +
                "priority='" + priority + '\'' +
                ", needFix=" + needFix +
                '}';
    }
}
