package com.qingge.springboot.mapper;
import com.qingge.springboot.entity.CourseGoalWithScore;
import com.qingge.springboot.entity.StudentCourseGoalScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-04-10
 */
public interface StudentCourseGoalScoreMapper extends BaseMapper<StudentCourseGoalScore> {

    Page<StudentCourseGoalScore> findPage(@Param("page") Page<StudentCourseGoalScore> page, @Param("name") String name, @Param("courseName") String courseName,@Param("teacherId") Integer teacherId);
    Page<StudentCourseGoalScore> findAllPage(@Param("page") Page<StudentCourseGoalScore> page, @Param("name") String name, @Param("courseName") String courseName);

    List<CourseGoalWithScore> findPagePlus(@Param("courseId")Integer courseId, @Param("studentId") Integer studentId);

    List<CourseGoalWithScore> findPagePlusX(@Param("courseId")Integer courseId, @Param("studentId") Integer studentId);

    List<Double> selectByIds(@Param("courseId") Integer courseId, @Param("clazzId") Integer clazzId);

    @Select("select score from student_course_goal_score where course_goal_id = #{courseGoalId} and student_id = #{studentId}")
    Double getScoreByCourseGoalAndStudent(@Param("courseGoalId") Integer courseGoalId, @Param("studentId") Integer studentId);

    List<StudentCourseGoalScore> findOneCourseGoalInfo(@Param("courseGoalId") Integer courseGoalId,@Param("courseId") Integer courseId, @Param("clazzId") Integer clazzId);
}
