package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingge.springboot.entity.StudentGradRequireScore;
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
public interface StudentMapper extends BaseMapper<Student> {

    Page<Student> findPage(Page<Student> page, String name, String clazzName);

    @Select("select clazz_id from student where id = #{studentId}")
    Integer selectClazzIdByStudentId(@Param("studentId") Integer studentId);

    @Select("select major_id from student where id = #{studentId}")
    Integer selectMajorIdByStudentId(@Param("studentId") Integer studentId);

    Page<StudentGradRequireScore> findStudentInfo(@Param("page") Page<StudentGradRequireScore> page,@Param("name") String name,@Param("clazzName") String clazzName);
}
