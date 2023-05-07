package com.qingge.springboot.controller;


import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.Point;
import com.qingge.springboot.entity.StudentGradRequireScore;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.IClazzGradRequireScoreService;
import com.qingge.springboot.entity.ClazzGradRequireScore;
import java.util.List;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wano
 * @since 2022-04-18
 */
@RestController
@RequestMapping("/clazz_grad_require_score")
public class ClazzGradRequireScoreController {

    @Resource
    private IClazzGradRequireScoreService clazzGradRequireScoreService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody ClazzGradRequireScore clazzGradRequireScore) {
        clazzGradRequireScoreService.saveOrUpdate(clazzGradRequireScore);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        clazzGradRequireScoreService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        clazzGradRequireScoreService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(clazzGradRequireScoreService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(clazzGradRequireScoreService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<ClazzGradRequireScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(clazzGradRequireScoreService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @GetMapping("/calculate")
    public Result CalculateClazzGradRequireScore(@RequestParam Integer clazzId){
        clazzGradRequireScoreService.calculateClazzGradRequireScore(clazzId);
        return Result.success();
    }

    @GetMapping("/ShowClazzGradRequireScore/{id}")
    public Result ShowClazzGradRequireScore(@PathVariable Integer id){
        List<ClazzGradRequireScore> list;
        list = clazzGradRequireScoreService.ShowClazzGradRequireScore(id);
        System.out.println(list);
        return Result.success(list);
    }

    @GetMapping("/FindClazzPoint/{clazzId}")
    public Result FindClazzPoint(@PathVariable Integer clazzId){
        List<Point> list = clazzGradRequireScoreService.FindClazzPoint(clazzId);
        return Result.success(list);
    }
}

