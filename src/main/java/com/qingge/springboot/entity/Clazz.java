package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-03-31
 */
@Getter
@Setter
  @ApiModel(value = "Clazz对象", description = "")
public class Clazz implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("专业ID")
      private Integer majorId;

      @ApiModelProperty("班级ID")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("编号")
      private String code;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("年级")
      private String grade;

      @ApiModelProperty("学院")
      private String depart;

      @TableField(exist = false)
      private String major;

      @TableField(exist = false)
      private String majorName;

}
