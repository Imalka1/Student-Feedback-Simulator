package model;

public class SubjectLecturer {
    private int sublecid;
    private String subid;
    private String lecid;
    private boolean current;

    public int getSublecid() {
        return sublecid;
    }

    public void setSublecid(int sublecid) {
        this.sublecid = sublecid;
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    public String getLecid() {
        return lecid;
    }

    public void setLecid(String lecid) {
        this.lecid = lecid;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
