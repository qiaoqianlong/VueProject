package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.ClazzCourseGoalScore;
import com.qingge.springboot.entity.CourseSchedule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.entity.MajorCourseGoalScore;
import com.qingge.springboot.entity.StudentCourseGoalScore;

/**
 * <p>
 * 角色菜单关系表 服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-01
 */
public interface ICourseScheduleService extends IService<CourseSchedule> {

    Page<CourseSchedule> findPage(Page<CourseSchedule> page, Integer courseId,Integer teacherId,Integer clazzId,String name);

    Page<CourseSchedule> findPageByTeacherId(Page<Object> page, Integer teacherId);

    Page<StudentCourseGoalScore> findStudentCourseInfo(Page<StudentCourseGoalScore> Page, String name, String courseName);

    Page<ClazzCourseGoalScore> findClazzCourseInfo(Page<ClazzCourseGoalScore> Page, String name, String courseName, Integer teacherId);

    Page<MajorCourseGoalScore> findMajorCourseInfo(Page<MajorCourseGoalScore> Page, String name, String courseName);

    Page<ClazzCourseGoalScore> findAllClazzCourseInfo(Page<ClazzCourseGoalScore> Page, String name, String courseName);
}
