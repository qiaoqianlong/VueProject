package com.qingge.springboot.controller.dto;

import com.qingge.springboot.entity.Course;
import com.qingge.springboot.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * 接受前端登录请求的参数
 */
@Data
public class CourseReviewDTO {
    private Integer courseId;
    private String courseName;
    private Integer status;
}
