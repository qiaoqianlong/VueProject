package com.qingge.springboot.mapper;

import com.qingge.springboot.entity.CourseGoalWeight;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-04-03
 */
public interface CourseGoalWeightMapper extends BaseMapper<CourseGoalWeight> {

    @Select("select * from course_goal_weight where teacher_id = #{teacherId} and course_id = #{courseId}")
    List<CourseGoalWeight> findAllByTeacherIdAndCourseId(@Param("teacherId") Integer teacherId, @Param("courseId") Integer courseId);

    List<CourseGoalWeight> findCourseGoal(@Param("courseId") Integer courseId, @Param("teacherId") Integer teacherId);
}
