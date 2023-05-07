package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.PointCourseWeight;
import com.qingge.springboot.entity.Student;
import com.qingge.springboot.mapper.PointCourseWeightMapper;
import com.qingge.springboot.mapper.StudentMapper;
import com.qingge.springboot.service.IPointCourseWeightService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程指标点矩阵表 服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-04-08
 */
@Service
public class PointCourseWeightServiceImpl extends ServiceImpl<PointCourseWeightMapper, PointCourseWeight> implements IPointCourseWeightService {

    @Resource
    private PointCourseWeightMapper pointCourseWeightMapper;

    @Override
    public Page<PointCourseWeight> findPage(Page<Object> page,Integer pointId) {
            return pointCourseWeightMapper.findPage(page,pointId);
    }
}
