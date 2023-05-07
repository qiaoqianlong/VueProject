package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-03-30
 */
@Getter
@Setter
  @ApiModel(value = "Major对象", description = "")
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("专业ID")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("编号")
      private String code;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("描述")
      private String description;


}
