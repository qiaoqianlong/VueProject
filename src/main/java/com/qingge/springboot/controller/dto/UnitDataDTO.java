package com.qingge.springboot.controller.dto;

import lombok.Data;

@Data
public class UnitDataDTO {

    //毕业生各项工作能力的自我评估
    private Integer humanQuality;
    private Integer proKnowledge;
    private Integer egCapability;
    private Integer socialAbility;
    private Integer learnAbility;

    //培养目标合理性评价
    private Integer ecoAdaptability;
    private Integer etdAdaptability;
    private Integer idAdaptability;
    private Integer Recognition;
}
