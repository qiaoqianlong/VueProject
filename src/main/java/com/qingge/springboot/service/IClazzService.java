package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Clazz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-03-31
 */
public interface IClazzService extends IService<Clazz> {

    Page<Clazz> findPage(Page<Clazz> page, String name);

    List<Clazz> findAll();

    Page<Clazz> ShowClazzGradRequire(Page<Clazz> Page, String name, String majorName);
}
