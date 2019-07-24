package model;

public class Mark {
    private String uid;
    private int ecid;
    private String dateOfSubmitted;
    private int sublecid;
    private int marks;

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getEcid() {
        return ecid;
    }

    public void setEcid(int ecid) {
        this.ecid = ecid;
    }

    public String getDateOfSubmitted() {
        return dateOfSubmitted;
    }

    public void setDateOfSubmitted(String dateOfSubmitted) {
        this.dateOfSubmitted = dateOfSubmitted;
    }

    public int getSublecid() {
        return sublecid;
    }

    public void setSublecid(int sublecid) {
        this.sublecid = sublecid;
    }
}
