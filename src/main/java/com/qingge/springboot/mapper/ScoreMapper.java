package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Score;
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
public interface ScoreMapper extends BaseMapper<Score> {

    Page<Score> findPage(Page<Score> page, @Param("name") String name, @Param("courseName") String courseName,
                         @Param("teacherCheckName") String teacherCheckName,
                         @Param("teacherId") Integer teacherId,
                         @Param("courseId") Integer courseId,
                         @Param("checkTeacherId") Integer checkTeacherId);

    @Select("select score from score where student_id = #{studentId} and course_id = #{courseId} and check_teacher_weight_id = #{checkTeacherId}")
    Double selectInitScoreByIds(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId, @Param("checkTeacherId") Integer checkTeacherId);
}
