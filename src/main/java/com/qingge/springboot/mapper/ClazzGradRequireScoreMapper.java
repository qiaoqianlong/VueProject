package com.qingge.springboot.mapper;

import com.qingge.springboot.entity.ClazzGradRequireScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingge.springboot.entity.Point;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-04-18
 */
public interface ClazzGradRequireScoreMapper extends BaseMapper<ClazzGradRequireScore> {

    List<ClazzGradRequireScore> ShowClazzGradRequireScore(@Param("id") Integer id);

    List<Point> FindClazzPoint(@Param("id") Integer ClazzId);
}
