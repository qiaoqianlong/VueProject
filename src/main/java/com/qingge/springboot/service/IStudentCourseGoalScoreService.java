package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.CourseGoalWithScore;
import com.qingge.springboot.entity.StudentCourseGoalScore;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-10
 */
public interface IStudentCourseGoalScoreService extends IService<StudentCourseGoalScore> {

    Page<StudentCourseGoalScore> findPage(Page<StudentCourseGoalScore> objectPage, String name, String courseName,Integer teacherId);

    List<CourseGoalWithScore> findPagePlus(Integer courseId, Integer studentId);

    List<CourseGoalWithScore> findPagePlusX(Integer courseId, Integer studentId);

    List<Double> selectByIds(Integer courseId, Integer clazzId);

    void stuCourseGoalScore(Integer studentId, Integer courseId);

    List<StudentCourseGoalScore> findOneCourseGoalInfo(Integer courseGoalId, Integer courseId ,Integer clazzId);

    Page<StudentCourseGoalScore> findAllPage(Page<StudentCourseGoalScore> objectPage, String name, String courseName);
}
