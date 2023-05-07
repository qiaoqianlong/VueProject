package com.qingge.springboot;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingge.springboot.entity.*;
import com.qingge.springboot.mapper.*;
import com.qingge.springboot.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class GraduateRequireCalculateTest {

    @Resource
    public IPointCourseWeightService pointCourseWeightService;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private CourseWeightsMapper courseWeightsMapper;
    @Resource
    private StudentCourseGoalScoreMapper studentCourseGoalScoreMapper;
    @Resource
    private StudentGradRequireScoreMapper studentGradRequireScoreMapper;
    @Resource
    private IStudentGradRequireScoreService studentGradRequireScoreService;
    @Resource
    private IClazzGradRequireScoreService clazzGradRequireScoreService;
    @Resource
    private  ClazzMapper clazzMapper;
    @Resource
    private  GradRequireMapper gradRequireMapper;
    @Resource
    private  PointMapper pointMapper;
    @Resource
    private IStudentService studentService;

    // 计算班级毕业要求达成度
    @Test
    public void clazzGraduateRequireScore( ) throws Exception{
        Integer clazzId = new Integer(1);
        // 根据班级id，查询所有学生，计算并保存所有学生的毕业要求达成度
        List<Student> students = studentService.list(new QueryWrapper<Student>().eq("clazz_id", clazzId));
        for (Student s: students) {
            studentGraduateRequireScore(s.getId());
        }
        //  根据班级id，查专业id
        Integer majorId = clazzMapper.selectMajorIdByClazzId(clazzId);
        // 根据专业id，获取毕业要求id
        List<Integer> gradRequireIds = gradRequireMapper.listIds(majorId);
        // 根据毕业要求id，获取指标点id [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37]
        List<Integer> pointIds = new ArrayList<>();
        for (Integer grId: gradRequireIds) {
            pointIds.addAll(pointMapper.listIds(grId));
        }
        // 分别计算37个指标点的班级达成度平均值
        Double clazzGradRequireScoreSum = new Double(0);
        Double clazzGradRequireScoreAvg = new Double(0);
        for (Integer pId: pointIds) {
            // 根据班级id和指标点id，查 学生毕业要求达成度 对象数组
            List<StudentGradRequireScore> studentGradRequireScores = studentGradRequireScoreService.list(new QueryWrapper<StudentGradRequireScore>()
                    .eq("clazz_id",clazzId)
                    .eq("point_id",pId));
            // 累加求和，计算一个班学生的一个指标点的达成度和
            for (StudentGradRequireScore studentGradRequireScore: studentGradRequireScores) {
                clazzGradRequireScoreSum += studentGradRequireScore.getScore();
            }
            // 计算班级一个毕业要求指标达成度平均值
            clazzGradRequireScoreAvg = clazzGradRequireScoreSum/studentGradRequireScores.size();

            // 将毕业要求指标达成度值存储到数据库中，判断数据库里已有数据是否重复 存在就更新 不存在就添加
            ClazzGradRequireScore clazzGradRequireScore = new ClazzGradRequireScore();
            ClazzGradRequireScore judgeObj = new ClazzGradRequireScore();
            QueryWrapper<ClazzGradRequireScore> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("clazz_id",clazzId);
            queryWrapper.eq("major_id",majorId);
            queryWrapper.eq("point_id",pId);
            judgeObj = clazzGradRequireScoreService.getOne(queryWrapper);
            if(judgeObj != null){
                judgeObj.setScore(clazzGradRequireScoreAvg);
                clazzGradRequireScoreService.update(judgeObj,queryWrapper);
            }else{
                // 保存更新的数据
               clazzGradRequireScore.setClazzId(clazzId);
               clazzGradRequireScore.setMajorId(majorId);
               clazzGradRequireScore.setPointId(pId);
               clazzGradRequireScore.setScore(clazzGradRequireScoreAvg);
                clazzGradRequireScoreService.save(clazzGradRequireScore);
                clazzGradRequireScore.setId(null);
            }
            clazzGradRequireScoreSum = new Double(0);
        }
    }

    // 计算学生毕业要求达成度
//    @Test
    public void studentGraduateRequireScore(  Integer studentId ) throws Exception{
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

    // 计算一个指标点达成度
    public void studentOnePointScore( Integer pointId,Integer studentId ) throws Exception{
//    public void studentOnePointScore() throws Exception{
//        Integer pointId = new Integer(1);
//        Integer studentId = new Integer(201917301);
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
}
