package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Point;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-03-31
 */
public interface IPointService extends IService<Point> {

    Page<Point> findPage(Page<Point> page);

    List<Point> findPointsByGrID(Integer grId);
}
