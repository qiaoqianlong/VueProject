package com.qingge.springboot.mapper;

import com.qingge.springboot.entity.CheckOutlineWeight;
import com.qingge.springboot.entity.CheckTeacherWeight;
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
 * @since 2022-04-03
 */
public interface CheckTeacherWeightMapper extends BaseMapper<CheckTeacherWeight> {

    @Select("select * from check_teacher_weight where  teacher_id = #{teacherId} and course_id = #{courseId}")
    List<CheckTeacherWeight> findAllByTeacherIdAndCourseId(Integer teacherId, Integer courseId);
}
