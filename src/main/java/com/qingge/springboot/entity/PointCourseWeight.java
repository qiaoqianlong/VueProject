package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程指标点矩阵表
 * </p>
 *
 * @author wano
 * @since 2022-04-08
 */
@Data
@TableName("point_course_weight")
@ApiModel(value = "PointCourseWeight对象", description = "课程指标点矩阵表")
public class PointCourseWeight implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程指标点权重id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("指标点id")
    private Integer pointId;

    @ApiModelProperty("课程id")
    private Integer courseId;

    private Double weight;

    @TableField(exist = false)
    private String pointName;
    @TableField(exist = false)
    private String courseName;
}
