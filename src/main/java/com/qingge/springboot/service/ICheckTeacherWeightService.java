package com.qingge.springboot.service;

import com.qingge.springboot.entity.CheckTeacherWeight;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.entity.CourseGoalWeight;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-03
 */
public interface ICheckTeacherWeightService extends IService<CheckTeacherWeight> {


    List<CheckTeacherWeight> findAllByTeacherIdAndCourseId(Integer teacherId, Integer courseId);
}
