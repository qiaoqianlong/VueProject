package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.controller.dto.SelfAssessDTO;
import com.qingge.springboot.entity.QuestionnaireCount;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wano
 * @since 2022-04-24
 */
public interface IQuestionnaireCountService extends IService<QuestionnaireCount> {

    //void saveAll(String grade, String name, String phone, String title1, String title2, Integer title3, Integer title4, Integer title5, Integer title6, Integer title7, Integer title8, Integer title9, Integer title10, Integer title11, Integer title13, Integer title14, Integer title15, Integer title16, Integer title17,Integer title18_1,);

    void saveAll(String grade, String name, String phone, String title1, String title2, Integer title3, Integer title4, Integer title5, Integer title6, Integer title7, Integer title8, Integer title9, Integer title10, Integer title11, Integer title13, Integer title14, Integer title15, Integer title16, Integer title17, Integer title18_1, Integer title18_2, Integer title18_3, Integer title18_4, Integer title18_5, Integer title18_6, Integer title18_7, Integer title18_8, Integer title18_9, Integer title18_10);

    List<SelfAssessDTO> SelfAssess(Integer num);
}
