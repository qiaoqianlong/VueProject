<template>
  <div>
    <!--选择配置课程-->
    <div style="margin: 10px 0">
      <span style="margin: 0 10px 0 0 ">
        选择要查询的学生
      </span>
    </div>


    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入学生姓名" suffix-icon="el-icon-s-custom" v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="请输入班级名称" suffix-icon="el-icon-message" class="ml-5" v-model="clazzName"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>


    <!--成绩信息 表格-->
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="studentId" label="学生学号"></el-table-column>
      <el-table-column prop="studentName" label="学生姓名"></el-table-column>
      <el-table-column prop="clazzName" label="所属班级"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="showStudent(scope.$index)">预览 <i class="el-icon-zoom-in"></i></el-button>
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
      clazzName: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      courseWeightsTableData: [],
      teacher_courses: [],
      courseWeightsObj:{},
    }
  },
  created() {
    this.load()
  },
  methods: {

    load() {
      this.request.get("/student/studentInfo", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          clazzName: this.clazzName,
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
    showStudent(index){
      console.log(this.tableData[index])
      window.sessionStorage.setItem('data',JSON.stringify(this.tableData[index]) )
      this.$router.push('/showStudentGradRequireScore')
    }
  }
}
</script>


<style>
.headerBg {
  background: #eee!important;
}
</style>
