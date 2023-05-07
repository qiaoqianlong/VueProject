package com.qingge.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.controller.dto.PieDataDTO;
import com.qingge.springboot.controller.dto.UnitDataDTO;
import com.qingge.springboot.controller.dto.UnitFeedbackInfoDTO;
import com.qingge.springboot.entity.UnitQuestionnaireCount;
import com.qingge.springboot.mapper.UnitQuestionnaireCountMapper;
import com.qingge.springboot.service.IUnitQuestionnaireCountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wano
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/unit_questionnaire_count")
public class UnitQuestionnaireCountController {

    @Resource
    private IUnitQuestionnaireCountService unitQuestionnaireCountService;

    @Resource
    private UnitQuestionnaireCountMapper unitQuestionnaireCountMapper;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody UnitQuestionnaireCount unitQuestionnaireCount) {
        unitQuestionnaireCountService.saveOrUpdate(unitQuestionnaireCount);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        unitQuestionnaireCountService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        unitQuestionnaireCountService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(unitQuestionnaireCountService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(unitQuestionnaireCountService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<UnitQuestionnaireCount> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(unitQuestionnaireCountService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @GetMapping("/loadTableData1")
    public Result LoadTableData1(){
        List<UnitFeedbackInfoDTO> list = unitQuestionnaireCountService.loadTableData1();
        System.out.println(list);
        return Result.success(list);
    }

    @GetMapping("/loadTableData2")
    public Result LoadTableData2(){
        List<UnitDataDTO> list = new ArrayList<>();
        Integer num;
        for (num = 1; num < 6; num++){
            UnitDataDTO unitDataDTO = new UnitDataDTO();
            unitDataDTO.setRecognition(unitQuestionnaireCountMapper.selectRecognition(num));
            unitDataDTO.setEcoAdaptability(unitQuestionnaireCountMapper.selectEcoAdaptability(num));
            unitDataDTO.setEgCapability(unitQuestionnaireCountMapper.selectEgCapability(num));
            unitDataDTO.setEtdAdaptability(unitQuestionnaireCountMapper.selectEtdAdaptability(num));
            unitDataDTO.setHumanQuality(unitQuestionnaireCountMapper.selectHumanQuality(num));
            unitDataDTO.setIdAdaptability(unitQuestionnaireCountMapper.selectIdAdaptability(num));
            unitDataDTO.setLearnAbility(unitQuestionnaireCountMapper.selectLearnAbility(num));
            unitDataDTO.setProKnowledge(unitQuestionnaireCountMapper.selectProKnowledge(num));
            unitDataDTO.setSocialAbility(unitQuestionnaireCountMapper.selectSocialAbility(num));
            list.add(unitDataDTO);
        }
        System.out.println(list);
        return Result.success(list);
    }

    @GetMapping("/loadPie")
    public Result LoadPie(){
        PieDataDTO pieDataDTO = new PieDataDTO();
        pieDataDTO.setA(unitQuestionnaireCountMapper.selectA());
        pieDataDTO.setB(unitQuestionnaireCountMapper.selectB());
        pieDataDTO.setC(unitQuestionnaireCountMapper.selectC());
        pieDataDTO.setD(unitQuestionnaireCountMapper.selectD());
        pieDataDTO.setE(unitQuestionnaireCountMapper.selectE());
        pieDataDTO.setF(unitQuestionnaireCountMapper.selectF());
        System.out.println(pieDataDTO);
        return  Result.success(pieDataDTO);
    }

}

