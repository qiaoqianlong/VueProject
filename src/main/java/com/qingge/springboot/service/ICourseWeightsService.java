package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.CourseWeights;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-01
 */
public interface ICourseWeightsService extends IService<CourseWeights> {

    Page<CourseWeights> findPage(Page<CourseWeights> page,String name,Integer teacherId);

    List<CourseWeights> findAllByTeacherIdAndCourseId(Integer teacherId, Integer courseId);

//    void saveWeights(CourseWeights courseWeights);
}
