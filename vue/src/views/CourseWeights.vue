<template>
  <div>
    <el-table :data="tableData" border stripe :header-cell-class-courseId="'headerBg'">
      <el-table-column prop="course" label="课程" ></el-table-column>
      <el-table-column prop="clazz" label="班级" ></el-table-column>
      <el-table-column prop="teacher" label="教师"></el-table-column>
      <el-table-column prop="status" label="审核状态" v-if="" >
        <template slot-scope="scope">
          <el-tag type="primary" v-if="scope.row.status === 0">待审核</el-tag>
          <el-tag type="info" v-if="scope.row.status === 1">审核通过</el-tag>
          <el-tag type="warning" v-if="scope.row.status === 2">审核未通过</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="showCourseWeightsMain(scope.$index)" class="ml-5">配置参数 <i class="el-icon-circle-plus-outline" ></i></el-button>
        </template>
      </el-table-column>
    </el-table>
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
  name: "CourseSchedule",
  data() {
    return {
      serverIp: serverIp,
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      form: {},
      teacherId:undefined,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      // 课前参数配置中 课程安排计划
      this.request.get("/course_schedule/page/byTeacher", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          teacherId: this.user.id
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = parseInt(res.data.total)
      })
    },
    showCourseWeightsMain(index){
      console.log(this.tableData[index])
      window.sessionStorage.setItem('course_schedule_data',JSON.stringify(this.tableData[index]) )
      this.$router.push('/course_weights_main')
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
  }
}
</script>


<style>
.headerBg {
  background: #eee!important;
}
</style>
