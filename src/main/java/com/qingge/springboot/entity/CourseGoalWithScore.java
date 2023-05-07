package com.qingge.springboot.entity;

import lombok.Data;

@Data
public class CourseGoalWithScore {
    private String courseGoalName;
    private double courseGoalScore;
    private double originalScore;
    private String ctwName;

}
