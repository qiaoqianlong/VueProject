<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="12">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid" />专业名称</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{this.d.majorName}}
          </div>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-bank-card" /> 课程名称</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{this.d.courseName}}
          </div>
        </el-card>
      </el-col>

    </el-row>
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 450px"></div>
      </el-col>

      <el-col :span="12">
        <div id="pie" style="width: 500px; height: 400px"></div>
      </el-col>
    </el-row>

    <el-row style="margin-bottom: 20px" justify="center" type="flex">
      <el-button type="primary" icon="el-icon-arrow-left" @click="back">返回</el-button>
    </el-row>

  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: "Home",
  data() {
    return {
      title:['product'],
      fieldOne:['courseOne'],
      fieldTwo:['courseTwo'],
      fieldThree:['courseThree'],
      fieldFour:['courseFour'],
      fieldFive:['courseFive'],
      series:[],
      data:[],
      indicator:[],
      d:{
        majorName:{},
        majorId:{},
        courseId:{}
      }
    }
  },

  methods:{
    back(){
      this.$router.push('/major_course_goal_score')
    }
  },

  created() {
    console.log(JSON.parse(window.sessionStorage.getItem('data')) )
    Object.assign(this.d,JSON.parse(window.sessionStorage.getItem('data')))
  },
  mounted() {  // 页面元素渲染之后再触发



    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
      title: {
        text: '课程达成度雷达图'
      },
      legend: {
        data: [this.d.clazzName]
      },
      tooltip:{
        show:true,
        trigger:'item'
      },
      radar: {
        // shape: 'circle',
        indicator: this.indicator
      },

      series: [
        {
          name: 'Budget vs spending',
          type: 'radar',
          data: [
            {
              value:this.data,
              name:this.d.clazzName
            }
          ]
        }
      ]
    };

    var chartDom2 = document.getElementById('pie');
    var myChart2 = echarts.init(chartDom2);
    var option2;

    option2 = {
      title: {
        text: '分布图'
      },
      xAxis:{
        show:true,
        type:'category',

        axisLabel:{
          show:true,
          interval:0
        },
      },

      legend: {},
      tooltip: {},
      dataset: {
        source: [
          this.title,
          this.fieldOne,
          this.fieldTwo,
          this.fieldThree,
          this.fieldFour,
          this.fieldFive,
        ]
      },
      // xAxis: { type: 'category' },
      yAxis: {},
      series: this.series
    };



    this.request.get("/echarts/majorShow/" + this.d.courseId + "/" + this.d.majorId).then(res => {

      console.log(res.data)
      res.data.forEach(item=>{
        this.data.push(item.courseGoalScore)
        this.indicator.push({
          name:item.courseGoalName,
          max:1
        })
        this.title.push(item.courseGoalName)
        this.fieldOne.push(item.fieldOneCount)
        this.fieldTwo.push(item.fieldTwoCount)
        this.fieldThree.push(item.fieldThreeCount)
        this.fieldFive.push(item.fieldFiveCount)
        this.fieldFour.push(item.fieldFourCount)
        this.series.push({ type: 'bar' })
      })

      // 数据准备完毕之后再set
      option && myChart.setOption(option);
      option2 && myChart2.setOption(option2);
    })


  }
}
</script>

<style scoped>

</style>
