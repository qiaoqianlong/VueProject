<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="12">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid" />班级</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{this.d.name}}
          </div>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-bank-card" /> 专业名称</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{this.d.majorName}}
          </div>
        </el-card>
      </el-col>

    </el-row>
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 450px"></div>
      </el-col>

      <el-col :span="11">
        <el-table :data="tableData"  height="400" border style="width: 100%;margin-bottom: 60px; " >
          <el-table-column prop="pointName" label="指标点" width="80"></el-table-column>
          <el-table-column prop="Score" label="达成度" width="80"></el-table-column>
          <el-table-column prop="description" label="详细描述" ></el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin: 10px 0">
      <span style="margin: 0 10px 0 0 ">
        选择指标点进行班级散点图的查看
      </span>
        <el-select clearable placeholder="请选择指标点" v-model="pointId" >
          <el-option v-for="item in point_score" :key="item.id" :label="item.name" :value="item.id"></el-option>
        </el-select>
        <el-button type="primary" @click="loadClazzOnePointInfo" class="ml-5">确定 <i class="el-icon-circle-plus-outline" ></i></el-button>
      </div>
        <div id="point" style="width: 800px; height: 500px"></div>
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
      dataS:[],
      data:[],
      point_score:[],
      tableData:[],
      series:[],
      pointId: null,
      indicator:[],
      d:{
        clazzName:{},
        clazzId:{},
        courseId:{}
      }
    }
  },

  methods:{
    back(){
      this.$router.push('/clazzCourse')
    },

    loadClazzOnePointInfo(){
      this.request.get("/student_grad_require_score/findClazzOnePointInfo/" + this.pointId + "/"+ this.d.id).then(res=>{
      // console.log(res.data)
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

    loadPointChoice(){
      this.request.get("/clazz_grad_require_score/FindClazzPoint/" + this.d.id).then(res=>{
        this.point_score = res.data
      })
    }
  },

  created() {
    console.log(JSON.parse(window.sessionStorage.getItem('data')) )
    Object.assign(this.d,JSON.parse(window.sessionStorage.getItem('data')))
    this.loadPointChoice()

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



    this.request.get("/clazz_grad_require_score/ShowClazzGradRequireScore/"  + this.d.id).then(res => {
      console.log(res.data)
      res.data.forEach(item=>{
        this.data.push(item.score)
        this.indicator.push({
          name:item.pointName,
          max:1
        })

        this.tableData.push({
          pointName:item.pointName,
          Score:item.score,
          description:item.description
        })
      })

      // 数据准备完毕之后再set
      option && myChart.setOption(option);
      this.option3 && myChart3.setOption(this.option3);
    })


  }
}
</script>

<style scoped>

</style>
