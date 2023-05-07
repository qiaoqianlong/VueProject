package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.entity.StudentGradRequireScore;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-03-31
 */
public interface IStudentService extends IService<Student> {

    Page<Student> findPage(Page<Student> page, String name, String clazzName);

    Page<StudentGradRequireScore> findStudentInfo(Page<StudentGradRequireScore> Page, String name, String clazzName);
}
