<template>
  <div>
    <!--选择配置课程-->
    <div style="margin: 10px 0">
      <span style="margin: 0 10px 0 0 ">
        选择要查询的专业
      </span>
    </div>


    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入专业名称" suffix-icon="el-icon-s-custom" v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="请输入课程名称" suffix-icon="el-icon-message" class="ml-5" v-model="courseName"></el-input>
      <!--<el-input style="width: 200px" placeholder="请输入教师考核项名称" suffix-icon="el-icon-position" class="ml-5" v-model="teacherCheckName"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column prop="majorName" label="专业"></el-table-column>
      <el-table-column prop="courseName" label="课程"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="CalculateData(scope.$index)">计算<i class="el-icon-thumb"></i></el-button>
          <el-button type="primary" @click="showMajor(scope.$index)">预览<i class="el-icon-zoom-in"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import {serverIp} from "../../public/config";

export default {
  name: "Score",
  data() {
    return {
      serverIp: serverIp,
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      name:"",
      courseName: "",
      multipleSelection: [],
      courseWeightsTableData: [],
      teacher_courses: [],
      courseId: 0,
      courseWeightsObj:{},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    this.load()

  },
  methods: {

    load() {
      this.request.get("/major_course_goal_score/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          courseName: this.courseName,
        }
      }).then(res => {
        this.tableData = res.data.records
        console.log(res.data.records)
        this.total = parseInt(res.data.total)
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },

    reset() {
      this.name = ""
      this.courseName = ""
      //this.teacherCheckName = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },


    showMajor(index){
      console.log(this.tableData[index])
      window.sessionStorage.setItem('data',JSON.stringify(this.tableData[index]) )
      this.$router.push('/showMajor')
    },

    // 专业计算达成度
    CalculateData(index){
      console.log(this.tableData[index])
      this.request.get("/major_course_goal_score/calculate", {
        params: {
          majorId : this.tableData[index].majorId,
          courseId : this.tableData[index].courseId,
          grade : this.tableData[index].grade
        }
      })
    }
  }
}
</script>


<style>
.headerBg {
  background: #eee!important;
}
</style>
