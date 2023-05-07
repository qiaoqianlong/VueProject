package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.MajorCourseGoalScore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.entity.ShowClazzInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-12
 */
public interface IMajorCourseGoalScoreService extends IService<MajorCourseGoalScore> {

    Page<MajorCourseGoalScore> findPage(Page<MajorCourseGoalScore> Page, String name, String courseName);

    List<ShowClazzInfo> findPagePlus(Integer courseId, Integer majorId);

    void majorCourseGoalScore(Integer majorId, Integer courseId, String grade);
}
