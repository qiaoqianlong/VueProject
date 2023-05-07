package com.qingge.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.qingge.springboot.common.Constants;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.config.AuthAccess;
import com.qingge.springboot.entity.*;
import com.qingge.springboot.mapper.FileMapper;
import com.qingge.springboot.service.IClazzCourseGoalScoreService;
import com.qingge.springboot.service.IMajorCourseGoalScoreService;
import com.qingge.springboot.service.IStudentCourseGoalScoreService;
import com.qingge.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Resource
    private IUserService userService;

    @Resource
    private FileMapper fileMapper;

    @Resource
    private IStudentCourseGoalScoreService iStudentCourseGoalScoreService;

    @Resource
    private IClazzCourseGoalScoreService iClazzCourseGoalScoreService;

    @Resource
    private IMajorCourseGoalScoreService iMajorCourseGoalScoreService;


    @GetMapping("/example")
    public Result get() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return Result.success(map);
    }



    @GetMapping("/members/{courseId}/{studentId}")
    public Result members(
                          @PathVariable Integer studentId,
                          @PathVariable Integer courseId) {
        //List<StudentCourseGoalScore> list = iStudentCourseGoalScoreService.list();
        List<CourseGoalWithScore> list;
        list = iStudentCourseGoalScoreService.findPagePlus(courseId,studentId);
        System.out.println(list);
        return Result.success(list);
    }

    @GetMapping("/studentOS/{courseId}/{studentId}")
    public Result StudentOS(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId) {
        List<CourseGoalWithScore> list;
        list = iStudentCourseGoalScoreService.findPagePlusX(courseId,studentId);
        System.out.println(list);
        return Result.success(list);
    }

    @GetMapping("/clazzShow/{courseId}/{clazzId}")
    public Result ClazzShow(
            @PathVariable Integer clazzId,
            @PathVariable Integer courseId) {
        //List<StudentCourseGoalScore> list = iStudentCourseGoalScoreService.list();
        List<ShowClazzInfo> list;
        list = iClazzCourseGoalScoreService.findPagePlus(courseId,clazzId);
        System.out.println(list);
        return Result.success(list);
    }

    @GetMapping("/clazzShowX/{courseId}/{clazzId}")
    public Result ClazzShowX(
            @PathVariable Integer clazzId,
            @PathVariable Integer courseId) {
        //List<StudentCourseGoalScore> list = iStudentCourseGoalScoreService.list();
/*        List<ShowClazzInfo> list;
        list = iClazzCourseGoalScoreService.findPagePlus(courseId,clazzId);
        System.out.println(list);*/
        List<Double> list;
        list = iStudentCourseGoalScoreService.selectByIds(courseId,clazzId);
        Map<Integer,Double> map = new HashMap<>();
        for(int i = 0;i < list.size();i++){
            map.put(i,list.get(i));
        }
        System.out.println(map);
        return Result.success(map);
    }

    @GetMapping("/majorShow/{courseId}/{majorId}")
    public Result MajorShow(
            @PathVariable Integer majorId,
            @PathVariable Integer courseId) {
        //List<StudentCourseGoalScore> list = iStudentCourseGoalScoreService.list();
        List<ShowClazzInfo> list;
        list = iMajorCourseGoalScoreService.findPagePlus(courseId,majorId);
        System.out.println(list);
        return Result.success(list);
    }



    @AuthAccess
    @GetMapping("/file/front/all")

    public Result frontAll() {
        List<Files> files = fileMapper.selectList(null);  // 3. 从数据库取出数据
        return Result.success(files);
    }
}
