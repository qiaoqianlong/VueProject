package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wano
 * @since 2022-04-27
 */
@Getter
@Setter
  @TableName("unit_questionnaire_count")
@ApiModel(value = "UnitQuestionnaireCount对象", description = "")
public class UnitQuestionnaireCount implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("企业名称")
      private String enterprisename;

      @ApiModelProperty("企业性质")
      private Integer enterprisenature;

      @ApiModelProperty("企业类型")
      private Integer enterprisetype;

    private Integer title1;

    private Integer title2;

    private Integer title3;

    private Integer title4;

    private Integer title5;

    private Integer title6;

    private Integer title7;

    private Integer title8;

    private Integer title9;

    private String title10_1;
    private String title10_2;
    private String title10_3;
    private String title10_4;
    private String title10_5;
    private String title10_6;

    private String title11;


}
