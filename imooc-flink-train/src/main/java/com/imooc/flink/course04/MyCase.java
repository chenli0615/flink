package com.imooc.flink.course04;

public class MyCase {
    private String stepId;
    private String col1;
    private String col2;

    @Override
    public String toString() {
        return "MyCase{" +
                "stepId='" + stepId + '\'' +
                ", col1='" + col1 + '\'' +
                ", col2='" + col2 + '\'' +
                '}';
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }
}
