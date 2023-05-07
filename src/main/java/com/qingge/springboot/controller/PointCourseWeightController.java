package com.qingge.springboot.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.*;
import com.qingge.springboot.mapper.PointCourseWeightMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.IPointCourseWeightService;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程指标点矩阵表 前端控制器
 * </p>
 *
 * @author wano
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/point_course_weight")
public class PointCourseWeightController {

    @Resource
    private IPointCourseWeightService pointCourseWeightService;
    @Resource
    private PointCourseWeightMapper pointCourseWeightMapper;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody PointCourseWeight pointCourseWeight) {
        pointCourseWeightService.saveOrUpdate(pointCourseWeight);
//        pointCourseWeightMapper.insert();
//        pointCourseWeightService.saveBatch();
//        pointCourseWeightService.save()；
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pointCourseWeightService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        pointCourseWeightService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(pointCourseWeightService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(pointCourseWeightService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") Integer pointId) {
        Page<PointCourseWeight> page = pointCourseWeightService.findPage(new Page<>(pageNum, pageSize),pointId);
        return Result.success(page);
    }

    // 导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<PointCourseWeight> list = pointCourseWeightService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("课程支撑指标点权重矩阵", "UTF-8");
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
        List<Matrix> list = reader.readAll(Matrix.class);

        // 读取并存入课程指标点权重矩阵值
        PointCourseWeight pointCourseWeight = new PointCourseWeight();
        for (int i=0;i<list.size();i++) {
            Object[] values =  getFiledValues(list.get(i));
            // 纵坐标
            for (int j=0;j<values.length;j++) {
                if (values[j]!=null){
                    pointCourseWeight.setCourseId(i+1); // 课程id
                    pointCourseWeight.setPointId(j+1);  // 指标点id
                    pointCourseWeight.setWeight((Double) values[j]);
                    pointCourseWeightService.save(pointCourseWeight);
                    pointCourseWeight.setId(null);
                }
            }
        }

        return Result.success(true);
    }

    /**
     * 1、获取属性名数组
     * */
    private String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            // System.out.println(fields[i].getType());
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 2、根据属性名获取属性值
     * */
    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 3、获取对象的所有属性值，返回一个对象数组
     * */
    public Object[] getFiledValues(Object o){
        String[] fieldNames=this.getFiledName(o);
        Object[] value=new Object[fieldNames.length];
        for(int i=0;i<fieldNames.length;i++){
            value[i]=this.getFieldValueByName(fieldNames[i], o);
        }
        return value;
    }
}

