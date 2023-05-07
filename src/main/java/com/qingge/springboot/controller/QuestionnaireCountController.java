package com.qingge.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.controller.dto.SelfAssessDTO;
import com.qingge.springboot.controller.dto.StuPieDataDTO;
import com.qingge.springboot.controller.dto.TrainGoalDTO;
import com.qingge.springboot.entity.QuestionnaireCount;
import com.qingge.springboot.mapper.QuestionnaireCountMapper;
import com.qingge.springboot.service.IQuestionnaireCountService;
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
 * @since 2022-04-24
 */
@RestController
@RequestMapping("/questionnaire_count")
public class QuestionnaireCountController {

    @Resource
    private IQuestionnaireCountService questionnaireCountService;
    @Resource
    private QuestionnaireCountMapper questionnaireCountMapper;



    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody QuestionnaireCount questionnaireCount) {
        questionnaireCountService.saveOrUpdate(questionnaireCount);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        questionnaireCountService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        questionnaireCountService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(questionnaireCountService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(questionnaireCountService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<QuestionnaireCount> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(questionnaireCountService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @GetMapping("/count")
    public Result countData(@RequestParam String grade,
                            @RequestParam String name,
                            @RequestParam String phone,
                            @RequestParam String title1,
                            @RequestParam String title2,
                            @RequestParam Integer title3,
                            @RequestParam Integer title4,
                            @RequestParam Integer title5,
                            @RequestParam Integer title6,
                            @RequestParam Integer title7,
                            @RequestParam Integer title8,
                            @RequestParam Integer title9,
                            @RequestParam Integer title10,
                            @RequestParam Integer title11,
                            @RequestParam Integer title13,
                            @RequestParam Integer title14,
                            @RequestParam Integer title15,
                            @RequestParam Integer title16,
                            @RequestParam Integer title17,
                            @RequestParam Integer title18_1,
                            @RequestParam Integer title18_2,
                            @RequestParam Integer title18_3,
                            @RequestParam Integer title18_4,
                            @RequestParam Integer title18_5,
                            @RequestParam Integer title18_6,
                            @RequestParam Integer title18_7,
                            @RequestParam Integer title18_8,
                            @RequestParam Integer title18_9,
                            @RequestParam Integer title18_10

    ){

        questionnaireCountService.saveAll(grade,name,phone,title1,title2,title3,title4,title5,title6,title7,title8,title9,title10,title11,title13,title14,title15,title16,title17,title18_1,title18_2,title18_3,title18_4,title18_5,title18_6,title18_7,title18_8,title18_9,title18_10);
        return Result.success();
    }


    @GetMapping("/selfAssess/{num}")
    public Result SelfAssess(@PathVariable Integer num){
        //List<SelfAssessDTO> list = questionnaireCountService.SelfAssess(num);
        List<SelfAssessDTO> list = new ArrayList<>();
        SelfAssessDTO selfAssessDTO = new SelfAssessDTO();
        selfAssessDTO.setBaseTheory(questionnaireCountMapper.selectBaseTheory(num));
        selfAssessDTO.setProKnowledge(questionnaireCountMapper.selectProKnowledge(num));
        selfAssessDTO.setSfDesignPower(questionnaireCountMapper.selectSfDesignPower(num));
        selfAssessDTO.setFLanguagePower(questionnaireCountMapper.selectFLanguagePower(num));
        selfAssessDTO.setSfExploitPower(questionnaireCountMapper.selectSfExploitPower(num));
        selfAssessDTO.setTeamwork(questionnaireCountMapper.selectTeamwork(num));
        selfAssessDTO.setOrganizingAbility(questionnaireCountMapper.selectOrganizingAbility(num));
        selfAssessDTO.setStudyPower(questionnaireCountMapper.selectStudyPower(num));
        selfAssessDTO.setInterPower(questionnaireCountMapper.selectInterPower(num));
        selfAssessDTO.setCreativity(questionnaireCountMapper.selectCreativity(num));
        System.out.println(selfAssessDTO);
        list.add(selfAssessDTO);
        System.out.println(list);
        return Result.success(list);
    }

    @GetMapping("/selfAssessX")
    public Result SelfAssessX(){
        //List<SelfAssessDTO> list = questionnaireCountService.SelfAssess(num);
        List<SelfAssessDTO> list = new ArrayList<>();
        Integer num;
        for(num = 1;num<6;num++){
            SelfAssessDTO selfAssessDTO = new SelfAssessDTO();
            selfAssessDTO.setBaseTheory(questionnaireCountMapper.selectBaseTheory(num));
            selfAssessDTO.setProKnowledge(questionnaireCountMapper.selectProKnowledge(num));
            selfAssessDTO.setSfDesignPower(questionnaireCountMapper.selectSfDesignPower(num));
            selfAssessDTO.setFLanguagePower(questionnaireCountMapper.selectFLanguagePower(num));
            selfAssessDTO.setSfExploitPower(questionnaireCountMapper.selectSfExploitPower(num));
            selfAssessDTO.setTeamwork(questionnaireCountMapper.selectTeamwork(num));
            selfAssessDTO.setOrganizingAbility(questionnaireCountMapper.selectOrganizingAbility(num));
            selfAssessDTO.setStudyPower(questionnaireCountMapper.selectStudyPower(num));
            selfAssessDTO.setInterPower(questionnaireCountMapper.selectInterPower(num));
            selfAssessDTO.setCreativity(questionnaireCountMapper.selectCreativity(num));
            //System.out.println(num);
            //System.out.println(selfAssessDTO);
            list.add(selfAssessDTO);
        }
        //System.out.println(list);
        return Result.success(list);
    }

    @GetMapping("/loadTableData1")
    public Result LoadTableData1(){
        List<TrainGoalDTO> list = new ArrayList<>();
        Integer num;
        for (num = 1; num<6; num++){
            TrainGoalDTO trainGoalDTO = new TrainGoalDTO();
            trainGoalDTO.setGoal1(questionnaireCountMapper.selectGoal1(num));
            trainGoalDTO.setGoal2(questionnaireCountMapper.selectGoal2(num));
            trainGoalDTO.setGoal3(questionnaireCountMapper.selectGoal3(num));
            trainGoalDTO.setGoal4(questionnaireCountMapper.selectGoal4(num));
            trainGoalDTO.setGoal5(questionnaireCountMapper.selectGoal5(num));
            trainGoalDTO.setTotalGoal(questionnaireCountMapper.selectTotalGoal(num));
            list.add(trainGoalDTO);
        }
        System.out.println(list);
        return Result.success(list);
    }

    @GetMapping("/loadStuPieData1")
    public Result LoadStuPieData1(){
        StuPieDataDTO stuPieDataDTO = new StuPieDataDTO();
        stuPieDataDTO.setLevel1(questionnaireCountMapper.selectPerGoal1(1));
        stuPieDataDTO.setLevel2(questionnaireCountMapper.selectPerGoal1(2));
        stuPieDataDTO.setLevel3(questionnaireCountMapper.selectPerGoal1(3));
        stuPieDataDTO.setLevel4(questionnaireCountMapper.selectPerGoal1(4));
        stuPieDataDTO.setLevel5(questionnaireCountMapper.selectPerGoal1(5));
        return Result.success(stuPieDataDTO);
    }

    @GetMapping("/loadStuPieData2")
    public Result LoadStuPieData2(){
        StuPieDataDTO stuPieDataDTO = new StuPieDataDTO();
        stuPieDataDTO.setLevel1(questionnaireCountMapper.selectPerGoal2(1));
        stuPieDataDTO.setLevel2(questionnaireCountMapper.selectPerGoal2(2));
        stuPieDataDTO.setLevel3(questionnaireCountMapper.selectPerGoal2(3));
        stuPieDataDTO.setLevel4(questionnaireCountMapper.selectPerGoal2(4));
        stuPieDataDTO.setLevel5(questionnaireCountMapper.selectPerGoal2(5));
        return Result.success(stuPieDataDTO);
    }

    @GetMapping("/loadStuPieData3")
    public Result LoadStuPieData3(){
        StuPieDataDTO stuPieDataDTO = new StuPieDataDTO();
        stuPieDataDTO.setLevel1(questionnaireCountMapper.selectPerGoal3(1));
        stuPieDataDTO.setLevel2(questionnaireCountMapper.selectPerGoal3(2));
        stuPieDataDTO.setLevel3(questionnaireCountMapper.selectPerGoal3(3));
        stuPieDataDTO.setLevel4(questionnaireCountMapper.selectPerGoal3(4));
        stuPieDataDTO.setLevel5(questionnaireCountMapper.selectPerGoal3(5));
        return Result.success(stuPieDataDTO);
    }


    @GetMapping("/loadStuPieData4")
    public Result LoadStuPieData4(){
        StuPieDataDTO stuPieDataDTO = new StuPieDataDTO();
        stuPieDataDTO.setLevel1(questionnaireCountMapper.selectPerGoal4(1));
        stuPieDataDTO.setLevel2(questionnaireCountMapper.selectPerGoal4(2));
        stuPieDataDTO.setLevel3(questionnaireCountMapper.selectPerGoal4(3));
        stuPieDataDTO.setLevel4(questionnaireCountMapper.selectPerGoal4(4));
        stuPieDataDTO.setLevel5(questionnaireCountMapper.selectPerGoal4(5));
        return Result.success(stuPieDataDTO);
    }

    @GetMapping("/loadStuPieData5")
    public Result LoadStuPieData5(){
        StuPieDataDTO stuPieDataDTO = new StuPieDataDTO();
        stuPieDataDTO.setLevel1(questionnaireCountMapper.selectPerGoal5(1));
        stuPieDataDTO.setLevel2(questionnaireCountMapper.selectPerGoal5(2));
        stuPieDataDTO.setLevel3(questionnaireCountMapper.selectPerGoal5(3));
        stuPieDataDTO.setLevel4(questionnaireCountMapper.selectPerGoal5(4));
        stuPieDataDTO.setLevel5(questionnaireCountMapper.selectPerGoal5(5));
        return Result.success(stuPieDataDTO);
    }

    @GetMapping("/loadStuPieTotalData")
    public Result LoadStuPieTotalData(){
        StuPieDataDTO stuPieDataDTO = new StuPieDataDTO();
        stuPieDataDTO.setLevel1(questionnaireCountMapper.selectPertotalGoal(1));
        stuPieDataDTO.setLevel2(questionnaireCountMapper.selectPertotalGoal(2));
        stuPieDataDTO.setLevel3(questionnaireCountMapper.selectPertotalGoal(3));
        stuPieDataDTO.setLevel4(questionnaireCountMapper.selectPertotalGoal(4));
        stuPieDataDTO.setLevel5(questionnaireCountMapper.selectPertotalGoal(5));
        return Result.success(stuPieDataDTO);
    }
}

