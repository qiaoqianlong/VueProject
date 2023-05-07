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
 * 
 * </p>
 *
 * @author wano
 * @since 2022-04-18
 */
@Getter
@Setter
  @TableName("clazz_grad_require_score")
@ApiModel(value = "ClazzGradRequireScore对象", description = "")
public class ClazzGradRequireScore implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("原始成绩ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("学生ID")
      private Integer majorId;

      @ApiModelProperty("班级id")
      private Integer clazzId;

      @ApiModelProperty("指标点id")
      private Integer pointId;

      @ApiModelProperty("毕业要求达成度")
      private Double score;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @TableField(exist = false)
      private String pointName;

      @TableField(exist = false)
      private String description;
}
