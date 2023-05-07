package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Point;
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
 * @since 2022-03-31
 */
public interface PointMapper extends BaseMapper<Point> {

    Page<Point> findPage(@Param("page") Page<Point> page);

    @Select("select id from point where grad_require_id = #{grId}")
    List<Integer> listIds(@Param("grId") Integer grId);
}
