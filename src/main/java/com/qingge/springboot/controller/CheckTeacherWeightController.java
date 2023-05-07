package com.qingge.springboot.controller;


import com.qingge.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.ICheckTeacherWeightService;
import com.qingge.springboot.entity.CheckTeacherWeight;
import java.util.List;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wano
 * @since 2022-04-03
 */
@RestController
@RequestMapping("/check_teacher_weight")
public class CheckTeacherWeightController {

    @Resource
    private ICheckTeacherWeightService checkTeacherWeightService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody CheckTeacherWeight checkTeacherWeight) {
        checkTeacherWeightService.saveOrUpdate(checkTeacherWeight);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        checkTeacherWeightService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        checkTeacherWeightService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(checkTeacherWeightService.list());
    }

    @GetMapping("/findAll/{teacherId}/{courseId}")
    public Result findAllByTeacherId(@PathVariable Integer teacherId, @PathVariable Integer courseId) {
        return Result.success(checkTeacherWeightService.findAllByTeacherIdAndCourseId(teacherId,courseId));
    }


    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(checkTeacherWeightService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<CheckTeacherWeight> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(checkTeacherWeightService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

