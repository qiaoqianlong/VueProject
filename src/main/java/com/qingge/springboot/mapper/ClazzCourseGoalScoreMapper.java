package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.ClazzCourseGoalScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingge.springboot.entity.CourseGoalWithScore;
import com.qingge.springboot.entity.ShowClazzInfo;
import com.qingge.springboot.entity.StudentCourseGoalScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-04-11
 */
public interface ClazzCourseGoalScoreMapper extends BaseMapper<ClazzCourseGoalScore> {

    Page<ClazzCourseGoalScore> findPage(@Param("page") Page<ClazzCourseGoalScore> page, @Param("name") String name, @Param("courseName") String courseName);

    List<ShowClazzInfo> findPagePlus(Integer courseId, Integer clazzId);
}
