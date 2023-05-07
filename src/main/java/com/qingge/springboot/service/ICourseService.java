package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.controller.dto.CourseReviewDTO;
import com.qingge.springboot.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-03-31
 */
public interface ICourseService extends IService<Course> {

    Page<Course> findPage(Page<Course> page, String name,String type);

    List<String> findCourseTypes();

    List<CourseReviewDTO> coursesByTeacherId(Integer teacherId);
}
