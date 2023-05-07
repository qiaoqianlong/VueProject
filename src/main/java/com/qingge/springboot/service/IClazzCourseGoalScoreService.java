package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.ClazzCourseGoalScore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.entity.CourseGoalWithScore;
import com.qingge.springboot.entity.ShowClazzInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-11
 */
public interface IClazzCourseGoalScoreService extends IService<ClazzCourseGoalScore> {

    Page<ClazzCourseGoalScore> findPage(Page<ClazzCourseGoalScore> objectPage, String name, String courseName);

    List<ShowClazzInfo> findPagePlus(Integer courseId, Integer clazzId);

    void clazzCourseGoalScore(Integer clazzId, Integer courseId);
}
