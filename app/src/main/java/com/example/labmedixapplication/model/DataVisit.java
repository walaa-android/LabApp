package com.example.labmedixapplication.model;

public class DataVisit {
    private String DayVisit;
    private String TimeVisit;
    private String LabName;

    public DataVisit(String dayVisit, String timeVisit, String labName) {
        DayVisit = dayVisit;
        TimeVisit = timeVisit;
        LabName = labName;
    }

    public DataVisit() {
    }

    public String getDayVisit() {
        return DayVisit;
    }

    public void setDayVisit(String dayVisit) {
        DayVisit = dayVisit;
    }

    public String getTimeVisit() {
        return TimeVisit;
    }

    public void setTimeVisit(String timeVisit) {
        TimeVisit = timeVisit;
    }

    public String getLabName() {
        return LabName;
    }

    public void setLabName(String labName) {
        LabName = labName;
    }
}
