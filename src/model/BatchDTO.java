package model;

public class BatchDTO {
    private int degid;
    private int facid;
    private String batchName;
    private int monthDiff;
    private int yearDiff;

    public int getMonthDiff() {
        return monthDiff;
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

    public int getDegid() {
        return degid;
    }

    public void setDegid(int degid) {
        this.degid = degid;
    }

    public int getFacid() {
        return facid;
    }

    public void setFacid(int facid) {
        this.facid = facid;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
}
