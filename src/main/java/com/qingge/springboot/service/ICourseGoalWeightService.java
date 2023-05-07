package com.qingge.springboot.service;

import com.qingge.springboot.entity.CourseGoalWeight;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-03
 */
public interface ICourseGoalWeightService extends IService<CourseGoalWeight> {

    List<CourseGoalWeight> findAllByTeacherIdAndCourseId(Integer teacherId, Integer courseId);

    List<CourseGoalWeight> findCourseGoal(Integer courseId, Integer teacherId);
}
