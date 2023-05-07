package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.ClazzCourseGoalScore;
import com.qingge.springboot.entity.CourseSchedule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingge.springboot.entity.MajorCourseGoalScore;
import com.qingge.springboot.entity.StudentCourseGoalScore;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色菜单关系表 Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-04-01
 */
public interface CourseScheduleMapper extends BaseMapper<CourseSchedule> {

    Page<CourseSchedule> findPage(Page<CourseSchedule> page, @Param("courseId") Integer courseId, @Param("teacherId") Integer teacherId, @Param("clazzId") Integer clazzId, @Param("name") String name);


    @Select("select course_id from course_schedule where teacher_id = #{teacherId}")
    List<Integer> selectByTeacherId(@Param("teacherId") Integer teacherId);

    @Select("select teacher_id from course_schedule where course_id = #{courseId} and clazz_id = #{clazzId}")
    Integer selectTeacherIdByIds(@Param("courseId") Integer courseId, @Param("clazzId") Integer clazzId);

    @Select("select distinct status from course_schedule where course_id = #{courseId} and teacher_id = #{teacherId}")
    Integer getStatusByIds(@Param("teacherId") Integer teacherId, @Param("courseId") Integer courseId);

    @Select("select course_id from course_schedule where course_id = #{courseId} and teacher_id = #{teacherId}")
    Integer getClazzIdByIds(@Param("teacherId") Integer teacherId, @Param("courseId") Integer courseId);

    @Select("select teacher_id as teacherId from course_schedule where clazz_id = #{clazzId} and course_id = #{courseId}")
    Integer selectTeacherIdByClazzIdCourseId(@Param("clazzId") Integer clazzId, @Param("courseId") Integer courseId);

    Page<CourseSchedule> findPageByTeacherId(@Param("page") Page<Object> page, @Param("teacherId") Integer teacherId);

    Page<StudentCourseGoalScore> findStudentCourseInfo(@Param("page") Page<StudentCourseGoalScore> page,@Param("name") String name,@Param("courseName") String courseName);

    Page<ClazzCourseGoalScore> findClazzCourseInfo(@Param("page") Page<ClazzCourseGoalScore> page,@Param("name") String name,@Param("courseName") String courseName, @Param("teacherId") Integer teacherId);

    Page<MajorCourseGoalScore> findMajorCourseInfo( @Param("page") Page<MajorCourseGoalScore> page,@Param("name") String name,@Param("courseName") String courseName);

    Page<ClazzCourseGoalScore> findAllClazzCourseInfo(@Param("page") Page<ClazzCourseGoalScore> page,@Param("name") String name,@Param("courseName") String courseName);
}
