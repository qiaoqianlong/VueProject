package com.qingge.springboot.mapper;

import com.qingge.springboot.entity.GradRequire;
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
public interface GradRequireMapper extends BaseMapper<GradRequire> {

    @Select("select id from grad_require where major_id = #{majorId}")
    List<Integer> listIds(@Param("majorId") Integer majorId);

    @Select("select name from grad_require where id = #{gradRequireId}")
    String getGradRequireNameById(@Param("gradRequireId") Integer gradRequireId);
}
