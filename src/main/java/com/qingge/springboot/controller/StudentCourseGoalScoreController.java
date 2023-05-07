package com.qingge.springboot.controller;


import com.qingge.springboot.common.Result;
import com.qingge.springboot.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.IStudentCourseGoalScoreService;
import com.qingge.springboot.entity.StudentCourseGoalScore;
import java.util.List;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wano
 * @since 2022-04-10
 */
@RestController
@RequestMapping("/student_course_goal_score")
public class StudentCourseGoalScoreController {

    @Resource
    private IStudentCourseGoalScoreService studentCourseGoalScoreService;
    @Resource
    private UserMapper userMapper;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody StudentCourseGoalScore studentCourseGoalScore) {
        studentCourseGoalScoreService.saveOrUpdate(studentCourseGoalScore);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        studentCourseGoalScoreService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        studentCourseGoalScoreService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(studentCourseGoalScoreService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(studentCourseGoalScoreService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam Integer teacherId,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String courseName) {

        Page<StudentCourseGoalScore> page;
        String code = userMapper.selectByTeacherId(teacherId);
        if(code.equals("ROLE_TEACHER")){
            page = studentCourseGoalScoreService
                    .findPage(new Page<>(pageNum, pageSize),name,courseName,teacherId);
        }else{
            page = studentCourseGoalScoreService.findAllPage(new Page<>(pageNum, pageSize),name,courseName);
        }
        return Result.success(page);
    }

    @PostMapping("/calculate")
    public Result CalculateStudentCourseGoalScoreData(@RequestParam Integer studentId,
                                                      @RequestParam Integer courseId){
        studentCourseGoalScoreService.stuCourseGoalScore(studentId, courseId);
        return Result.success();
    }

    @GetMapping("/findOneCourseGoalInfo/{courseGoalId}/{courseId}/{clazzId}")
    public Result findOneCourseGoalInfo(@PathVariable Integer courseGoalId,
                                        @PathVariable Integer courseId,
                                        @PathVariable Integer clazzId){
        List<StudentCourseGoalScore> list = studentCourseGoalScoreService.findOneCourseGoalInfo(courseGoalId,courseId,clazzId);
        return Result.success(list);
    }

}

