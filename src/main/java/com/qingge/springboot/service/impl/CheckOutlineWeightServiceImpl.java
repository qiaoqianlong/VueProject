package com.qingge.springboot.service.impl;

import com.qingge.springboot.entity.CheckOutlineWeight;
import com.qingge.springboot.entity.CourseGoalWeight;
import com.qingge.springboot.mapper.CheckOutlineWeightMapper;
import com.qingge.springboot.mapper.CourseGoalWeightMapper;
import com.qingge.springboot.service.ICheckOutlineWeightService;
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
 * @since 2022-04-03
 */
@Service
public class CheckOutlineWeightServiceImpl extends ServiceImpl<CheckOutlineWeightMapper, CheckOutlineWeight> implements ICheckOutlineWeightService {

    @Resource
    private CheckOutlineWeightMapper checkOutlineWeightMapper;
    @Override
    public List<CheckOutlineWeight> findAllByTeacherIdAndCourseId(Integer teacherId, Integer courseId) {
        return checkOutlineWeightMapper.findAllByTeacherIdAndCourseId( teacherId,  courseId);
    }
}
