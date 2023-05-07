package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Course;
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
public interface CourseMapper extends BaseMapper<Course> {

    //@Param 与Mapper.xml 中的变量名#{name} 对应
    Page<Course> findPage(Page<Course> page, @Param("name") String name, @Param("type") String type);

    @Select("select DISTINCT  type from course")
    List<String> findCourseTypes();

    @Select("select name from course where id = #{id}")
    String getCourseNameById(@Param("id") Integer id);
}
