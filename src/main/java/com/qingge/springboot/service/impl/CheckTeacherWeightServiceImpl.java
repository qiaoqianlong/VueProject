package com.qingge.springboot.service.impl;

import com.qingge.springboot.entity.CheckOutlineWeight;
import com.qingge.springboot.entity.CheckTeacherWeight;
import com.qingge.springboot.mapper.CheckOutlineWeightMapper;
import com.qingge.springboot.mapper.CheckTeacherWeightMapper;
import com.qingge.springboot.service.ICheckTeacherWeightService;
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
public class CheckTeacherWeightServiceImpl extends ServiceImpl<CheckTeacherWeightMapper, CheckTeacherWeight> implements ICheckTeacherWeightService {

    @Resource
    private CheckTeacherWeightMapper checkTeacherWeightMapper;

    @Override
    public List<CheckTeacherWeight> findAllByTeacherIdAndCourseId(Integer teacherId, Integer courseId) {
        return checkTeacherWeightMapper.findAllByTeacherIdAndCourseId( teacherId,  courseId);
    }
}
