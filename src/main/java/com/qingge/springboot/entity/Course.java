package com.qingge.springboot.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-03-31
 */
@Getter
@Setter
  @ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("专业ID")
      private Integer majorId;

      @ApiModelProperty("课程ID")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("类型")
      private String type;

      @ApiModelProperty("性质")
      private String properties;

      @ApiModelProperty("开课学期")
      private Integer term;

      @ApiModelProperty("学分")
      private Double credit;

      @ApiModelProperty("理论学时")
      private Double theoryHour;

      @ApiModelProperty("实践学时")
      private Double expHour;

      @ApiModelProperty("总学时")
      private Double totalHour;

      @TableField(exist = false)
      private String major;

}
