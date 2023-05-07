package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.ClazzCourseGoalScore;
import com.qingge.springboot.entity.CourseSchedule;
import com.qingge.springboot.entity.MajorCourseGoalScore;
import com.qingge.springboot.entity.StudentCourseGoalScore;
import com.qingge.springboot.mapper.CourseScheduleMapper;
import com.qingge.springboot.service.ICourseScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 角色菜单关系表 服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-04-01
 */
@Service
public class CourseScheduleServiceImpl extends ServiceImpl<CourseScheduleMapper, CourseSchedule> implements ICourseScheduleService {

    @Resource
    private CourseScheduleMapper courseScheduleMapper;

    @Override
    public Page<CourseSchedule> findPage(Page<CourseSchedule> page, Integer courseId,Integer teacherId,Integer clazzId,String name) {
        return courseScheduleMapper.findPage(page, courseId,teacherId, clazzId,name);
    }

    @Override
    public Page<CourseSchedule> findPageByTeacherId(Page<Object> page, Integer teacherId) {
        return courseScheduleMapper.findPageByTeacherId(page,teacherId);
    }

    @Override
    public Page<StudentCourseGoalScore> findStudentCourseInfo(Page<StudentCourseGoalScore> Page, String name, String courseName) {
        return courseScheduleMapper.findStudentCourseInfo(Page, name, courseName);
    }

    @Override
    public Page<ClazzCourseGoalScore> findClazzCourseInfo(Page<ClazzCourseGoalScore> Page, String name, String courseName, Integer teacherId) {
        return courseScheduleMapper.findClazzCourseInfo(Page, name, courseName, teacherId);
    }

    @Override
    public Page<MajorCourseGoalScore> findMajorCourseInfo(Page<MajorCourseGoalScore> Page, String name, String courseName) {
        return courseScheduleMapper.findMajorCourseInfo(Page, name, courseName);
    }

    @Override
    public Page<ClazzCourseGoalScore> findAllClazzCourseInfo(Page<ClazzCourseGoalScore> Page, String name, String courseName) {
        return courseScheduleMapper.findAllClazzCourseInfo(Page, name, courseName);
    }
}
