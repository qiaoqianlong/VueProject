<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 20px">
      <el-col :span="8">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid" />学生姓名</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{this.d.studentName}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card style="color: #F56C6C">
          <div><i class="el-icon-money" />学生学号</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{this.d.studentCode}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-bank-card" /> 班级名称</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{this.d.clazzName}}
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 450px"></div>
      </el-col>

      <el-col :span="11">
        <el-table :data="tableData" height="400" border style="width: 100%;margin-bottom: 60px; " >
        <el-table-column prop="pointName" label="指标点" width="280"></el-table-column>
        <el-table-column prop="Score" label="达成度" width="280"></el-table-column>
        </el-table>

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
      Boolean:true,
      pageNum: 1,
      pageSize: 5,
      data:[],
      tableData:[],
      tableDataX:[],
      indicator:[],
      d:{
        studentName:{},
        studentCode:{},
        studentId:{},
        courseId:{}
      }
    }
  },

  methods:{
    back(){
      this.$router.push('/studentGradRequire')
    }
  },

  created() {
    //this.load()
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
        data: [this.d.studentName]
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
          name: '',
          type: 'radar',
          data: [
            {
              value:this.data,
              name:this.d.studentName
            }
          ]
        }
      ]
    };



    this.request.get("/student_grad_require_score/ShowStuGradRequireScore/" + this.d.studentId).then(res => {
      console.log(res.data)
      res.data.forEach(item=>{
        this.data.push(item.score)
        this.indicator.push({
          name:item.pointName,
          max:1
        })
        this.tableData.push({
          pointName:item.pointName,
          Score:item.score
        })
      })

      // 数据准备完毕之后再set
      option && myChart.setOption(option);

    })

  },

}
</script>

<style scoped>

</style>
