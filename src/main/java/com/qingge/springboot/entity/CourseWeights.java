package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-04-01
 */
@Getter
@Setter
@TableName("course_weights")
@ApiModel(value = "CourseWeights对象", description = "")
public class CourseWeights implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("课前参数评价id")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("教师id")
      private Integer teacherId;

      @ApiModelProperty("指标点id")
      private Integer pointId;

      @ApiModelProperty("课程id")
      private Integer courseId;

      @ApiModelProperty("课程目标id")
      private Integer courseGoalId;

      @ApiModelProperty("大纲考核项id")
      private Integer checkOutlineId;

      @ApiModelProperty("教师考核项id")
      private Integer checkTeacherId;

      @ApiModelProperty("保存提交状态")
      private Integer status;

      @TableField(exist = false)
      private Double weightCourseGoal;
      @TableField(exist = false)
      private Double weightCheckOutline;
      @TableField(exist = false)
      private Double weightCheckTeacher;
      @TableField(exist = false)
      private String nameCourseGoal;
      @TableField(exist = false)
      private String nameCheckOutline;
      @TableField(exist = false)
      private String nameCheckTeacher;
      @TableField(exist = false)
      private String teacher;
      @TableField(exist = false)
      private String course;
      @TableField(exist = false)
      private String pointName;
}
