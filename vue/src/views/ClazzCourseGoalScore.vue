<template>
  <div>
    <!--选择配置课程-->
    <div style="margin: 10px 0">
      <span style="margin: 0 10px 0 0 ">
        选择要查询的班级
      </span>
    </div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入班级名" suffix-icon="el-icon-s-custom" v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="请输入课程名" suffix-icon="el-icon-message" class="ml-5" v-model="courseName"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="clazzName" label="班级"></el-table-column>
      <el-table-column prop="courseName" label="课程"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="CalculateData(scope.$index)">计算<i class="el-icon-thumb"></i></el-button>
          <el-button type="primary" @click="showClazz(scope.$index)">预览<i class="el-icon-zoom-in"></i></el-button>
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
  name: "ClazzCourse",
  data() {
    return {
      serverIp: serverIp,
      tableData: [],
      total: 0,
      pageNum: 1,
      Info: localStorage.getItem("user.id"),
      pageSize: 5,
      name:"",
      courseName: "",
      multipleSelection: [],
      courseWeightsTableData: [],
      teacher_courses: [],
      courseId: 0,
      courseWeightsObj:{},
      teacherId:undefined,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    console.log(JSON.parse(window.localStorage.getItem('user')) )
    this.teacherId = this.user.id
    console.log(this.teacherId)
    this.load()

  },
  methods: {

    load() {
      this.request.get("/course_schedule/find_clazz_course_Info", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          courseName: this.courseName,
          teacherId: this.teacherId,
        }
      }).then(res => {
        this.tableData = res.data.records
        // console.log(res.data.records)
        this.total = parseInt(res.data.total)
      })
      //console.log(this.teacherId)
    },


    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    reset() {
      this.name = ""
      this.courseName = ""
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
    showClazz(index){
      console.log(this.tableData[index])
      window.sessionStorage.setItem('data',JSON.stringify(this.tableData[index]) )
      this.$router.push('/showClazz')
    },
    // 班级计算达成度
    CalculateData(index){
      console.log(this.tableData[index])
      this.request.get("/clazz_course_goal_score/calculate", {
        params: {
          clazzId : this.tableData[index].clazzId,
          courseId : this.tableData[index].courseId
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
