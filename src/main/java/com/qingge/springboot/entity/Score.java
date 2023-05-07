package com.qingge.springboot.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author wano
 * @since 2022-04-01
 */
@Data
@ApiModel(value = "Score对象", description = "")
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty("原始成绩ID")
        @TableId(value = "id", type = IdType.AUTO)
        private Integer id;

        @ApiModelProperty("教师id")
        private Integer teacherId;

        @ApiModelProperty("班级id")
        private Integer clazzId;

        @ApiModelProperty("学生ID")
        private Integer studentId;

        @ApiModelProperty("课程ID")
        private Integer courseId;

        @ApiModelProperty("教师考核项ID")
        private Integer checkTeacherWeightId;

        @ApiModelProperty("成绩")
        private Double score;

        @ApiModelProperty("创建时间")
        private LocalDateTime createTime;

        // 学生 学号、姓名
        @TableField(exist = false)
        private String studentCode;
        @TableField(exist = false)
        private String studentName;
        @TableField(exist = false)
        private String checkTeacherName;
        @TableField(exist = false)
        private String courseName;
}
