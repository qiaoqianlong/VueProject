package com.qingge.springboot.controller;


import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.StudentCourseGoalScore;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.IClazzCourseGoalScoreService;
import com.qingge.springboot.entity.ClazzCourseGoalScore;
import java.util.List;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wano
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/clazz_course_goal_score")
public class ClazzCourseGoalScoreController {

    @Resource
    private IClazzCourseGoalScoreService clazzCourseGoalScoreService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody ClazzCourseGoalScore clazzCourseGoalScore) {
        clazzCourseGoalScoreService.saveOrUpdate(clazzCourseGoalScore);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        clazzCourseGoalScoreService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        clazzCourseGoalScoreService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(clazzCourseGoalScoreService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(clazzCourseGoalScoreService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String courseName) {
        Page<ClazzCourseGoalScore> page = clazzCourseGoalScoreService.findPage(new Page<>(pageNum, pageSize),name,courseName);
        return Result.success(page);
    }

    @GetMapping("/calculate")
    public Result CalculateClazzCourseGoalScoreData(@RequestParam Integer clazzId,
                                                    @RequestParam Integer courseId){
        clazzCourseGoalScoreService.clazzCourseGoalScore(clazzId, courseId);
        return Result.success();
    }
}

