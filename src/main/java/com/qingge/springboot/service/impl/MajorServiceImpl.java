package com.qingge.springboot.service.impl;

import com.qingge.springboot.entity.Major;
import com.qingge.springboot.mapper.MajorMapper;
import com.qingge.springboot.service.IMajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-03-30
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements IMajorService {

}
