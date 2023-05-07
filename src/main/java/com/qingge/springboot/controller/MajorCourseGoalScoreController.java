package com.qingge.springboot.controller;


import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.ClazzCourseGoalScore;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.IMajorCourseGoalScoreService;
import com.qingge.springboot.entity.MajorCourseGoalScore;
import java.util.List;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wano
 * @since 2022-04-12
 */
@RestController
@RequestMapping("/major_course_goal_score")
public class MajorCourseGoalScoreController {

    @Resource
    private IMajorCourseGoalScoreService majorCourseGoalScoreService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody MajorCourseGoalScore majorCourseGoalScore) {
        majorCourseGoalScoreService.saveOrUpdate(majorCourseGoalScore);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        majorCourseGoalScoreService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        majorCourseGoalScoreService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(majorCourseGoalScoreService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(majorCourseGoalScoreService.getById(id));
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String courseName) {

        Page<MajorCourseGoalScore> page = majorCourseGoalScoreService.findPage(new Page<>(pageNum, pageSize),name,courseName);

        return Result.success(page);
    }

    @GetMapping("/calculate")
    public Result CalculateMajorCourseGoalScoreData(@RequestParam Integer majorId,
                                                    @RequestParam Integer courseId,
                                                    @RequestParam String grade){
        majorCourseGoalScoreService.majorCourseGoalScore(majorId, courseId, grade);
        return Result.success();
    }
}

