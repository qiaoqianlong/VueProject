package com.qingge.springboot.controller;


import com.qingge.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.ICheckOutlineWeightService;
import com.qingge.springboot.entity.CheckOutlineWeight;
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
@RequestMapping("/check_outline_weight")
public class CheckOutlineWeightController {

    @Resource
    private ICheckOutlineWeightService checkOutlineWeightService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody CheckOutlineWeight checkOutlineWeight) {
        checkOutlineWeightService.saveOrUpdate(checkOutlineWeight);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        checkOutlineWeightService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        checkOutlineWeightService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(checkOutlineWeightService.list());
    }

    @GetMapping("/findAll/{teacherId}/{courseId}")
    public Result findAllByTeacherId(@PathVariable Integer teacherId, @PathVariable Integer courseId) {
        return Result.success(checkOutlineWeightService.findAllByTeacherIdAndCourseId(teacherId,courseId));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(checkOutlineWeightService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<CheckOutlineWeight> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(checkOutlineWeightService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

