package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Clazz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-03-31
 */
public interface ClazzMapper extends BaseMapper<Clazz> {

    Page<Clazz> findPage(Page<Clazz> page, @Param("name") String name);

    @Select("select major_id from clazz where id = #{clazzId}")
    Integer selectMajorIdByClazzId(@Param("clazzId") Integer clazzId);

    Page<Clazz> ShowClazzGradRequire(@Param("page") Page<Clazz> page,@Param("name") String name,@Param("majorName") String majorName);
}
