package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.CheckOutlineWeight;
import com.qingge.springboot.entity.CheckTeacherWeight;
import com.qingge.springboot.entity.CourseGoalWeight;
import com.qingge.springboot.entity.CourseWeights;
import com.qingge.springboot.mapper.CheckOutlineWeightMapper;
import com.qingge.springboot.mapper.CheckTeacherWeightMapper;
import com.qingge.springboot.mapper.CourseGoalWeightMapper;
import com.qingge.springboot.mapper.CourseWeightsMapper;
import com.qingge.springboot.service.ICourseWeightsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-04-01
 */
@Service
public class CourseWeightsServiceImpl extends ServiceImpl<CourseWeightsMapper, CourseWeights> implements ICourseWeightsService {

    @Resource
    private CourseWeightsMapper courseWeightsMapper;

    @Override
    public Page<CourseWeights> findPage(Page<CourseWeights> page,String name,Integer teacherId) {
        return courseWeightsMapper.findPage(page,name,teacherId);
    }

    @Override
    public List<CourseWeights> findAllByTeacherIdAndCourseId(Integer teacherId, Integer courseId) {
        return courseWeightsMapper.findAllByTeacherIdAndCourseId(teacherId,courseId);
    }

}
