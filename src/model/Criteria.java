package model;

public class Criteria {
    private int echId;
    private int ecId;
    private String criteriaHeadingName;
    private String criteriaName;

    public int getEchId() {
        return echId;
    }

    public void setEchId(int echId) {
        this.echId = echId;
    }

    public int getEcId() {
        return ecId;
    }

    public void setEcId(int ecId) {
        this.ecId = ecId;
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
