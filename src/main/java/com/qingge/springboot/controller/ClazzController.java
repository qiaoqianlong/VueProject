package com.qingge.springboot.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.Clazz;
//import com.qingge.springboot.entity.Course;
import com.qingge.springboot.entity.Major;
import com.qingge.springboot.mapper.MajorMapper;
import com.qingge.springboot.service.IMajorService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.IClazzService;
import com.qingge.springboot.entity.Clazz;

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
@RequestMapping("/clazz")
public class ClazzController {

    @Resource
    private IClazzService clazzService;

    @Resource
    private MajorMapper majorMapper;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        clazzService.saveOrUpdate(clazz);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        clazzService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        clazzService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(clazzService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(clazzService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        Page<Clazz> page = clazzService.findPage(new Page<>(pageNum, pageSize), name);
        return Result.success(page);
    }

    // 导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Clazz> list = clazzService.list();
        Major major = new Major();
        for (Clazz clazz : list) {
            if(clazz.getMajorId() !=null){
                major = majorMapper.selectById(clazz.getMajorId());
                clazz.setMajor(major.getName());
            }
        }
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("班级信息", "UTF-8");
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
        List<Clazz> list = reader.readAll(Clazz.class);
//        // 方式2：忽略表头的中文，直接读取表的内容
//        List<List<Object>> list = reader.read(1);
//        List<Clazz> clazzs = CollUtil.newArrayList();
//        for (List<Object> row : list) {
//            Clazz clazz = new Clazz();
//            clazz.setCode(row.get(0).toString());
//            clazz.setName(row.get(1).toString());
//            clazz.setGrade(row.get(2).toString());
//            clazz.setDepart(row.get(3).toString());
//            clazzs.add(clazz);
//        }
        clazzService.saveBatch(list);
        return Result.success(true);
    }

    @GetMapping("/showClazzGradRequire")
    public Result ShowClazzGradRequire(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(defaultValue = "") String name,
                                       @RequestParam(defaultValue = "") String majorName){


        Page<Clazz> page = clazzService.ShowClazzGradRequire(new Page<>(pageNum, pageSize),name,majorName);
        return Result.success(page);
    }

}

