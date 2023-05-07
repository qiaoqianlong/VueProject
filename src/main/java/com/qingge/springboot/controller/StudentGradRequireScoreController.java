package com.qingge.springboot.controller;


import com.qingge.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.IStudentGradRequireScoreService;
import com.qingge.springboot.entity.StudentGradRequireScore;
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
@RequestMapping("/student_grad_require_score")
public class StudentGradRequireScoreController {

    @Resource
    private IStudentGradRequireScoreService studentGradRequireScoreService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody StudentGradRequireScore studentGradRequireScore) {
        studentGradRequireScoreService.saveOrUpdate(studentGradRequireScore);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        studentGradRequireScoreService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        studentGradRequireScoreService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(studentGradRequireScoreService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(studentGradRequireScoreService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<StudentGradRequireScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(studentGradRequireScoreService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @GetMapping("/calculate")
    public Result CalculateStudentGradRequireScore(@RequestParam Integer studentId){
        studentGradRequireScoreService.studentGraduateRequireScore(studentId);
        return Result.success();
    }

    @GetMapping("/ShowStuGradRequireScore/{studentId}")
    public Result ShowStuGradRequireScore(@PathVariable Integer studentId){
        List<StudentGradRequireScore> list;
        list = studentGradRequireScoreService.ShowStuGradRequireScore(studentId);
        return Result.success(list);
    }

    @GetMapping("/findClazzOnePointInfo/{pointId}/{clazzId}")
    public Result FindClazzOnePointInfo(@PathVariable Integer pointId,
                                        @PathVariable Integer clazzId){
        List<StudentGradRequireScore> list = studentGradRequireScoreService.FindClazzOnePointInfo(pointId,clazzId);
        return Result.success(list);
    }

}

