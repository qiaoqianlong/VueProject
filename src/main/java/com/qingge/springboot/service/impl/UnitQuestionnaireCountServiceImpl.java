package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.controller.dto.UnitFeedbackInfoDTO;
import com.qingge.springboot.entity.UnitQuestionnaireCount;
import com.qingge.springboot.mapper.UnitQuestionnaireCountMapper;
import com.qingge.springboot.service.IUnitQuestionnaireCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-04-27
 */
@Service
public class UnitQuestionnaireCountServiceImpl extends ServiceImpl<UnitQuestionnaireCountMapper, UnitQuestionnaireCount> implements IUnitQuestionnaireCountService {

    @Resource
    UnitQuestionnaireCountMapper unitQuestionnaireCountMapper;

    @Override
    public List<UnitFeedbackInfoDTO> loadTableData1() {
        return unitQuestionnaireCountMapper.loadTableData1();
    }
}
