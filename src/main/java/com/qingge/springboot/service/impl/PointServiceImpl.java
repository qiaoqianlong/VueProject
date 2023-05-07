package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Point;
import com.qingge.springboot.mapper.GradRequireMapper;
import com.qingge.springboot.mapper.PointMapper;
import com.qingge.springboot.service.IPointService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-03-31
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements IPointService {

    @Resource
    private PointMapper pointMapper;
    @Resource
    private GradRequireMapper gradRequireMapper;


    @Override
    public Page<Point> findPage(Page<Point> page) {
        return pointMapper.findPage(page);
    }

    @Override
    public List<Point> findPointsByGrID(Integer grId) {
        List<Point> points = list(new QueryWrapper<Point>().eq("grad_require_id",grId));
        for (Point point: points) {
            point.setGradRequire(gradRequireMapper.getGradRequireNameById(point.getGradRequireId()));
        }
        return points;
    }
}
