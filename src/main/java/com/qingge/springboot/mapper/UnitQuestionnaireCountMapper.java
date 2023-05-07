package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingge.springboot.controller.dto.UnitFeedbackInfoDTO;
import com.qingge.springboot.entity.UnitQuestionnaireCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-04-27
 */
public interface UnitQuestionnaireCountMapper extends BaseMapper<UnitQuestionnaireCount> {
    Integer selectHumanQuality(@Param("num") Integer num);
    Integer selectProKnowledge(@Param("num") Integer num);
    Integer selectEgCapability(@Param("num") Integer num);
    Integer selectSocialAbility(@Param("num") Integer num);
    Integer selectLearnAbility(@Param("num") Integer num);
    Integer selectEcoAdaptability(@Param("num") Integer num);
    Integer selectEtdAdaptability(@Param("num") Integer num);
    Integer selectIdAdaptability(@Param("num") Integer num);
    Integer selectRecognition(@Param("num") Integer num);

    Double selectA();
    Double selectB();
    Double selectC();
    Double selectD();
    Double selectE();
    Double selectF();

    List<UnitFeedbackInfoDTO> loadTableData1();
}
