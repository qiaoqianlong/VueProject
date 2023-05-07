package com.qingge.springboot.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.controller.dto.CourseReviewDTO;
import com.qingge.springboot.entity.Course;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.ICourseService;
import com.qingge.springboot.entity.Course;

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
 * @since 2022-03-31
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private ICourseService courseService;
    private Integer majorId;
    public Integer getMajorId() {
        return majorId;
    }
    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Course course) {
        courseService.saveOrUpdate(course);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        courseService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        courseService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(courseService.list());
    }

    @GetMapping("/course_types")
    public Result findCourseTypes() {
        List<String> courseTypes = courseService.findCourseTypes();
        return Result.success(courseTypes);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(courseService.getById(id));
    }

//    @GetMapping("/page")
//    public Result findPage(@RequestParam Integer pageNum,
//                           @RequestParam Integer pageSize,
//                           @RequestParam(defaultValue = "") String name) {
//        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        if (!"".equals(name)) {
//            queryWrapper.like("name", name);
//        }
//        return Result.success(courseService.page(new Page<>(pageNum, pageSize), queryWrapper));
//    }

    // 添加外键，需要自定义page方法
    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String courseType,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        // 方法二： 自定义分页方法
        Page<Course> page = courseService.findPage(new Page<>(pageNum, pageSize), name,courseType);
        return Result.success(page);
    }

    // 导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Course> list = courseService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
//        writer.addHeaderAlias("code", "编码");
//        writer.addHeaderAlias("name", "名称");
//        writer.addHeaderAlias("description", "描述");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("课程信息", "UTF-8");
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
        List<Course> list = reader.readAll(Course.class);
        for (Course c: list) {
            c.setMajorId(this.getMajorId());
        }
//        // 方式2：忽略表头的中文，直接读取表的内容
//        List<List<Object>> list = reader.read(1);
//        List<Course> courses = CollUtil.newArrayList();
//        for (List<Object> row : list) {
//            Course course = new Course();
//            course.setMajorId(this.getMajorId());
//            course.setName(row.get(0).toString());
//            course.setType(row.get(1).toString());
//            course.setProperties(row.get(2).toString());
//            course.setTerm(Convert.toInt(row.get(3)));
//            course.setCredit(Convert.toDouble(row.get(4)));
//            course.setTheoryHour(Convert.toDouble(row.get(5)));
//            course.setExpHour(Convert.toDouble(row.get(6)));
//            course.setTotalHour(Convert.toDouble(row.get(7)));
//            courses.add(course);
//        }
        courseService.saveBatch(list);
        return Result.success(true);
    }

    // 根据 教师id 获取所有课程
    @GetMapping("/courses_teacher/{teacherId}")
    public Result coursesByTeacherId(@PathVariable Integer teacherId) throws Exception {
        List<CourseReviewDTO> courseReviewDTOS = courseService.coursesByTeacherId(teacherId);
        return Result.success(courseReviewDTOS);
    }

    // 根据 教师id 获取所有课程
    @PostMapping("/saveMajorId/{majorId}")
    public Result courseMajorId(@PathVariable Integer majorId) throws Exception {
        this.setMajorId(majorId);
        return Result.success();
    }

}

