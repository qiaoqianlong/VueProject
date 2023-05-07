package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wano
 * @since 2022-04-10
 */
@Getter
@Setter
  @TableName("course_goal_score")
@ApiModel(value = "CourseGoalScore对象", description = "")
public class CourseGoalScore implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("原始成绩ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("学生ID")
      private Integer studentId;

      @ApiModelProperty("课程id")
      private Integer courseId;

      @ApiModelProperty("课程目标参数ID")
      private Integer courseGoalWeightId;

      @ApiModelProperty("课程目标达成度")
      private Double score;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;


}
