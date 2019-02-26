package model;

public class CriteriaDTO {
    private int echid;
    private int ecid;
    private String criteriaHeadingName;
    private String criteriaName;

    public int getEchid() {
        return echid;
    }

    public void setEchid(int echid) {
        this.echid = echid;
    }

    public int getEcid() {
        return ecid;
    }

    public void setEcid(int ecid) {
        this.ecid = ecid;
    }

    public String getCriteriaHeadingName() {
        return criteriaHeadingName;
    }

    public void setCriteriaHeadingName(String criteriaHeadingName) {
        this.criteriaHeadingName = criteriaHeadingName;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }
}
