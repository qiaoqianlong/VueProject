package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Clazz;
import com.qingge.springboot.mapper.ClazzMapper;
import com.qingge.springboot.service.IClazzService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-03-31
 */
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements IClazzService {

    @Resource
    private ClazzMapper clazzMapper;
    @Override
    public Page<Clazz> findPage(Page<Clazz> page, String name) {
        return clazzMapper.findPage(page,name);
    }

    @Override
    public List<Clazz> findAll() {
        // 从数据库里查出所有的数据

        // 根据 majorid 查专业

        return null;
    }

    @Override
    public Page<Clazz> ShowClazzGradRequire(Page<Clazz> Page, String name, String majorName) {
        return clazzMapper.ShowClazzGradRequire(Page,name,majorName);
    }
}
