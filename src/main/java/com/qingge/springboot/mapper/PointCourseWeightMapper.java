package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.PointCourseWeight;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 课程指标点矩阵表 Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-04-08
 */
public interface PointCourseWeightMapper extends BaseMapper<PointCourseWeight> {

    @Select("select weight from point_course_weight where point_id = #{pointId} and course_id = #{courseId}")
    Double selectWeightByPointAndCourse(@Param("pointId") Integer pointId, @Param("courseId") Integer courseId);

    Page<PointCourseWeight> findPage(Page<Object> page, @Param("pointId") Integer pointId);
}
