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
import lombok.Setter;

/**
 * <p>
 * 课程教师班级关系表
 * </p>
 *
 * @author wano
 * @since 2022-04-01
 */
@Getter
@Setter
  @TableName("course_schedule")
@ApiModel(value = "CourseSchedule对象", description = "课程教师班级关系表")
public class CourseSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程计划id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("课程id")
    private Integer courseId;

    @ApiModelProperty("教师id")
    private Integer teacherId;

    @ApiModelProperty("班级id")
    private Integer clazzId;

    @ApiModelProperty("审核状态")  // 0 未审核 1 审核通过 2 审核未通过
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private String course;

    @TableField(exist = false)
    private String teacher;

    @TableField(exist = false)
    private String clazz;

}
