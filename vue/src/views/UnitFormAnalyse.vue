<template>
  <div>

    <el-row>
      <h1 style="margin-bottom: 30px;" align="center">用人单位对我校毕业生各项工作能力及专业培养目标合理性的评价统计</h1>
      <el-table :data="tableData2" style="width: 100%;;margin-bottom: 60px;">
        <el-table-column prop="level" label="程度" width="150"></el-table-column>
        <el-table-column label="毕业生各项工作能力的自我评估" >
          <el-table-column prop="humanQuality" label="人文素养" width="80"></el-table-column>
          <el-table-column prop="proKnowledge" label="专业知识" width="80"></el-table-column>
          <el-table-column prop="egCapability" label="工程能力" width="80"></el-table-column>
          <el-table-column prop="socialAbility" label="社会能力" width="100"></el-table-column>
          <el-table-column prop="learnAbility" label="学习能力" width="100"></el-table-column>
        </el-table-column>
        <el-table-column label="培养目标合理性评价" >
          <el-table-column prop="ecoAdaptability" label="经济发展适应度" width="100"></el-table-column>
          <el-table-column prop="etdAdaptability" label="工程技术发展适应度" width="100"></el-table-column>
          <el-table-column prop="idAdaptability" label="产业发展适应度" width="100"></el-table-column>
          <el-table-column prop="Recognition" label="总体认可度" width="100"></el-table-column>
        </el-table-column>
      </el-table>
    </el-row>

    <div >
      <h1 style="margin-bottom: 30px;" align="center">用人单位对毕业生能力的满意度及培养目标合理性的评价</h1>
    </div>
    <el-row>
      <div id="ability" style="width: 100%; height: 450px"></div>
    </el-row>

    <div>
      <h1 style="margin-bottom: 30px;" align="center">用人单位对毕业生各项能力的满意度及培养目标合理性的评价统计</h1>
    </div>
    <el-row>
      <div id="pie" style="width: 100%; height: 450px"></div>
    </el-row>

    <div>
      <h1 style="margin-bottom: 30px;" align="center">参与培养目标评价的用人单位反馈意见</h1>
    </div>
    <el-row>
      <el-table :data="tableData1" height="250" border style="width: 100%">
        <el-table-column prop="enterpriseName" label="用人单位" width="280" align="center"></el-table-column>
        <el-table-column prop="feedbackInfo" label="用人单位反馈意见" width="742" align="center"></el-table-column>
      </el-table>
    </el-row>


  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: "Home",
  data() {
    return {
      btuData:[],
      title:['product'],
      field1:['人文素养'],
      field2:['专业知识'],
      field3:['工程能力'],
      field4:['社会能力'],
      field5:['学习能力'],
      field6:['经济发展适应度'],
      field7:['工程技术发展适应度'],
      field8:['产业发展适应度'],
      field9:['总体认可度'],
      name:[],
      value:[],
      series:[],
      data:[],
      tableData1:[],
      tableData2:[
              {level:"满意", humanQuality:"",proKnowledge:"",egCapability:"",socialAbility:"",learnAbility:"",ecoAdaptability:"",etdAdaptability:"",idAdaptability:"",Recognition:""},
              {level:"比较满意", humanQuality:"",proKnowledge:"",egCapability:"",socialAbility:"",learnAbility:"",ecoAdaptability:"",etdAdaptability:"",idAdaptability:"",Recognition:""},
              {level:"一般", humanQuality:"",proKnowledge:"",egCapability:"",socialAbility:"",learnAbility:"",ecoAdaptability:"",etdAdaptability:"",idAdaptability:"",Recognition:""},
              {level:"较差", humanQuality:"",proKnowledge:"",egCapability:"",socialAbility:"",learnAbility:"",ecoAdaptability:"",etdAdaptability:"",idAdaptability:"",Recognition:""},
              {level:"很差", humanQuality:"",proKnowledge:"",egCapability:"",socialAbility:"",learnAbility:"",ecoAdaptability:"",etdAdaptability:"",idAdaptability:"",Recognition:""},
      ],
      indicator:[],
    }
  },

  methods:{
/*    back(){

    },*/

    loadTableData1(){
      this.request.get('/unit_questionnaire_count/loadTableData1').then(res=>{
        console.log(res.data)
        res.data.forEach(item=>{
         /* this.tableData1.enterpriseName = item.enterpriseName
          this.tableData1.feedbackInfo = item.feedbackInfo*/
          this.tableData1.push({
            enterpriseName: item.enterpriseName,
            feedbackInfo: item.feedbackInfo
          })
        })
        console.log(this.tableData1)
      })
    },

    //毕业生各项能力的自我评估表的加载方法
    loadTableData2(){
      this.request.get('/unit_questionnaire_count/loadTableData2').then(res=>{
        //console.log(res.data)
        let i = 0
        res.data.forEach(item=>{
          this.tableData2[i].humanQuality = item.humanQuality
          this.tableData2[i].proKnowledge = item.proKnowledge
          this.tableData2[i].egCapability = item.egCapability
          this.tableData2[i].socialAbility = item.socialAbility
          this.tableData2[i].learnAbility = item.learnAbility
          this.tableData2[i].ecoAdaptability = item.ecoAdaptability
          this.tableData2[i].etdAdaptability = item.etdAdaptability
          this.tableData2[i].idAdaptability = item.idAdaptability
          this.tableData2[i].Recognition = item.recognition
          i = i+1
        })
      })
    },

  },

  created() {
    this.loadTableData1()
    this.loadTableData2()
  },
  mounted() {  // 页面元素渲染之后再触发

    var chartDom1 = document.getElementById('ability');
    var myChart1 = echarts.init(chartDom1);
    var option;

    option = {
      legend: {},
      tooltip: {},
      dataset: {
        source: [
                this.title,
                this.field1,
                this.field2,
                this.field3,
                this.field4,
                this.field5,
                this.field6,
                this.field7,
                this.field8,
                this.field9,
        ]
      },
      xAxis: { type: 'category', axisLabel: {
          //x轴文字的配置
          show: true,
          interval: 0,//使x轴文字显示全
          formatter:function(value){
            var str = "";
            var num = 4; //每行显示字数
            var valLength = value.length; //该项x轴字数
            var rowNum = Math.ceil(valLength / num); // 行数

            if(rowNum > 1) {
              for(var i = 0; i < rowNum; i++) {
                var temp = "";
                var start = i * num;
                var end = start + num;

                temp = value.substring(start, end) + "\n";
                str += temp;
              }
              return str;
            } else {
              return value;
            }
          }
        }},
      yAxis: {},
      series: this.series
    };

    this.request.get('/unit_questionnaire_count/loadTableData2').then(res=>{
      console.log(res.data)
      this.title.push("满意","比较满意","一般","较差","很差")
      res.data.forEach(item=>{
        this.field1.push(item.humanQuality)
        this.field2.push(item.proKnowledge)
        this.field3.push(item.egCapability)
        this.field4.push(item.socialAbility)
        this.field5.push(item.learnAbility)
        this.field6.push(item.ecoAdaptability)
        this.field7.push(item.etdAdaptability)
        this.field8.push(item.idAdaptability)
        this.field9.push(item.recognition)
        this.series.push({ type: 'bar' })
      })
      option && myChart1.setOption(option);
            })

    var chartDom2 = document.getElementById('pie');
    var myChart2 = echarts.init(chartDom2);
    var option2;

    option2 = {
      title: {
        text: '培养目标合理性的评价统计图',
        //subtext: 'Fake Data',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: '',
          type: 'pie',
          radius: '50%',
          data: this.btuData,
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };

    this.request.get('/unit_questionnaire_count/loadPie').then(res=>{
      console.log(res.data)
      const arr=["A.责任感和职业道德","B.专业知识","C.工程设计实践能力","D.团队合作能力","E.国际视野","F.沟通能力"]
      Object.keys(res.data).forEach((item,index)=>{
        this.btuData.push({
          value:res.data[item],
          name:arr[index]
        })
      })
      console.log(this.btuData)
      option2 && myChart2.setOption(option2);
    }
    )


  }
}
</script>

<style scoped>

</style>
