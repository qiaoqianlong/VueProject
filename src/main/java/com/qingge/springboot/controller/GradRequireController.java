package com.qingge.springboot.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.Clazz;
import com.qingge.springboot.entity.GradRequire;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.service.IGradRequireService;
import com.qingge.springboot.entity.GradRequire;

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
@RequestMapping("/grad_require")
public class GradRequireController {

    @Resource
    private IGradRequireService gradRequireService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody GradRequire gradRequire) {
        gradRequireService.saveOrUpdate(gradRequire);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        gradRequireService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        gradRequireService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(gradRequireService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(gradRequireService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        QueryWrapper<GradRequire> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return Result.success(gradRequireService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    // 导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<GradRequire> list = gradRequireService.list();
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
        String fileName = URLEncoder.encode("毕业要求信息", "UTF-8");
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
        List<GradRequire> list = reader.readAll(GradRequire.class);

        // 方式2：忽略表头的中文，直接读取表的内容
//        List<List<Object>> list = reader.read(1);
//        List<GradRequire> gradReuires = CollUtil.newArrayList();
//        for (List<Object> row : list) {
//            GradRequire gradReuire = new GradRequire();
//            gradReuire.setCode(row.get(0).toString());
//            gradReuire.setName(row.get(1).toString());
//            gradReuire.setDescription(row.get(2).toString());
//            gradReuires.add(gradReuire);
//        }

        gradRequireService.saveBatch(list);
        return Result.success(true);
    }

}

