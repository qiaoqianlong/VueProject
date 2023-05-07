package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author wano
 * @since 2022-03-31
 */
@Data
@ApiModel(value = "Student对象", description = "")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;


      @ApiModelProperty("学生ID")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("专业ID")
      private Integer majorId;

      @ApiModelProperty("班级ID")
      private Integer clazzId;

      @ApiModelProperty("学号")
      private String code;

      @ApiModelProperty("姓名")
      private String name;


      @ApiModelProperty("性别")
      private String gender;

      @ApiModelProperty("学院")
      private String depart;

      @ApiModelProperty("年级")
      private String grade;

      @ApiModelProperty("电话")
      private String phone;

      @ApiModelProperty("邮箱")
      private String email;

    @ApiModelProperty("出生日期")
    private String birth;

    @ApiModelProperty("家庭地址")
    private String address;

    //      @DateTimeFormat(pattern="yyyy-MM-dd")
//      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
//      @ApiModelProperty("生日")
//      private Date birth;

  @TableField(exist = false)
  private String major;
  @TableField(exist = false)
  private String clazz;



}
