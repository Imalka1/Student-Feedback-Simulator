package model;

public class Degree {
    private int degid;
    private int facid;
    private String facultyName;
    private String degreeName;

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

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }
}
