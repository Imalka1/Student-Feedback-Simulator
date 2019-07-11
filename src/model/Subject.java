package model;

public class Subject {
    private String subjectId;
    private int sublecId;
    private int degid;
    private int semid;
    private String subjectName;
    private int credits;
    private String lecturerName;

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public int getSublecId() {
        return sublecId;
    }

    public void setSublecId(int sublecId) {
        this.sublecId = sublecId;
    }

    public int getDegid() {
        return degid;
    }

    public void setDegid(int degid) {
        this.degid = degid;
    }

    public int getSemid() {
        return semid;
    }

    public void setSemid(int semid) {
        this.semid = semid;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
