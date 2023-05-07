package com.qingge.springboot.controller;


import com.qingge.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.ICourseGoalScoreService;
import com.qingge.springboot.entity.CourseGoalScore;
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
@RequestMapping("/course-goal-score")
public class CourseGoalScoreController {

    @Resource
    private ICourseGoalScoreService courseGoalScoreService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody CourseGoalScore courseGoalScore) {
        courseGoalScoreService.saveOrUpdate(courseGoalScore);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        courseGoalScoreService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseGoalScoreService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(courseGoalScoreService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(courseGoalScoreService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<CourseGoalScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(courseGoalScoreService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

