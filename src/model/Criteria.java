package model;

public class Criteria {
    private int ecId;
    private String criteriaHeadingName;
    private String criteriaName;
    private boolean criteriaHeading;

    public int getEcId() {
        return ecId;
    }

    public void setEcId(int ecId) {
        this.ecId = ecId;
    }

    public boolean isCriteriaHeading() {
        return criteriaHeading;
    }

    public void setCriteriaHeading(boolean criteriaHeading) {
        this.criteriaHeading = criteriaHeading;
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
