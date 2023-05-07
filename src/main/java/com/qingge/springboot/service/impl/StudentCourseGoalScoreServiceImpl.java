package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.CourseGoalWithScore;
import com.qingge.springboot.entity.CourseWeights;
import com.qingge.springboot.entity.StudentCourseGoalScore;
import com.qingge.springboot.mapper.*;
import com.qingge.springboot.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-04-10
 */
@Service
public class StudentCourseGoalScoreServiceImpl extends ServiceImpl<StudentCourseGoalScoreMapper, StudentCourseGoalScore> implements IStudentCourseGoalScoreService {
    @Resource
    private StudentCourseGoalScoreMapper studentcourseGoalScoreMapper;
    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private CourseScheduleMapper courseScheduleMapper;
    @Resource
    private ICourseWeightsService courseWeightsService;
    @Resource
    private IStudentCourseGoalScoreService studentCourseGoalScoreService;

    @Override
    public Page<StudentCourseGoalScore> findPage(Page<StudentCourseGoalScore> Page, String name, String courseName,Integer teacherId) {
        return studentcourseGoalScoreMapper.findPage(Page,name,courseName,teacherId);
    }

    @Override
    public List<CourseGoalWithScore> findPagePlus(Integer courseId, Integer studentId) {
        return  studentcourseGoalScoreMapper.findPagePlus(courseId,studentId);
    }

    @Override
    public List<CourseGoalWithScore> findPagePlusX(Integer courseId, Integer studentId) {
        return  studentcourseGoalScoreMapper.findPagePlusX(courseId,studentId);
    }

    @Override
    public List<Double> selectByIds(Integer courseId, Integer clazzId) {
        return studentcourseGoalScoreMapper.selectByIds(courseId, clazzId);
    }

    @Override
    public void stuCourseGoalScore(Integer studentId, Integer courseId) {
        // 根据学生id，查询班级id
        Integer clazzId = studentMapper.selectClazzIdByStudentId(studentId);
        // 根据班级id和课程id，查询教师id
        Integer teacherId = courseScheduleMapper.selectTeacherIdByIds(courseId,clazzId);
        // 根据教师id、课程id，查询课程参数 教师考核项ids  1管理员 19数据结构
        List<CourseWeights> courseWeights = courseWeightsService.findAllByTeacherIdAndCourseId(teacherId,courseId);
        // 获取对象属性中不重复值ids
        List<Integer> courseGoalIds=  courseWeights.stream().map(CourseWeights::getCourseGoalId).distinct().collect(Collectors.toList());

        // map集合，计算 课程目标下的大纲考核项的参数和
        // <课程目标id,参数和> 给map初始化
        Map<Integer,Double> map = new HashMap<>();
        Double sum = Double.valueOf(0);
        for (Integer cgId:courseGoalIds){
            map.put(cgId,sum);
        }
        // 计算 课程目标下的大纲考核项的参数和
        for (Integer cgId: courseGoalIds) {
            for (int i = 0; i < courseWeights.size(); i++) {
                if (courseWeights.get(i).getCourseGoalId() == cgId){
                    sum = map.get(cgId);
                    sum = sum + courseWeights.get(i).getWeightCheckOutline();
                    map.put(cgId,sum);
                }
            }
        }
        // System.out.println(map);  // {7=0.25, 8=0.35, 14=0.4}

        // 默认教师考核项是1，直接计算课程目标达成度
        Double initScore = Double.valueOf(0);  // 学生一个教师考核项的原始成绩
        Double outlineScore = Double.valueOf(0);  // 学生一个大纲考核项成绩
        Double cgwSum = Double.valueOf(0);
        // map集合，存储 达成度计算结果
        // <课程目标id,课程目标达成度值>   给map初始化
        Map<Integer,Double> resMap = new HashMap<>();
        Double courseGoalScore = Double.valueOf(0);
        for (Integer cgId:courseGoalIds){
            resMap.put(cgId,courseGoalScore);
        }
        // 计算 课程目标达成度
        for (int i = 0; i < courseWeights.size(); i++) {
            try {
                // 根据学生id、课程id、教师考核项id 获取一个学生的原始成绩
                initScore = scoreMapper.selectInitScoreByIds(studentId, courseId, courseWeights.get(i).getCheckTeacherId());
                // 计算 大纲考核项成绩
                outlineScore = initScore * courseWeights.get(i).getWeightCheckTeacher();
                // 根据 课程目标id 得到参数和
                cgwSum = map.get(courseWeights.get(i).getCourseGoalId());
                courseGoalScore = resMap.get(courseWeights.get(i).getCourseGoalId());
                // 得到 （归一化后的）成绩 ==》 课程目标达成度
                courseGoalScore += outlineScore * (courseWeights.get(i).getWeightCheckOutline() / (cgwSum * 100));
                // 将课程目标达成度保存到resMap中   <课程目标id,课程目标达成度值>
                resMap.put(courseWeights.get(i).getCourseGoalId(),courseGoalScore);
            }catch (Exception e){
                return ;
            }
            //System.out.println("********" + courseGoalScore +"="+  outlineScore +"* "+ courseWeights.get(i).getWeightCheckOutline()+ "**********");
        }
        // System.out.println(resMap);   //{7=0.8708, 8=0.8602857142857143, 14=0.7635}
        // 将resMap中的课程目标达成度保存到数据库中（学生id，课程id，课程目标id，课程目标达成度值）
        ArrayList<StudentCourseGoalScore> goalScores = new ArrayList<>();
        for (Integer cgId:courseGoalIds){
            StudentCourseGoalScore goalScore = new StudentCourseGoalScore(teacherId,clazzId,studentId,courseId,cgId,Double.valueOf(0));
            StudentCourseGoalScore judgeObj = new StudentCourseGoalScore();
            // 判断数据库里已有数据是否重复 存在就更新 不存在就添加
            QueryWrapper<StudentCourseGoalScore> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("clazz_id",clazzId);
            queryWrapper.eq("student_id",studentId);
            queryWrapper.eq("course_id",courseId);
            queryWrapper.eq("course_goal_id",cgId);
            judgeObj = studentCourseGoalScoreService.getOne(queryWrapper);
            if(judgeObj != null){
                judgeObj.setScore(resMap.get(cgId));
                studentCourseGoalScoreService.update(judgeObj,queryWrapper);
            }else{
                // 保存更新的数据
                goalScore.setScore(resMap.get(cgId));
                studentCourseGoalScoreService.save(goalScore);
                goalScore.setId(null);
            }
        }
    }

    @Override
    public List<StudentCourseGoalScore> findOneCourseGoalInfo(Integer courseGoalId, Integer courseId, Integer clazzId) {
        return studentcourseGoalScoreMapper.findOneCourseGoalInfo(courseGoalId,courseId,clazzId);
    }

    @Override
    public Page<StudentCourseGoalScore> findAllPage(Page<StudentCourseGoalScore> Page, String name, String courseName) {
        return studentcourseGoalScoreMapper.findAllPage(Page,name,courseName);
    }
}
