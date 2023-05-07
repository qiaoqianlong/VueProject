package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Student;
import com.qingge.springboot.entity.StudentGradRequireScore;
import com.qingge.springboot.mapper.StudentMapper;
import com.qingge.springboot.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-03-31
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Page<Student> findPage(Page<Student> page, String name, String clazzName) {
        return studentMapper.findPage(page,name,clazzName);
    }

    @Override
    public Page<StudentGradRequireScore> findStudentInfo(Page<StudentGradRequireScore> Page, String name, String clazzName) {
        return studentMapper.findStudentInfo(Page,name,clazzName);
    }
}
