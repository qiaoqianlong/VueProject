package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Score;
import com.qingge.springboot.mapper.ScoreMapper;
import com.qingge.springboot.service.IScoreService;
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
 * @since 2022-04-01
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {
    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private IScoreService scoreService;

    @Override
    public Page<Score> findPage(Page<Score> page, String name, String courseName, String teacherCheckName, Integer teacherId, Integer courseId, Integer checkTeacherId) {
        return scoreMapper.findPage(page,name,courseName,teacherCheckName,teacherId,courseId,checkTeacherId);
    }

    @Override
    public Double[] checkTeacherScore(){
        // 获取学生的所有成绩 加上限定条件
        List<Score> scores = scoreService.list();
        for (Score s:scores) {
            System.out.println(s.toString());
        }
        // 获取成绩对应的所有参数
        // 计算 教师考核项 成绩
        // 计算 大纲考核项 成绩
        // 计算 课程目标 达成度

        return null;
    }




}
