package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingge.springboot.entity.ClazzGradRequireScore;
import com.qingge.springboot.entity.Point;
import com.qingge.springboot.entity.Student;
import com.qingge.springboot.entity.StudentGradRequireScore;
import com.qingge.springboot.mapper.*;
import com.qingge.springboot.service.IClazzGradRequireScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.service.IPointCourseWeightService;
import com.qingge.springboot.service.IStudentGradRequireScoreService;
import com.qingge.springboot.service.IStudentService;
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
public class ClazzGradRequireScoreServiceImpl extends ServiceImpl<ClazzGradRequireScoreMapper, ClazzGradRequireScore> implements IClazzGradRequireScoreService {

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
    @Resource
    private ClazzGradRequireScoreMapper clazzGradRequireScoreMapper;

    @Resource
    StudentGradRequireScoreServiceImpl studentGradRequireScoreServiceImpl;

    @Override
    public void calculateClazzGradRequireScore(Integer clazzId) {
        // 根据班级id，查询所有学生，计算并保存所有学生的毕业要求达成度
        List<Student> students = studentService.list(new QueryWrapper<Student>().eq("clazz_id", clazzId));
        for (Student s: students) {
            studentGradRequireScoreServiceImpl.studentGraduateRequireScore(s.getId());
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

    @Override
    public List<ClazzGradRequireScore> ShowClazzGradRequireScore(Integer id) {
        return clazzGradRequireScoreMapper.ShowClazzGradRequireScore(id);
    }

    @Override
    public List<Point> FindClazzPoint(Integer clazzId) {
        return clazzGradRequireScoreMapper.FindClazzPoint(clazzId);
    }
}
