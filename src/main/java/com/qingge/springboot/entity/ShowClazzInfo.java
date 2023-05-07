package com.qingge.springboot.entity;

public class ShowClazzInfo {
    private String courseGoalName;
    private double courseGoalScore;
    private int fieldOneCount;
    private int fieldTwoCount;
    private int fieldThreeCount;
    private int fieldFourCount;
    private int fieldFiveCount;

    public String getCourseGoalName() {
        return courseGoalName;
    }

    public void setCourseGoalName(String courseGoalName) {
        this.courseGoalName = courseGoalName;
    }

    public double getCourseGoalScore() {
        return courseGoalScore;
    }

    public void setCourseGoalScore(double courseGoalScore) {
        this.courseGoalScore = courseGoalScore;
    }

    public int getFieldOneCount() {
        return fieldOneCount;
    }

    public void setFieldOneCount(int fieldOneCount) {
        this.fieldOneCount = fieldOneCount;
    }

    public int getFieldTwoCount() {
        return fieldTwoCount;
    }

    public void setFieldTwoCount(int fieldTwoCount) {
        this.fieldTwoCount = fieldTwoCount;
    }

    public int getFieldThreeCount() {
        return fieldThreeCount;
    }

    public void setFieldThreeCount(int fieldThreeCount) {
        this.fieldThreeCount = fieldThreeCount;
    }

    public int getFieldFourCount() {
        return fieldFourCount;
    }

    public void setFieldFourCount(int fieldFourCount) {
        this.fieldFourCount = fieldFourCount;
    }

    public int getFieldFiveCount() {
        return fieldFiveCount;
    }

    public void setFieldFiveCount(int fieldFiveCount) {
        this.fieldFiveCount = fieldFiveCount;
    }

    @Override
    public String toString() {
        return "ShowClazzInfo{" +
                "courseGoalName='" + courseGoalName + '\'' +
                ", courseGoalScore=" + courseGoalScore +
                ", fieldOneCount=" + fieldOneCount +
                ", fieldTwoCount=" + fieldTwoCount +
                ", fieldThreeCount=" + fieldThreeCount +
                ", fieldFourCount=" + fieldFourCount +
                ", fieldFiveCount=" + fieldFiveCount +
                '}';
    }
}
