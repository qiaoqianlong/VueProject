package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.PointCourseWeight;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程指标点矩阵表 服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-08
 */
public interface IPointCourseWeightService extends IService<PointCourseWeight> {

    Page<PointCourseWeight> findPage(Page<Object> page,Integer pointId);
}
