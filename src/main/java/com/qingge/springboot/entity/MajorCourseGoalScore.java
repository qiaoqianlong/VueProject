package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wano
 * @since 2022-04-12
 */
@Getter
@Setter
@NoArgsConstructor
  @TableName("major_course_goal_score")
@ApiModel(value = "MajorCourseGoalScore对象", description = "")
public class MajorCourseGoalScore implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("专业课程目标达成度id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("年级")
      private String grade;

      @ApiModelProperty("专业id")
      private Integer majorId;

      @ApiModelProperty("课程id")
      private Integer courseId;

      @ApiModelProperty("课程目标id")
      private Integer courseGoalId;

      @ApiModelProperty("平均值")
      private Double average;

      @ApiModelProperty("区段一频次")
      private Integer fieldOneCount;

      @ApiModelProperty("区段二频次")
      private Integer fieldTwoCount;

      @ApiModelProperty("区段三频次")
      private Integer fieldThreeCount;

      @ApiModelProperty("区段四频次")
      private Integer fieldFourCount;

      @ApiModelProperty("区段五频次")
      private Integer fieldFiveCount;

      @TableField(exist = false)
      private String majorName;

      @TableField(exist = false)
      private String courseName;

  public MajorCourseGoalScore(Integer id, String grade, Integer majorId, Integer courseId, Integer courseGoalId, Double average, Integer fieldOneCount, Integer fieldTwoCount, Integer fieldThreeCount, Integer fieldFourCount, Integer fieldFiveCount) {
    this.id = id;
    this.grade = grade;
    this.majorId = majorId;
    this.courseId = courseId;
    this.courseGoalId = courseGoalId;
    this.average = average;
    this.fieldOneCount = fieldOneCount;
    this.fieldTwoCount = fieldTwoCount;
    this.fieldThreeCount = fieldThreeCount;
    this.fieldFourCount = fieldFourCount;
    this.fieldFiveCount = fieldFiveCount;
  }
}
