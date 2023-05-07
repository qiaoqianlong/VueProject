package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.MajorCourseGoalScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingge.springboot.entity.ShowClazzInfo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-04-12
 */
public interface MajorCourseGoalScoreMapper extends BaseMapper<MajorCourseGoalScore> {

    Page<MajorCourseGoalScore> findPage(Page<MajorCourseGoalScore> page, String name, String courseName);

    List<ShowClazzInfo> findPagePlus(Integer courseId, Integer majorId);
}
