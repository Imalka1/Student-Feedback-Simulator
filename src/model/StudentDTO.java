package model;

public class StudentDTO {
    private int stid;
    private String uid;
    private int degId;
    private int batchId;
    private String studentName;
    private String nationalId;
    private String batchName;

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getDegId() {
        return degId;
    }

    public void setDegId(int degId) {
        this.degId = degId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
