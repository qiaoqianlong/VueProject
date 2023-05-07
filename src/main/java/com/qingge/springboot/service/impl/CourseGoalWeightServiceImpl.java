package com.qingge.springboot.service.impl;

import com.qingge.springboot.entity.CourseGoalWeight;
import com.qingge.springboot.mapper.CourseGoalWeightMapper;
import com.qingge.springboot.service.ICourseGoalWeightService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-04-03
 */
@Service
public class CourseGoalWeightServiceImpl extends ServiceImpl<CourseGoalWeightMapper, CourseGoalWeight> implements ICourseGoalWeightService {

    @Resource
    private CourseGoalWeightMapper courseGoalWeightMapper;
    @Override
    public List<CourseGoalWeight> findAllByTeacherIdAndCourseId(Integer teacherId, Integer courseId) {
        return courseGoalWeightMapper.findAllByTeacherIdAndCourseId( teacherId,  courseId);
    }

    @Override
    public List<CourseGoalWeight> findCourseGoal(Integer courseId, Integer teacherId) {
        return courseGoalWeightMapper.findCourseGoal(courseId,teacherId);
    }
}
