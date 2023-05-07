package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.*;
import com.qingge.springboot.mapper.*;
import com.qingge.springboot.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-04-11
 */
@Service
public class ClazzCourseGoalScoreServiceImpl extends ServiceImpl<ClazzCourseGoalScoreMapper, ClazzCourseGoalScore> implements IClazzCourseGoalScoreService {

    @Resource
    private ClazzCourseGoalScoreMapper clazzCourseGoalScoreMapper;
    @Resource
    private CourseScheduleMapper courseScheduleMapper;
    @Resource
    private ICourseWeightsService courseWeightsService;
    @Resource
    private IStudentCourseGoalScoreService studentCourseGoalScoreService;
    @Resource
    private IStudentService studentService;
    @Resource
    private ClazzMapper clazzMapper;
    @Resource
    private IClazzCourseGoalScoreService clazzCourseGoalScoreService;


    @Override
    public Page<ClazzCourseGoalScore> findPage(Page<ClazzCourseGoalScore> Page, String name, String courseName) {
        return clazzCourseGoalScoreMapper.findPage(Page,name,courseName);
    }

    @Override
    public List<ShowClazzInfo> findPagePlus(Integer courseId, Integer clazzId) {
        return  clazzCourseGoalScoreMapper.findPagePlus(courseId,clazzId);
    }

    @Override
    public void clazzCourseGoalScore(Integer clazzId, Integer courseId) {
        Integer majorId = clazzMapper.selectMajorIdByClazzId(clazzId);
        // 根据班级id和课程id，查询教师id
        Integer teacherId = courseScheduleMapper.selectTeacherIdByIds(courseId,clazzId);
        // 根据教师id、课程id，查询课程参数
        List<CourseWeights> courseWeights = courseWeightsService.findAllByTeacherIdAndCourseId(teacherId,courseId);
        // 获取课程的所有课程目标ids数组
        List<Integer> courseGoalIds=  courseWeights.stream().map(CourseWeights::getCourseGoalId).distinct().collect(Collectors.toList());
        // 根据班级id，查询所有学生
        List<Student> students = studentService.list(new QueryWrapper<Student>().eq("clazz_id", clazzId));
        // 计算并保存所有学生的课程目标达成度
        for (Student s: students) {
            studentCourseGoalScoreService.stuCourseGoalScore(s.getId(),courseId);
        }

        // 根据班级id、课程id,查询所有学生的课程目标达成度
        List<StudentCourseGoalScore> studentAllCgsList = new ArrayList<>();
        List<StudentCourseGoalScore> list =
                studentCourseGoalScoreService.list(new QueryWrapper<StudentCourseGoalScore>()
                        .eq("clazz_id",clazzId)
                        .eq("course_id",courseId));

        // 计算班级课程目标达成度 平均值和频次
        Double clazzCgsSum = Double.valueOf(0);
        Double clazzCgsAvg = Double.valueOf(0);
        // <课程目标id,所有学生参数和> 及初始化
        Map<Integer,Double> clazzSumResMap = new HashMap<>();
        for (Integer cgId:courseGoalIds){
            clazzSumResMap.put(cgId,Double.valueOf(0));
        }
        // 计算班级的所有学生的课程目标达成度和
        for (StudentCourseGoalScore e: list ) {
            clazzCgsSum = clazzSumResMap.get(e.getCourseGoalId());
            clazzCgsSum += e.getScore();
            clazzSumResMap.put(e.getCourseGoalId(),clazzCgsSum);
            studentAllCgsList.add(e);
        }
        // 算班级的所有学生的课程目标达成度平均值 avgClazzCourseGoal
        // clazzAvgResMap {7=0.8767172413793104, 8=0.8560068965517243, 14=0.7824370689655173}
        Map<Integer,Double> clazzAvgResMap = new HashMap<>();
        for (Integer cgId:courseGoalIds){
            clazzAvgResMap.put(cgId,Double.valueOf(0));
        }
        for (Integer cgId:courseGoalIds){
            clazzCgsAvg = clazzAvgResMap.get(cgId);
            clazzCgsAvg += ( clazzSumResMap.get(cgId) / (studentAllCgsList.size()/courseGoalIds.size()) );
            clazzAvgResMap.put(cgId,clazzCgsAvg);
        }
        System.out.println(clazzAvgResMap);

        // 统计所有学生在各个区段的频次
        // counts [0, 0, 0, 5, 24] [0, 0, 0, 17, 12] [0, 0, 0, 2, 27]
        Integer count = new Integer(0);
        Integer[] counts = new Integer[5];
        Arrays.fill(counts,0);
        ClazzCourseGoalScore clazzCourseGoalScore = new ClazzCourseGoalScore(null , teacherId,majorId, clazzId,  courseId,  0,
                Double.valueOf(0),0,0,0,0,0);
        ClazzCourseGoalScore judgeClazzObj = new ClazzCourseGoalScore();
        for (Integer cgId:courseGoalIds){
            // 统计 单个课程目标的频次
            for (StudentCourseGoalScore e: studentAllCgsList) {
                if(cgId.equals(e.getCourseGoalId())){
                    // 判断区段频次
                    if(e.getScore() >=0.9 && e.getScore() <=1.0){
                        counts[4]++;
                    }else if(e.getScore() >=0.8){
                        counts[3]++;
                    }else if(e.getScore() >=0.7){
                        counts[2]++;
                    }else if(e.getScore() >=0.6){
                        counts[1]++;
                    }else if (e.getScore() >= Double.valueOf(0)) {
                        counts[0]++;
                    }
                }
            }
            // 直接将 班级课程目标对象 存储到数据库中
            // 判断数据库里已有数据是否重复 存在就更新 不存在就添加
            QueryWrapper<ClazzCourseGoalScore> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("major_id",majorId);
            queryWrapper.eq("clazz_id",clazzId);
            queryWrapper.eq("course_id",courseId);
            queryWrapper.eq("course_goal_id",cgId);
            judgeClazzObj = clazzCourseGoalScoreService.getOne(queryWrapper);
            if(judgeClazzObj != null){
                judgeClazzObj.setCourseGoalId(cgId);
                judgeClazzObj.setAverage(clazzAvgResMap.get(cgId));
                judgeClazzObj.setFieldOneCount(counts[0]);
                judgeClazzObj.setFieldTwoCount(counts[1]);
                judgeClazzObj.setFieldThreeCount(counts[2]);
                judgeClazzObj.setFieldFourCount(counts[3]);
                judgeClazzObj.setFieldFiveCount(counts[4]);
                clazzCourseGoalScoreService.update(judgeClazzObj,queryWrapper);
                Arrays.fill(counts,0);
            }else{
                // 保存更新的数据
                clazzCourseGoalScore.setCourseGoalId(cgId);
                clazzCourseGoalScore.setAverage(clazzAvgResMap.get(cgId));
                clazzCourseGoalScore.setFieldOneCount(counts[0]);
                clazzCourseGoalScore.setFieldTwoCount(counts[1]);
                clazzCourseGoalScore.setFieldThreeCount(counts[2]);
                clazzCourseGoalScore.setFieldFourCount(counts[3]);
                clazzCourseGoalScore.setFieldFiveCount(counts[4]);
                clazzCourseGoalScoreService.save(clazzCourseGoalScore);
                clazzCourseGoalScore.setId(null);
                Arrays.fill(counts,0);
            }
        }
    }
}
