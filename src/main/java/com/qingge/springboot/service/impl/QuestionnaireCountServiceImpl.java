package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.controller.dto.SelfAssessDTO;
import com.qingge.springboot.entity.QuestionnaireCount;
import com.qingge.springboot.mapper.QuestionnaireCountMapper;
import com.qingge.springboot.service.IQuestionnaireCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wano
 * @since 2022-04-24
 */
@Service
public class QuestionnaireCountServiceImpl extends ServiceImpl<QuestionnaireCountMapper, QuestionnaireCount> implements IQuestionnaireCountService {

    @Resource
    QuestionnaireCountMapper questionnaireCountMapper;

    /*@Override
    public void saveAll(String grade, String name, String phone, String title1, String title2, Integer title3, Integer title4, Integer title5, Integer title6, Integer title7, Integer title8, Integer title9, Integer title10, Integer title11, Integer title12, Integer title13, Integer title14, Integer title15, Integer title16, Integer title17) {
        questionnaireCountMapper.saveAll(grade, name, phone, title1, title2, title3, title4, title5, title6, title7, title8, title9, title10, title11, title12, title13, title14, title15, title16, title17);
    }*/

    @Override
    public void saveAll(String grade, String name, String phone, String title1, String title2, Integer title3, Integer title4, Integer title5, Integer title6, Integer title7, Integer title8, Integer title9, Integer title10, Integer title11, Integer title13, Integer title14, Integer title15, Integer title16, Integer title17, Integer title18_1, Integer title18_2, Integer title18_3, Integer title18_4, Integer title18_5, Integer title18_6, Integer title18_7, Integer title18_8, Integer title18_9, Integer title18_10) {
        questionnaireCountMapper.saveAll(grade,name,phone,title1,title2,title3,title4,title5,title6,title7,title8,title9,title10,title11,title13,title14,title15,title16,title17,title18_1,title18_2,title18_3,title18_4,title18_5,title18_6,title18_7,title18_8,title18_9,title18_10);
    }

    @Override
    public List<SelfAssessDTO> SelfAssess(Integer num) {
        return questionnaireCountMapper.SelfAssess(num);
    }
}
