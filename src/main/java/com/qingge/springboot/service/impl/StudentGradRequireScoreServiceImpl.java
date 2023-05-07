package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingge.springboot.entity.PointCourseWeight;
import com.qingge.springboot.entity.StudentCourseGoalScore;
import com.qingge.springboot.entity.StudentGradRequireScore;
import com.qingge.springboot.mapper.*;
import com.qingge.springboot.service.IPointCourseWeightService;
import com.qingge.springboot.service.IStudentGradRequireScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-04-18
 */
@Service
public class StudentGradRequireScoreServiceImpl extends ServiceImpl<StudentGradRequireScoreMapper, StudentGradRequireScore> implements IStudentGradRequireScoreService {

    @Resource
    StudentMapper studentMapper;

    @Resource
    GradRequireMapper gradRequireMapper;

    @Resource
    PointMapper pointMapper;

    @Resource
    StudentCourseGoalScoreMapper studentCourseGoalScoreMapper;

    @Resource
    CourseWeightsMapper courseWeightsMapper;

    @Resource
    IStudentGradRequireScoreService studentGradRequireScoreService;

    @Resource
    public IPointCourseWeightService pointCourseWeightService;

    @Resource
    public StudentGradRequireScoreMapper studentGradRequireScoreMapper;

    @Override
    public void studentGraduateRequireScore(Integer studentId) {
        //        Integer studentId = new Integer(201917301);
        //  根据学生id，查专业id
        Integer majorId = studentMapper.selectMajorIdByStudentId(studentId);
        // 根据专业id，获取毕业要求id
        List<Integer> gradRequireIds = gradRequireMapper.listIds(majorId);
        // 根据毕业要求id，获取指标点id [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37]
        List<Integer> pointIds = new ArrayList<>();
        for (Integer grId: gradRequireIds) {
            pointIds.addAll(pointMapper.listIds(grId));
        }
        // 计算所有指标点达成度
        for (Integer pId: pointIds) {
            studentOnePointScore(pId,studentId);
        }
    }

    @Override
    public void studentOnePointScore(Integer pointId,Integer studentId) {
        // 根据学生id，查询班级id
        Integer clazzId = studentMapper.selectClazzIdByStudentId(studentId);
        // 计算指标点达成度
        // 根据指标点id，获取指标点参数对象数组 PointCourseWeights （4个） Columns: id, point_id, course_id, weight Row: 15, 1, 9, 0.3 Row: 17, 1, 10, 0.2 Row: 19, 1, 11, 0.2 Row: 21, 1, 12, 0.3
        List<PointCourseWeight> pointCourseWeights = pointCourseWeightService.list(new QueryWrapper<PointCourseWeight>()
                .eq("point_id",pointId));
        // 计算课程目标达成度 stuCourseGoalScoreSum
        Integer courseGoalId = new Integer(0);
        Double stuCourseGoalScore = new Double(0);
        Double stuCourseGoalScoreSum = new Double(0);
        try {
            for (int i = 0; i < pointCourseWeights.size(); i++) {
                // 根据指标点id和课程id，得到对应获取课程目标id (1,9 => 15) 1,10 1,11 1,12
                courseGoalId = courseWeightsMapper.getCourseGoalIdByPointAndCourse(pointCourseWeights.get(i).getPointId(),pointCourseWeights.get(i).getCourseId());
                // 根据课程目标id和学生id，得到课程目标达成度值 (15,201917301 => 0.72) 0.72 0.8 0.85
                stuCourseGoalScore = studentCourseGoalScoreMapper.getScoreByCourseGoalAndStudent(courseGoalId,studentId);
                // 加权后累加，计算毕业要求指标达成度 (0.216 = 0 + 0.72*0.3) 0.216 0.36 0.52 0.775
                // 课程目标达成度 * 参数 的和为一个指标点的达成度
                stuCourseGoalScoreSum += stuCourseGoalScore * pointCourseWeights.get(i).getWeight();
            }
        }catch(Exception e){
            // "该学生没有课程目标达成度值"
            return ;
        }
        // 将毕业要求指标达成度值存储到数据库中，判断数据库里已有数据是否重复 存在就更新 不存在就添加
        StudentGradRequireScore studentGradRequireScore = new StudentGradRequireScore();
        StudentGradRequireScore judgeObj = new StudentGradRequireScore();
        QueryWrapper<StudentGradRequireScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("clazz_id",clazzId);
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("point_id",pointId);
        judgeObj = studentGradRequireScoreService.getOne(queryWrapper);
        if(judgeObj != null){
            judgeObj.setScore(stuCourseGoalScoreSum);
            studentGradRequireScoreService.update(judgeObj,queryWrapper);
        }else{
            // 保存更新的数据
            studentGradRequireScore.setClazzId(clazzId);
            studentGradRequireScore.setStudentId(studentId);
            studentGradRequireScore.setPointId(pointId);
            studentGradRequireScore.setScore(stuCourseGoalScoreSum);
            studentGradRequireScoreService.save(studentGradRequireScore);
            studentGradRequireScore.setId(null);
        }
    }

    @Override
    public List<StudentGradRequireScore> ShowStuGradRequireScore(Integer studentId) {
        return studentGradRequireScoreMapper.ShowStuGradRequireScore(studentId);
    }

    @Override
    public List<StudentGradRequireScore> FindClazzOnePointInfo(Integer pointId, Integer clazzId) {
        return studentGradRequireScoreMapper.FindClazzOnePointInfo(pointId,clazzId);
    }
}
