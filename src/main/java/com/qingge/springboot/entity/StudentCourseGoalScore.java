package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wano
 * @since 2022-04-10
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("student_course_goal_score")
@ApiModel(value = "StudentCourseGoalScore对象", description = "")
public class StudentCourseGoalScore implements Serializable {

  private static final long serialVersionUID = 1L;
  public StudentCourseGoalScore(Integer teacherId,Integer clazzId, Integer studentId, Integer courseId, Integer courseGoalId, Double score) {
    this.teacherId = teacherId;
    this.clazzId = clazzId;
    this.studentId = studentId;
    this.courseId = courseId;
    this.courseGoalId = courseGoalId;
    this.score = score;
  }
  @ApiModelProperty("原始成绩ID")
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @ApiModelProperty("教师id")
  private Integer teacherId;

  @ApiModelProperty("班级id")
  private Integer clazzId;

  @ApiModelProperty("学生ID")
  private Integer studentId;

  @ApiModelProperty("课程id")
  private Integer courseId;

  @ApiModelProperty("课程目标参数ID")
  private Integer courseGoalId;

  @ApiModelProperty("课程目标达成度")
  private Double score;

  @ApiModelProperty("创建时间")
  private LocalDateTime createTime;

  @TableField(exist = false)
  private String studentCode;
  @TableField(exist = false)
  private String studentName;
  @TableField(exist = false)
  private String courseName;
  @TableField(exist = false)
  private String courseGoalWeightName;
}
