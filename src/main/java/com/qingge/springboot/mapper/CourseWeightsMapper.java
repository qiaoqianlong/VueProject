package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.CourseWeights;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-04-01
 */
public interface CourseWeightsMapper extends BaseMapper<CourseWeights> {

    Page<CourseWeights> findPage(@Param("page") Page<CourseWeights> page,
                                 @Param("name") String name, @Param("teacherId") Integer teacherId);

//    @Select("select * from course_weights where teacher_id = #{teacherId} and course_id = #{courseId}")
    List<CourseWeights> findAllByTeacherIdAndCourseId(@Param("teacherId") Integer teacherId, @Param("courseId") Integer courseId);

    @Select("select distinct course_goal_id from course_weights where point_id = #{pointId} and course_id = #{courseId}")
    Integer getCourseGoalIdByPointAndCourse(@Param("pointId") Integer pointId, @Param("courseId") Integer courseId);

    @Select("select distinct course_id,course_goal_id from course_weights where point_id = #{pointId}")
    List<CourseWeights> selectListById(@Param("pointId") Integer pointId);

    // 根据ID获取对象
//    CourseWeights getCourseWeightsAndScore(@Param("courseWeightsId") Integer courseWeightsId);
}
