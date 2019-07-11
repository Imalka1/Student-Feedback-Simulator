package model;

public class Batch {
    private int batchid;
    private String intake;
    private int year;
    private String batchName;
    private int monthDiff;
    private int yearDiff;

    public int getMonthDiff() {
        return monthDiff;
    }

    public int getBatchid() {
        return batchid;
    }

    public void setBatchid(int batchid) {
        this.batchid = batchid;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public void setMonthDiff(int monthDiff) {
        this.monthDiff = monthDiff;
    }

    public int getYearDiff() {
        return yearDiff;
    }

    public void setYearDiff(int yearDiff) {
        this.yearDiff = yearDiff;
    }
}
