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
 * @since 2022-04-24
 */
@Getter
@Setter
  @TableName("questionnaire_count")
@ApiModel(value = "QuestionnaireCount对象", description = "")
public class QuestionnaireCount implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("年级")
      private String grade;

      @ApiModelProperty("姓名")
      private String name;

      @ApiModelProperty("电话")
      private String phone;

    private String title1;

    private String title2;

    private Integer title3;

    private Integer title4;

    private Integer title5;

    private Integer title6;

    private Integer title7;

    private Integer title8;

    private Integer title9;

    private Integer title10;

    private Integer title11;

    private Integer title13;

    private Integer title14;

    private Integer title15;

    private Integer title16;

    private Integer title17;

    private Integer title18_1;
    private Integer title18_2;
    private Integer title18_3;
    private Integer title18_4;
    private Integer title18_5;
    private Integer title18_6;
    private Integer title18_7;
    private Integer title18_8;
    private Integer title18_9;
    private Integer title18_10;
}
