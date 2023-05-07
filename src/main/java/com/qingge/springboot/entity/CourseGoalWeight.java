package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2022-04-03
 */
@Getter
@Setter
  @TableName("course_goal_weight")
@ApiModel(value = "CourseGoalWeight对象", description = "")
public class CourseGoalWeight implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("课程目标id")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

  @ApiModelProperty("教师id")
  private Integer teacherId ;
  @ApiModelProperty("课程id")
  private Integer courseId ;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("参数")
      private Double weight;


}
