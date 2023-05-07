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
 * @since 2022-04-12
 */
@Service
public class MajorCourseGoalScoreServiceImpl extends ServiceImpl<MajorCourseGoalScoreMapper, MajorCourseGoalScore> implements IMajorCourseGoalScoreService {

    @Resource
    private MajorCourseGoalScoreMapper majorCourseGoalScoreMapper;
    @Resource
    private CourseScheduleMapper courseScheduleMapper;
    @Resource
    private ICourseWeightsService courseWeightsService;
    @Resource
    private IClazzCourseGoalScoreService clazzCourseGoalScoreService;
    @Resource
    private IMajorCourseGoalScoreService majorCourseGoalScoreService;;
    @Resource
    private IClazzService clazzService ;


    @Override
    public Page<MajorCourseGoalScore> findPage(Page<MajorCourseGoalScore> Page, String name, String courseName) {
        return majorCourseGoalScoreMapper.findPage(Page,name,courseName);
    }

    @Override
    public List<ShowClazzInfo> findPagePlus(Integer courseId, Integer majorId) {
        return  majorCourseGoalScoreMapper.findPagePlus(courseId,majorId);
    }

    @Override
    public void majorCourseGoalScore(Integer majorId, Integer courseId, String grade) {
        // 根据专业id，获取所有班级对象数组,计算并保存所有课程的课程目标达成度
        List<Clazz> clazzes = clazzService.list(new QueryWrapper<Clazz>()
                .eq("major_id",majorId)
                .eq("grade",grade));
        for (Clazz clazz: clazzes) {
            clazzCourseGoalScoreService.clazzCourseGoalScore(clazz.getId(),courseId);
        }

        // 根据班级id和课程id，查询教师id
        Integer teacherId = courseScheduleMapper.selectTeacherIdByIds(courseId,clazzes.get(0).getId());
        // 根据教师id、课程id，查询课程参数
        List<CourseWeights> courseWeights = courseWeightsService.findAllByTeacherIdAndCourseId(teacherId,courseId);
        // 获取课程的所有课程目标ids数组
        List<Integer> courseGoalIds=  courseWeights.stream().map(CourseWeights::getCourseGoalId).distinct().collect(Collectors.toList());

        // 根据班级id和课程id，得到所有班级课程达成度对象数组
        List<ClazzCourseGoalScore> clazzCourseGoalScoreList = new ArrayList<>();
        List<ClazzCourseGoalScore> clazzesCourseGoalScoreList = new ArrayList<>();
        for (int i = 0; i < clazzes.size(); i++) {
            clazzCourseGoalScoreList = clazzCourseGoalScoreService.list(new QueryWrapper<ClazzCourseGoalScore>()
                    .eq("major_id",majorId)
                    .eq("clazz_id",clazzes.get(i).getId())
                    .eq("course_id",courseId));
            clazzesCourseGoalScoreList.addAll(clazzCourseGoalScoreList);
        }

        // <课程目标id,所有班级达成度和> 及初始化
        Map<Integer,Double> majorSumResMap = new HashMap<>();
        for (Integer cgId:courseGoalIds){
            majorSumResMap.put(cgId,Double.valueOf(0));
        }
        // 平均值相加平均，频次累加
        Double majorCgsSum = new Double(0);
        for (Integer cgId:courseGoalIds){
            for (ClazzCourseGoalScore c : clazzesCourseGoalScoreList) {
                if (cgId == c.getCourseGoalId()){
                    majorCgsSum = majorSumResMap.get(c.getCourseGoalId());
                    majorCgsSum += c.getAverage();
                    majorSumResMap.put(cgId,majorCgsSum);
                }
            }
        }
        // 计算班级的所有学生的课程目标达成度平均值
        Map<Integer,Double> majorAvgResMap = new HashMap<>();
        Double majorCgsAvg = new Double(0);
        for (Integer cgId:courseGoalIds){
            majorAvgResMap.put(cgId,Double.valueOf(0));
        }
        for (Integer cgId:courseGoalIds){
            majorCgsAvg = majorAvgResMap.get(cgId);
            majorCgsAvg += ( majorSumResMap.get(cgId) / clazzes.size() );
            majorAvgResMap.put(cgId,majorCgsAvg);
        }
        // 统计所有班级的学生在各个区段的频次
        MajorCourseGoalScore majorCourseGoalScore = new MajorCourseGoalScore(null ,grade, majorId,  courseId,  0,
                Double.valueOf(0),0,0,0,0,0);
        MajorCourseGoalScore judgeMajorObj = new MajorCourseGoalScore();
        Integer[] counts = new Integer[5];
        Arrays.fill(counts,0);
        List<ClazzCourseGoalScore> filterClazzCourseGoalScoreList = new ArrayList<>();
        for (Integer cgId:courseGoalIds) {
            // 获取所有课程目标id相同的属性
            filterClazzCourseGoalScoreList = clazzesCourseGoalScoreList.stream()
                    .filter(item -> item.getCourseGoalId().equals(cgId)).collect(Collectors.toList());
            for (ClazzCourseGoalScore e : filterClazzCourseGoalScoreList) {
                // 区段频次累加
                counts[4] += e.getFieldFiveCount();
                counts[3] += e.getFieldFourCount();
                counts[2] += e.getFieldThreeCount();
                counts[1] += e.getFieldTwoCount();
                counts[0] += e.getFieldOneCount();
            }
            // 将专业课程目标达成度 保存到数据库
            // 判断数据库里已有数据是否重复 存在就更新 不存在就添加
            QueryWrapper<MajorCourseGoalScore> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("grade",grade);
            queryWrapper.eq("major_id",majorId);
            queryWrapper.eq("course_id",courseId);
            queryWrapper.eq("course_goal_id",cgId);
            judgeMajorObj = majorCourseGoalScoreService.getOne(queryWrapper);
            if(judgeMajorObj != null){
                judgeMajorObj.setCourseGoalId(cgId);
                judgeMajorObj.setAverage(majorAvgResMap.get(cgId));
                judgeMajorObj.setFieldOneCount(counts[0]);
                judgeMajorObj.setFieldTwoCount(counts[1]);
                judgeMajorObj.setFieldThreeCount(counts[2]);
                judgeMajorObj.setFieldFourCount(counts[3]);
                judgeMajorObj.setFieldFiveCount(counts[4]);
                majorCourseGoalScoreService.update(judgeMajorObj,queryWrapper);
                Arrays.fill(counts,0);
            }else{
                // 保存更新的数据
                majorCourseGoalScore.setCourseGoalId(cgId);
                majorCourseGoalScore.setAverage(majorAvgResMap.get(cgId));
                majorCourseGoalScore.setFieldOneCount(counts[0]);
                majorCourseGoalScore.setFieldTwoCount(counts[1]);
                majorCourseGoalScore.setFieldThreeCount(counts[2]);
                majorCourseGoalScore.setFieldFourCount(counts[3]);
                majorCourseGoalScore.setFieldFiveCount(counts[4]);
                majorCourseGoalScoreService.save(majorCourseGoalScore);
                majorCourseGoalScore.setId(null);
                Arrays.fill(counts,0);
            }
        }
    }
}
