package com.qingge.springboot.service;

import com.qingge.springboot.entity.StudentGradRequireScore;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-18
 */
public interface IStudentGradRequireScoreService extends IService<StudentGradRequireScore> {

    void studentGraduateRequireScore(Integer studentId);

    void studentOnePointScore(Integer pointId,Integer studentId);

    List<StudentGradRequireScore> ShowStuGradRequireScore(Integer studentId);

    List<StudentGradRequireScore> FindClazzOnePointInfo(Integer pointId, Integer clazzId);
}
