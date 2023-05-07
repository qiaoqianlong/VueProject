<template>
  <div>

    <el-row>
<!--      <el-col :span="10">-->
<!--        <div id="main" style="width: 500px; height: 450px"></div>-->
<!--      </el-col>-->
        <h1>毕业生对培养目标合理性评价汇总数据表</h1>
        <el-table :data="tableData1"  border style="width: 60%;margin-bottom: 60px; " >
          <el-table-column prop="DegreeOfRation" label="合理性程度" width="100"></el-table-column>
          <el-table-column prop="Goal1" label="培养目标1" width="80"></el-table-column>
          <el-table-column prop="Goal2" label="培养目标2" width="80"></el-table-column>
          <el-table-column prop="Goal3" label="培养目标3" width="80"></el-table-column>
          <el-table-column prop="Goal4" label="培养目标4" width="80"></el-table-column>
          <el-table-column prop="Goal5" label="培养目标5" width="80"></el-table-column>
          <el-table-column prop="totalGoal" label="总体合理性" width="100"></el-table-column>
        </el-table>
    </el-row>

    <el-row>
        <el-col span="12">
            <div id="Pie1" style="width: 400px; height: 350px"></div>
        </el-col>
        <el-col span="12">
            <div id="Pie2" style="width: 400px; height: 350px"></div>
        </el-col>
    </el-row>

    <el-row>
          <el-col span="12">
              <div id="Pie3" style="width: 400px; height: 350px"></div>
          </el-col>
          <el-col span="12">
              <div id="Pie4" style="width: 400px; height: 350px"></div>
          </el-col>
    </el-row>

    <el-row>
          <el-col span="12">
              <div id="Pie5" style="width: 400px; height: 350px"></div>
          </el-col>
          <el-col span="12">
              <div id="Pie6" style="width: 400px; height: 350px"></div>
          </el-col>
      </el-row>

      <!--毕业生各项能力的自我评估表-->
    <el-row>
      <el-table :data="tableData2" style="width: 100%;;margin-bottom: 60px;">
        <el-table-column prop="level" label="程度" width="150"></el-table-column>
        <el-table-column label="毕业生各项工作能力的自我评估" >
          <el-table-column prop="baseTheory" label="基础理论" width="80"></el-table-column>
          <el-table-column prop="proKnowledge" label="专业知识" width="80"></el-table-column>
          <el-table-column prop="sfDesignPower" label="软件设计能力" width="80"></el-table-column>
          <el-table-column prop="flanguagePower" label="外语能力" width="100"></el-table-column>
          <el-table-column prop="sfExploitPower" label="软件开发能力" width="100"></el-table-column>
          <el-table-column prop="teamwork" label="团队意识" width="80"></el-table-column>
          <el-table-column prop="organizingAbility" label="组织能力" width="80"></el-table-column>
          <el-table-column prop="studyPower" label="学习能力" width="80"></el-table-column>
          <el-table-column prop="interPower" label="人际交往能力" width="100"></el-table-column>
          <el-table-column prop="creativity" label="创新能力" width="80"></el-table-column>
        </el-table-column>
      </el-table>
    </el-row>

    <div >
      <h1 style="position: center">能力评估柱状图</h1>
    </div>
    <el-row>
      <div id="ability" style="width: 1000px; height: 450px"></div>
    </el-row>

<!--    <el-row style="margin-bottom: 20px" justify="center" type="flex">
      <el-button type="primary" icon="el-icon-arrow-left" @click="back">返回</el-button>
    </el-row>-->

  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: "Home",
  data() {
    return {
      PieData1:[],
      PieData2:[],
      PieData3:[],
      PieData4:[],
      PieData5:[],
      PieData6:[],
      title:['product'],
      field1:['基础能力'],
      field2:['专业知识'],
      field3:['软件设计能力'],
      field4:['外语能力'],
      field5:['软件开发能力'],
      field6:['团队意识'],
      field7:['组织能力'],
      field8:['学习能力'],
      field9:['人际交往能力'],
      field10:['创新能力'],
      series:[],
      dataS:[],
      num1: 1,
      num2: 2,
      num3: 3,
      num4: 4,
      num5: 5,
      data:[],
      point_score:[],
      tableData1:[
        {DegreeOfRation:"非常合理",Goal1:"",Goal2:"",Goal3:"",Goal4:"",Goal5:"",totalGoal:""},
        {DegreeOfRation:"比较合理",Goal1:"",Goal2:"",Goal3:"",Goal4:"",Goal5:"",totalGoal:""},
        {DegreeOfRation:"一般合理",Goal1:"",Goal2:"",Goal3:"",Goal4:"",Goal5:"",totalGoal:""},
        {DegreeOfRation:"部分不合理",Goal1:"",Goal2:"",Goal3:"",Goal4:"",Goal5:"",totalGoal:""},
        {DegreeOfRation:"非常不合理",Goal1:"",Goal2:"",Goal3:"",Goal4:"",Goal5:"",totalGoal:""},
      ],
      tableData2:[
              {level:"精通", baseTheory:"",proKnowledge:"",sfDesignPower:"",flanguagePower:"",sfExploitPower:"",teamwork:"",organizingAbility:"",studyPower:"",interPower:"",creativity:""},
              {level:"熟练", baseTheory:"",proKnowledge:"",sfDesignPower:"",flanguagePower:"",sfExploitPower:"",teamwork:"",organizingAbility:"",studyPower:"",interPower:"",creativity:""},
              {level:"满意", baseTheory:"",proKnowledge:"",sfDesignPower:"",flanguagePower:"",sfExploitPower:"",teamwork:"",organizingAbility:"",studyPower:"",interPower:"",creativity:""},
              {level:"一般", baseTheory:"",proKnowledge:"",sfDesignPower:"",flanguagePower:"",sfExploitPower:"",teamwork:"",organizingAbility:"",studyPower:"",interPower:"",creativity:""},
              {level:"较差", baseTheory:"",proKnowledge:"",sfDesignPower:"",flanguagePower:"",sfExploitPower:"",teamwork:"",organizingAbility:"",studyPower:"",interPower:"",creativity:""},
      ],
      indicator:[],
    }
  },

  methods:{
/*    back(){
    },*/

    //毕业生各项能力的自我评估表的加载方法
    loadTableData2(){
      this.request.get('/questionnaire_count/selfAssess/' + this.num1).then(res=>{
        this.tableData2[0].baseTheory = res.data[0].baseTheory
        this.tableData2[0].proKnowledge = res.data[0].proKnowledge
        this.tableData2[0].sfDesignPower = res.data[0].sfDesignPower
        this.tableData2[0].sfExploitPower = res.data[0].sfExploitPower
        this.tableData2[0].studyPower = res.data[0].studyPower
        this.tableData2[0].organizingAbility = res.data[0].organizingAbility
        this.tableData2[0].interPower = res.data[0].interPower
        this.tableData2[0].flanguagePower = res.data[0].flanguagePower
        this.tableData2[0].teamwork = res.data[0].teamwork
        this.tableData2[0].creativity = res.data[0].creativity
      })

      this.request.get('/questionnaire_count/selfAssess/' + this.num2).then(res=>{
        this.tableData2[1].baseTheory = res.data[0].baseTheory
        this.tableData2[1].proKnowledge = res.data[0].proKnowledge
        this.tableData2[1].sfDesignPower = res.data[0].sfDesignPower
        this.tableData2[1].sfExploitPower = res.data[0].sfExploitPower
        this.tableData2[1].studyPower = res.data[0].studyPower
        this.tableData2[1].organizingAbility = res.data[0].organizingAbility
        this.tableData2[1].interPower = res.data[0].interPower
        this.tableData2[1].flanguagePower = res.data[0].flanguagePower
        this.tableData2[1].teamwork = res.data[0].teamwork
        this.tableData2[1].creativity = res.data[0].creativity
      })

      this.request.get('/questionnaire_count/selfAssess/' + this.num3).then(res=>{
        this.tableData2[2].baseTheory = res.data[0].baseTheory
        this.tableData2[2].proKnowledge = res.data[0].proKnowledge
        this.tableData2[2].sfDesignPower = res.data[0].sfDesignPower
        this.tableData2[2].sfExploitPower = res.data[0].sfExploitPower
        this.tableData2[2].studyPower = res.data[0].studyPower
        this.tableData2[2].organizingAbility = res.data[0].organizingAbility
        this.tableData2[2].interPower = res.data[0].interPower
        this.tableData2[2].flanguagePower = res.data[0].flanguagePower
        this.tableData2[2].teamwork = res.data[0].teamwork
        this.tableData2[2].creativity = res.data[0].creativity
      })

      this.request.get('/questionnaire_count/selfAssess/' + this.num4).then(res=>{
        this.tableData2[3].baseTheory = res.data[0].baseTheory
        this.tableData2[3].proKnowledge = res.data[0].proKnowledge
        this.tableData2[3].sfDesignPower = res.data[0].sfDesignPower
        this.tableData2[3].sfExploitPower = res.data[0].sfExploitPower
        this.tableData2[3].studyPower = res.data[0].studyPower
        this.tableData2[3].organizingAbility = res.data[0].organizingAbility
        this.tableData2[3].interPower = res.data[0].interPower
        this.tableData2[3].flanguagePower = res.data[0].flanguagePower
        this.tableData2[3].teamwork = res.data[0].teamwork
        this.tableData2[3].creativity = res.data[0].creativity
      })

      this.request.get('/questionnaire_count/selfAssess/' + this.num5).then(res=>{
        this.tableData2[4].baseTheory = res.data[0].baseTheory
        this.tableData2[4].proKnowledge = res.data[0].proKnowledge
        this.tableData2[4].sfDesignPower = res.data[0].sfDesignPower
        this.tableData2[4].sfExploitPower = res.data[0].sfExploitPower
        this.tableData2[4].studyPower = res.data[0].studyPower
        this.tableData2[4].organizingAbility = res.data[0].organizingAbility
        this.tableData2[4].interPower = res.data[0].interPower
        this.tableData2[4].flanguagePower = res.data[0].flanguagePower
        this.tableData2[4].teamwork = res.data[0].teamwork
        this.tableData2[4].creativity = res.data[0].creativity
      })
    },

    loadTableData1(){
      this.request.get('/questionnaire_count/loadTableData1').then(res=>{
          console.log(res.data)
          let i = 0
          res.data.forEach(item=>{
              this.tableData1[i].Goal1 = item.goal1
              this.tableData1[i].Goal2 = item.goal2
              this.tableData1[i].Goal3 = item.goal3
              this.tableData1[i].Goal4 = item.goal4
              this.tableData1[i].Goal5 = item.goal5
              this.tableData1[i].totalGoal = item.totalGoal
              i = i+1
          })
      })
    }

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
                this.field10,
        ]
      },
      xAxis: { type: 'category', axisLabel: {
          //x轴文字的配置
          show: true,
          interval: 0,//使x轴文字显示全
        }},
      yAxis: {},
      series: this.series
    };

    this.request.get('/questionnaire_count/selfAssessX/').then(res=>{
      //console.log(res.data)
      this.title.push("精通","熟练","满意","一般","较差")
      res.data.forEach(item=>{
        this.field1.push(item.baseTheory)
        this.field2.push(item.proKnowledge)
        this.field3.push(item.sfDesignPower)
        this.field4.push(item.flanguagePower)
        this.field5.push(item.sfExploitPower)
        this.field6.push(item.teamwork)
        this.field7.push(item.organizingAbility)
        this.field8.push(item.studyPower)
        this.field9.push(item.interPower)
        this.field10.push(item.creativity)
        this.series.push({ type: 'bar' })
      })

      option && myChart1.setOption(option);
            })


      var chartDom2 = document.getElementById('Pie1');
      var myChart2 = echarts.init(chartDom2);
      var option2;

      option2 = {
          title: {
              text: '培养目标1',
              //subtext: 'Fake Data',
              left: 'center'
          },
          tooltip: {
              trigger: 'item'
          },
          legend: {
              orient: 'vertical',
              right: 'right'
          },
          series: [
              {
                  name: '',
                  type: 'pie',
                  radius: '50%',
                  data: this.PieData1,
                  emphasis: {
                      itemStyle: {
                          shadowBlur: 5,
                          shadowOffsetX: 0,
                          shadowColor: 'rgba(0, 0, 0, 0.5)'
                      }
                  }
              }
          ]
      };

      this.request.get('/questionnaire_count/loadStuPieData1').then(res=>{
              console.log(res.data)
              const arr=["A.非常合理","B.比较合理","C.一般合理","D.部分不合理","E.非常不合理"]
              Object.keys(res.data).forEach((item,index)=>{
                 // console.log(res.data[item])
                  this.PieData1.push({
                      value:res.data[item],
                      name:arr[index]
                  })
              })
          console.log(this.PieData1)
          option2 && myChart2.setOption(option2);
          }
      )

      var chartDom3 = document.getElementById('Pie2');
      var myChart3 = echarts.init(chartDom3);
      var option3;

      option3 = {
          title: {
              text: '培养目标2',
              //subtext: 'Fake Data',
              left: 'center'
          },
          tooltip: {
              trigger: 'item'
          },
          legend: {
              orient: 'vertical',
              right: 'right'
          },
          series: [
              {
                  name: '',
                  type: 'pie',
                  radius: '50%',
                  data: this.PieData2,
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

      this.request.get('/questionnaire_count/loadStuPieData2').then(res=>{
              console.log(res.data)
              const arr=["A.非常合理","B.比较合理","C.一般合理","D.部分不合理","E.非常不合理"]
              Object.keys(res.data).forEach((item,index)=>{
                  // console.log(res.data[item])
                  this.PieData2.push({
                      value:res.data[item],
                      name:arr[index]
                  })
              })
              //console.log(this.PieData1)
              option3 && myChart3.setOption(option3);
          }
      )


      var chartDom4 = document.getElementById('Pie3');
      var myChart4 = echarts.init(chartDom4);
      var option4;

      option4 = {
          title: {
              text: '培养目标3',
              //subtext: 'Fake Data',
              left: 'center'
          },
          tooltip: {
              trigger: 'item'
          },
          legend: {
              orient: 'vertical',
              right: 'right'
          },
          series: [
              {
                  name: '',
                  type: 'pie',
                  radius: '50%',
                  data: this.PieData3,
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

      this.request.get('/questionnaire_count/loadStuPieData3').then(res=>{
              console.log(res.data)
              const arr=["A.非常合理","B.比较合理","C.一般合理","D.部分不合理","E.非常不合理"]
              Object.keys(res.data).forEach((item,index)=>{
                  // console.log(res.data[item])
                  this.PieData3.push({
                      value:res.data[item],
                      name:arr[index]
                  })
              })
              //console.log(this.PieData1)
              option4 && myChart4.setOption(option4);
          }
      )

      var chartDom5 = document.getElementById('Pie4');
      var myChart5 = echarts.init(chartDom5);
      var option5;

      option5 = {
          title: {
              text: '培养目标4',
              //subtext: 'Fake Data',
              left: 'center'
          },
          tooltip: {
              trigger: 'item'
          },
          legend: {
              orient: 'vertical',
              right: 'right'
          },
          series: [
              {
                  name: '',
                  type: 'pie',
                  radius: '50%',
                  data: this.PieData4,
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

      this.request.get('/questionnaire_count/loadStuPieData4').then(res=>{
              console.log(res.data)
              const arr=["A.非常合理","B.比较合理","C.一般合理","D.部分不合理","E.非常不合理"]
              Object.keys(res.data).forEach((item,index)=>{
                  // console.log(res.data[item])
                  this.PieData4.push({
                      value:res.data[item],
                      name:arr[index]
                  })
              })
              //console.log(this.PieData1)
              option5 && myChart5.setOption(option5);
          }
      )

      var chartDom6 = document.getElementById('Pie5');
      var myChart6 = echarts.init(chartDom6);
      var option6;

      option6 = {
          title: {
              text: '培养目标5',
              //subtext: 'Fake Data',
              left: 'center'
          },
          tooltip: {
              trigger: 'item'
          },
          legend: {
              orient: 'vertical',
              right: 'right'
          },
          series: [
              {
                  name: '',
                  type: 'pie',
                  radius: '50%',
                  data: this.PieData5,
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

      this.request.get('/questionnaire_count/loadStuPieData5').then(res=>{
              console.log(res.data)
              const arr=["A.非常合理","B.比较合理","C.一般合理","D.部分不合理","E.非常不合理"]
              Object.keys(res.data).forEach((item,index)=>{
                  // console.log(res.data[item])
                  this.PieData5.push({
                      value:res.data[item],
                      name:arr[index]
                  })
              })
              //console.log(this.PieData1)
              option6 && myChart6.setOption(option6);
          }
      )

      var chartDom7 = document.getElementById('Pie6');
      var myChart7 = echarts.init(chartDom7);
      var option7;

      option7 = {
          title: {
              text: '总体合理性',
              //subtext: 'Fake Data',
              left: 'center'
          },
          tooltip: {
              trigger: 'item'
          },
          legend: {
              orient: 'vertical',
              right: 'right'
          },
          series: [
              {
                  name: '',
                  type: 'pie',
                  radius: '50%',
                  data: this.PieData6,
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

      this.request.get('/questionnaire_count/loadStuPieData5').then(res=>{
              console.log(res.data)
              const arr=["A.非常合理","B.比较合理","C.一般合理","D.部分不合理","E.非常不合理"]
              Object.keys(res.data).forEach((item,index)=>{
                  this.PieData6.push({
                      value:res.data[item],
                      name:arr[index]
                  })
              })
              //console.log(this.PieData1)
              option7 && myChart7.setOption(option7);
          }
      )

  }
}
</script>

<style scoped>

</style>
