package com.qingge.springboot.mapper;

import com.qingge.springboot.entity.CheckOutlineWeight;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingge.springboot.entity.CourseGoalWeight;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-04-03
 */
public interface CheckOutlineWeightMapper extends BaseMapper<CheckOutlineWeight> {

    @Select("select * from check_outline_weight where teacher_id = #{teacherId} and course_id = #{courseId}")
    List<CheckOutlineWeight> findAllByTeacherIdAndCourseId(Integer teacherId, Integer courseId);
}
