package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-01
 */
public interface IScoreService extends IService<Score> {

    Page<Score> findPage(Page<Score> page, String name,
                         String courseName, String teacherCheckName,
                         Integer id, Integer courseId, Integer teacherId);

    Double[] checkTeacherScore();
    // 计算 教师考核项 成绩

    // 计算 大纲考核项 成绩

    // 计算 课程目标 达成度

}
