<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="12">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid" />班级</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{this.d.clazzName}}
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



    <el-row>
      <div style="margin: 10px 0">
      <span style="margin: 0 10px 0 0 ">
        选择课程目标进行班级散点图的查看
      </span>
        <el-select clearable placeholder="请选择课程目标" v-model="courseGoalId" >
          <el-option v-for="item in course_goal_score" :key="item.id" :label="item.name" :value="item.id"></el-option>
        </el-select>
        <el-button type="primary" @click="loadCourseOneGoalInfo" class="ml-5">确定 <i class="el-icon-circle-plus-outline" ></i></el-button>
      </div>
        <div id="point" style="width: 800px; height: 500px" justify="center"></div>
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
      option3:undefined,
      title:['product'],
      fieldOne:['courseOne'],
      fieldTwo:['courseTwo'],
      fieldThree:['courseThree'],
      fieldFour:['courseFour'],
      fieldFive:['courseFive'],
      data:[],
      course_goal_score:[],
      courseGoalId: null,
      series:[],
      indicator:[],
      d:{
        clazzName:{},
        clazzId:{},
        courseId:{}
      },
      Info:{
        id:"",
      }
    }
  },

  methods:{
    back(){
      this.$router.push('/clazz_course_goal_score')
    },

    loadCourseOneGoalInfo(){
      this.request.get("/student_course_goal_score/findOneCourseGoalInfo/" + this.courseGoalId + "/"+ this.d.courseId + "/" + this.d.clazzId).then(res=>{
       console.log(res.data)
        let arr = []
        res.data.forEach((item,index)=>{
          arr.push([index,item.score])
        })
        this.option3.series[0].data = arr
        let chartDom3 = document.getElementById('point');
        let myChart3 = echarts.init(chartDom3);
        this.option3 && myChart3.setOption(this.option3);
        console.log(this.option3)
      })
    },

    loadCourseGoalChoice(){
      this.request.get("/course_goal_weight/findCourseGoal/" + this.d.courseId + "/" +this.Info.id+ "/" +this.d.clazzId).then(res=>{
        //console.log(res.data)
        console.log(this.Info.id)
        this.course_goal_score = res.data
      })
    }
  },

  created() {
    console.log(JSON.parse(window.sessionStorage.getItem('data')) )
    console.log(JSON.parse(window.localStorage.getItem('user')) )
    Object.assign(this.d,JSON.parse(window.sessionStorage.getItem('data')))
    Object.assign(this.Info,JSON.parse(window.localStorage.getItem('user')))
    this.loadCourseGoalChoice()
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
      yAxis: {},
      series: this.series
    };


    let chartDom3 = document.getElementById('point');
    let myChart3 = echarts.init(chartDom3);

    this.option3 = {
      title: {
        text: '散点分布图'
      },
      xAxis: {},
      yAxis: {},
      series: [
        {
          symbolSize: 10,
          data: [0],
          type: 'scatter'
        }
      ]
    };



    this.request.get("/echarts/clazzShow/" + this.d.courseId + "/" + this.d.clazzId).then(res => {

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
      this.option3 && myChart3.setOption(this.option3);
    })




  }
}
</script>

<style scoped>

</style>
