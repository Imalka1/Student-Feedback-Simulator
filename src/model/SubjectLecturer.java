package model;

public class SubjectLecturer {
    private int subjectLecturerId;
    private String subjectId;
    private String lecturerId;
    private boolean current;

    public int getSubjectLecturerId() {
        return subjectLecturerId;
    }

    public void setSubjectLecturerId(int subjectLecturerId) {
        this.subjectLecturerId = subjectLecturerId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
