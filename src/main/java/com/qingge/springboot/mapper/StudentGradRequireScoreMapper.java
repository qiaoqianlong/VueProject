package com.qingge.springboot.mapper;

import com.qingge.springboot.entity.StudentGradRequireScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface StudentGradRequireScoreMapper extends BaseMapper<StudentGradRequireScore> {

    List<StudentGradRequireScore> ShowStuGradRequireScore(@Param("studentId") Integer studentId);

    List<StudentGradRequireScore> FindClazzOnePointInfo(@Param("pointId") Integer pointId,@Param("clazzId") Integer clazzId);
}
