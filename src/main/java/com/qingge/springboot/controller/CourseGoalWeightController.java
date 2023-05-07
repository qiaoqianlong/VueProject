package com.qingge.springboot.controller;


import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.PointCourseWeight;
import com.qingge.springboot.mapper.CourseScheduleMapper;
import com.qingge.springboot.mapper.UserMapper;
import com.qingge.springboot.service.IPointCourseWeightService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.ICourseGoalWeightService;
import com.qingge.springboot.entity.CourseGoalWeight;
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
@RequestMapping("/course_goal_weight")
public class CourseGoalWeightController {

    @Resource
    private ICourseGoalWeightService courseGoalWeightService;
    @Resource
    private IPointCourseWeightService pointCourseWeightService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CourseScheduleMapper courseScheduleMapper;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody CourseGoalWeight courseGoalWeight) {
        courseGoalWeightService.saveOrUpdate(courseGoalWeight);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        courseGoalWeightService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseGoalWeightService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(courseGoalWeightService.list());
    }

    @GetMapping("/findAll/{teacherId}/{courseId}")
    public Result findAllByTeacherId(@PathVariable Integer teacherId, @PathVariable Integer courseId) {
        return Result.success(courseGoalWeightService.findAllByTeacherIdAndCourseId(teacherId,courseId));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(courseGoalWeightService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<CourseGoalWeight> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(courseGoalWeightService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @GetMapping("/findCourseGoal/{courseId}/{teacherId}/{clazzId}")
    public Result findCourseGoal(@PathVariable Integer courseId,
                                 @PathVariable Integer teacherId,
                                 @PathVariable Integer clazzId){
        String code = userMapper.selectByTeacherId(teacherId);
        List<CourseGoalWeight> list;
        if(code.equals("ROLE_TEACHER")){
            list = courseGoalWeightService.findCourseGoal(courseId,teacherId);
        }else{
            Integer id = courseScheduleMapper.selectTeacherIdByClazzIdCourseId(clazzId,courseId);
            list = courseGoalWeightService.findCourseGoal(courseId,id);
        }
        return Result.success(list);
    }
}

