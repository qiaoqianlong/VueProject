package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingge.springboot.controller.dto.SelfAssessDTO;
import com.qingge.springboot.entity.QuestionnaireCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wano
 * @since 2022-04-24
 */
public interface QuestionnaireCountMapper extends BaseMapper<QuestionnaireCount> {

    //void saveAll(String grade, String name, String phone, String title1, String title2, Integer title3, Integer title4, Integer title5, Integer title6, Integer title7, Integer title8, Integer title9, Integer title10, Integer title11, Integer title12, Integer title13, Integer title14, Integer title15, Integer title16, Integer title17);

    void saveAll(String grade, String name, String phone, String title1, String title2, Integer title3, Integer title4, Integer title5, Integer title6, Integer title7, Integer title8, Integer title9, Integer title10, Integer title11, Integer title13, Integer title14, Integer title15, Integer title16, Integer title17, Integer title18_1, Integer title18_2, Integer title18_3, Integer title18_4, Integer title18_5, Integer title18_6, Integer title18_7, Integer title18_8, Integer title18_9, Integer title18_10);

    List<SelfAssessDTO> SelfAssess(@Param("num") Integer num);

    Integer selectBaseTheory(@Param("num") Integer num);

    Integer selectProKnowledge(@Param("num") Integer num);

    Integer selectSfDesignPower(@Param("num") Integer num);

    Integer selectFLanguagePower(@Param("num") Integer num);

    Integer selectSfExploitPower(@Param("num") Integer num);

    Integer selectTeamwork(@Param("num") Integer num);

    Integer selectOrganizingAbility(@Param("num") Integer num);

    Integer selectStudyPower(@Param("num") Integer num);

    Integer selectInterPower(@Param("num") Integer num);

    Integer selectCreativity(@Param("num") Integer num);

    Integer selectGoal1(@Param("num") Integer num);
    Integer selectGoal2(@Param("num") Integer num);
    Integer selectGoal3(@Param("num") Integer num);
    Integer selectGoal4(@Param("num") Integer num);
    Integer selectGoal5(@Param("num") Integer num);
    Integer selectTotalGoal(@Param("num") Integer num);

    Double selectPerGoal1(@Param("num") Integer num);

    Double selectPerGoal2(@Param("num") Integer num);
    Double selectPerGoal3(@Param("num") Integer num);
    Double selectPerGoal4(@Param("num") Integer num);
    Double selectPerGoal5(@Param("num") Integer num);
    Double selectPertotalGoal(@Param("num") Integer num);

}
