package edu.polytech.springappb;

public class LogModel {
    public LogModel(String log) {
        this.log = log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
    }

    private String log;

    @Override
    public String toString() {
        return log;
    }

}
