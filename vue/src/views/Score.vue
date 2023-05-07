<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入课程名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <!--      <el-input style="width: 200px"  placeholder="请输入学期" suffix-icon="el-icon-search" class="ml-5"  v-model="term"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <!--课程参数 表格-->
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'">
      <el-table-column prop="teacher" label="教师"></el-table-column>
      <el-table-column prop="course" label="课程"></el-table-column>
      <el-table-column prop="pointName" label="指标点"></el-table-column>
      <el-table-column prop="nameCourseGoal" label="课程目标名称"></el-table-column>
      <el-table-column prop="weightCourseGoal" label="课程目标参数"></el-table-column>
      <el-table-column prop="nameCheckOutline" label="大纲考核项名称"></el-table-column>
      <el-table-column prop="weightCheckOutline" label="大纲考核项参数"></el-table-column>
      <el-table-column prop="nameCheckTeacher" label="教师考核项名称"></el-table-column>
      <el-table-column prop="weightCheckTeacher" label="教师考核项参数"></el-table-column>
      <el-table-column label="操作"  width="350" align="center">
        <template slot-scope="scope">
          <el-upload :action="'http://' + serverIp + ':9090/score/import'"
                     :show-file-list="false" accept="xlsx"
                     :on-success="handleExcelImportSuccess" style="display: inline-block">
            <el-button type="primary" @click="upload(scope.row)" class="ml-5">导入成绩 <i class="el-icon-bottom"></i></el-button>
          </el-upload>
          <el-button type="warning" @click="exp(scope.row)" class="ml-5">导出成绩 <i class="el-icon-top"></i></el-button>
          <el-button type="danger" @click="showScoreMain(scope.row)" class="ml-5">查看成绩 <i class="el-icon-top"></i></el-button>
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
  name: "Score",
  data() {
    return {
      serverIp: serverIp,
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name:"",
      form: {},
      teacherId:undefined,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    this.teacherId = this.user.id
  },
  mounted() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/course_weights/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          teacherId: this.teacherId,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = parseInt(res.data.total)
      })
    },
    reset() {
      this.name = ""
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
    exp(row) {
      this.rowCourseWeights = JSON.parse(JSON.stringify(row))
      this.request.post("/score/export/params/", this.rowCourseWeights).then(res => {
        if (res.code === '200') {
          this.$message.success("参数保存成功")
          window.open(`http://${serverIp}:9090/score/export`)
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    upload(row) {
      this.rowCourseWeights = JSON.parse(JSON.stringify(row))
      this.request.post("/score/export/params/", this.rowCourseWeights).then(res => {
        if (res.code === '200') {
          this.$message.success("参数保存成功")
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    },
    showScoreMain(row){
      console.log(row)
      window.sessionStorage.setItem('scoreRowData',JSON.stringify(row))
      this.$router.push('/score_main')
    },
  }
}
</script>


<style>
.headerBg {
  background: #eee!important;
}
</style>
