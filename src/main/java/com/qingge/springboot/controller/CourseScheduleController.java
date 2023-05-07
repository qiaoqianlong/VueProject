package com.qingge.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.*;
import com.qingge.springboot.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.ICourseScheduleService;

import java.util.List;


import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course_schedule")
public class CourseScheduleController {

    @Resource
    private ICourseScheduleService courseScheduleService;

    @Resource
    private UserMapper userMapper;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody CourseSchedule courseSchedule) {
        return Result.success(courseScheduleService.saveOrUpdate(courseSchedule));
    }
    // 根据教师id\课程id\班级id，修改状态
    @PostMapping("/status")
    public Result saveStatus(@RequestBody CourseSchedule courseSchedule) {
        CourseSchedule cs = courseScheduleService.getOne(new QueryWrapper<CourseSchedule>()
                .eq("teacher_id",courseSchedule.getTeacherId())
                .eq("course_id",courseSchedule.getCourseId())
                .eq("clazz_id",courseSchedule.getClazzId()));
        cs.setStatus(courseSchedule.getStatus());
        courseScheduleService.saveOrUpdate(cs);
        return Result.success();
    }

    // 根据 id 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        courseScheduleService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseScheduleService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(courseScheduleService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(courseScheduleService.getById(id));
    }

    // 添加外键，需要自定义page方法
    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "")  Integer courseId,
                           @RequestParam(defaultValue = "") Integer  teacherId,
                           @RequestParam(defaultValue = "") Integer  clazzId,
                           @RequestParam(defaultValue = "") String  name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        // 方法二： 自定义分页方法
        Page<CourseSchedule> page = courseScheduleService.findPage(new Page<>(pageNum, pageSize),  courseId,teacherId, clazzId,name);
        return Result.success(page);
    }

    @GetMapping("/page/byTeacher")
    public Result findPageByTeacherId(@RequestParam Integer teacherId,
                                      @RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize) {
        // 方法二： 自定义分页方法
        Page<CourseSchedule> page = courseScheduleService.findPageByTeacherId(new Page<>(pageNum, pageSize),teacherId);
        return Result.success(page);
    }

    @GetMapping("/find_student_course_Info")
    public Result findStudentCourseInfo(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam(defaultValue = "") String name,
                                        @RequestParam(defaultValue = "") String courseName){

        Page<StudentCourseGoalScore> page = courseScheduleService.findStudentCourseInfo(new Page<>(pageNum, pageSize),name,courseName);
        return Result.success(page);
    }

    @GetMapping("/find_clazz_course_Info")
    public Result findClazzCourseInfo(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam(defaultValue = "") String name,
                                      @RequestParam(defaultValue = "") String courseName,
                                      @RequestParam Integer teacherId){

        String code = userMapper.selectByTeacherId(teacherId);
        Page<ClazzCourseGoalScore> page;
        if(code.equals("ROLE_TEACHER")){
             page = courseScheduleService.findClazzCourseInfo(new Page<>(pageNum, pageSize),name,courseName,teacherId);
        }else {
             page = courseScheduleService.findAllClazzCourseInfo(new Page<>(pageNum, pageSize),name,courseName);
        }
        return Result.success(page);
    }

    @GetMapping("/find_Major_course_Info")
    public Result findMajorCourseInfo(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam(defaultValue = "") String name,
                                      @RequestParam(defaultValue = "") String courseName){

        Page<MajorCourseGoalScore> page = courseScheduleService.findMajorCourseInfo(new Page<>(pageNum, pageSize),name,courseName);
        return Result.success(page);
    }

}

