package com.qingge.springboot.service;

import com.qingge.springboot.entity.ClazzGradRequireScore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.entity.Point;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-18
 */
public interface IClazzGradRequireScoreService extends IService<ClazzGradRequireScore> {

    void calculateClazzGradRequireScore(Integer clazzId);

    List<ClazzGradRequireScore> ShowClazzGradRequireScore(Integer id);

    List<Point> FindClazzPoint(Integer clazzId);
}
