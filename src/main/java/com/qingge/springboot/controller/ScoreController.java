package com.qingge.springboot.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.*;
import com.qingge.springboot.mapper.CourseScheduleMapper;
import com.qingge.springboot.mapper.ScoreMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.IScoreService;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wano
 * @since 2022-04-01
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Resource
    private IScoreService scoreService;
    @Resource
    private CourseScheduleMapper courseScheduleMapper;

    CourseWeights courseWeights1 = new CourseWeights();

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Score score) {
        scoreService.saveOrUpdate(score);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        scoreService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        scoreService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(scoreService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(scoreService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam Integer teacherId,
                           @RequestParam Integer courseId,
                           @RequestParam Integer checkTeacherId,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String courseName,
                           @RequestParam(defaultValue = "") String teacherCheckName) {
        Page<Score> page = scoreService.findPage(new Page<>(pageNum, pageSize)
                ,name,courseName,teacherCheckName,teacherId,courseId,checkTeacherId);
        return Result.success(page);
    }
    @PostMapping("/export/params/")
    public Result exportParams(@RequestBody CourseWeights courseWeights) throws Exception {
        this.courseWeights1.setTeacherId(courseWeights.getTeacherId());
        this.courseWeights1.setCourseId(courseWeights.getCourseId());
        this.courseWeights1.setCheckTeacherId(courseWeights.getCheckTeacherId());
        return Result.success();
    }
    // 导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Score> list = scoreService.list(new QueryWrapper<Score>()
                .eq("teacher_id",this.courseWeights1.getTeacherId())
                .eq("course_id",this.courseWeights1.getCourseId())
                .eq("check_teacher_weight_id",this.courseWeights1.getCheckTeacherId()));
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("成绩信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }
    /**
     * excel 导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Score> list = reader.readAll(Score.class);
        for (Score score: list) {
            if(score != null){
                // 根据学生id，验证班级id并写入成绩（可拓展）
                score.setClazzId(courseScheduleMapper.getClazzIdByIds(courseWeights1.getTeacherId(),courseWeights1.getCourseId()));

                score.setTeacherId(courseWeights1.getTeacherId());
                score.setCourseId(courseWeights1.getCourseId());
                score.setCheckTeacherWeightId(courseWeights1.getCheckTeacherId());
                scoreService.save(score);
            }
        }
//        scoreService.saveBatch(list);
        return Result.success(true);
    }

    // 计算达成度
    @PostMapping("/calculate")
    public Result calculate()  {
        scoreService.checkTeacherScore();
        return Result.success(true);
    }
}

