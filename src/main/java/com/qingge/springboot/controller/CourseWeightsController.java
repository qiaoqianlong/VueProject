package com.qingge.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingge.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.ICourseWeightsService;
import com.qingge.springboot.entity.CourseWeights;
import java.util.List;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wano
 * @since 2022-04-01
 */
@RestController
@RequestMapping("/course_weights")
public class CourseWeightsController {

    @Resource
    private ICourseWeightsService courseWeightsService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody CourseWeights courseWeights) {

        courseWeightsService.saveOrUpdate(courseWeights);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        courseWeightsService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseWeightsService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(courseWeightsService.list());
    }

    // 教师id和课程id，获取 该课程参数
    @GetMapping("/findAll/{teacherId}/{courseId}")
    public Result findAll(@PathVariable Integer teacherId,@PathVariable Integer courseId) {
        List<CourseWeights> courseWeights = courseWeightsService.findAllByTeacherIdAndCourseId(teacherId,courseId);
        return Result.success(courseWeights);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(courseWeightsService.getById(id));
    }


    @GetMapping("/page")
    public Result findPage( @RequestParam(defaultValue = "") Integer teacherId,
                            @RequestParam(defaultValue = "") String  name,
                            @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        //  自定义分页方法
        Page<CourseWeights> page = courseWeightsService.findPage(new Page<>(pageNum, pageSize),name,teacherId);
        return Result.success(page);
    }
}

