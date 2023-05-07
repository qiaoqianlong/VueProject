package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.controller.dto.UnitFeedbackInfoDTO;
import com.qingge.springboot.entity.UnitQuestionnaireCount;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-27
 */
public interface IUnitQuestionnaireCountService extends IService<UnitQuestionnaireCount> {

    List<UnitFeedbackInfoDTO> loadTableData1();
}
