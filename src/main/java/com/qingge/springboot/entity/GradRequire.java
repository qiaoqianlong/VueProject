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
 * @since 2022-03-31
 */
@Getter
@Setter
  @TableName("grad_require")
@ApiModel(value = "GradRequire对象", description = "")
public class GradRequire implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("毕业要求ID")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("专业id")
      private Integer majorId;

      @ApiModelProperty("编号")
      private String code;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("描述")
      private String description;


}
