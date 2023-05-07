<template>
  <div>
    <div style="margin: 10px 0">
      <el-select clearable placeholder="请选择课程" class="ml-5" v-model="courseId">
        <el-option v-for="item in courses" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>
      <el-select clearable placeholder="请选择教师" class="ml-5" v-model="teacherId">
        <el-option v-for="item in users" :key="item.id" :label="item.nickname" :value="item.id"></el-option>
      </el-select>
      <el-select clearable placeholder="请选择班级" class="ml-5" v-model="clazzId">
        <el-option v-for="item in clazzs" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-courseId="'headerBg'" @selection-change="handleSelectionChange">
      <el-table-column prop="course" label="课程" ></el-table-column>
      <el-table-column prop="teacher" label="教师"></el-table-column>
      <el-table-column prop="clazz" label="班级" ></el-table-column>
      <el-table-column prop="status" label="审核状态" >
        <template slot-scope="scope">
          <el-tag type="primary" v-if="scope.row.status === 0">待审核</el-tag>
          <el-tag type="info" v-if="scope.row.status === 1">审核通过</el-tag>
          <el-tag type="warning" v-if="scope.row.status === 2">审核未通过</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="150"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">查看课程参数 <i class="el-icon-edit"></i></el-button>
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

    <el-dialog title="课程参数信息审核" :visible.sync="dialogFormVisible" width="70%" >
      <el-table :data="form" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
        <el-table-column prop="teacher" label="教师"></el-table-column>
        <el-table-column prop="course" label="课程"></el-table-column>
        <el-table-column prop="pointName" label="指标点"></el-table-column>
        <el-table-column prop="nameCourseGoal" label="课程目标名称"></el-table-column>
        <el-table-column prop="weightCourseGoal" label="课程目标参数"></el-table-column>
        <el-table-column prop="nameCheckOutline" label="大纲考核项名称"></el-table-column>
        <el-table-column prop="weightCheckOutline" label="大纲考核项参数"></el-table-column>
        <el-table-column prop="nameCheckTeacher" label="教师考核项名称"></el-table-column>
        <el-table-column prop="weightCheckTeacher" label="教师考核项参数"></el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="warning" @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="danger" @click="reviewFailed">审核未通过</el-button>
        <el-button type="primary" @click="reviewApproved">审核通过</el-button>
      </div>
    </el-dialog>
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
      name:"",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      courses:[],
      users:[],
      clazzs:[],
      courseId:undefined,
      teacherId:undefined,
      clazzId:undefined,
      rowCourseSchedule: {},
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/course_schedule/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          courseId: this.courseId,
          teacherId: this.teacherId,
          clazzId: this.clazzId,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = parseInt(res.data.total)
      })
      this.request.get("/course").then(res => {
        this.courses = res.data
      })
      this.request.get("/user").then(res => {
        this.users = res.data
      })
      this.request.get("/clazz").then(res => {
        this.clazzs = res.data
      })
    },
    // 审核未通过
    reviewFailed(){
      this.rowCourseSchedule.status = 2
      this.save()
      this.load()
    },
    // 审核通过
    reviewApproved(){
      this.rowCourseSchedule.status = 1
      this.save()
      this.load()
    },
    save() {
      this.request.post("/course_schedule/status", this.rowCourseSchedule).then(res => {
        if (res.code === '200') {
          this.dialogFormVisible = false
          this.$message.success("审核成功")
          this.load()
        } else {
          this.$message.error("审核失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.rowCourseSchedule = JSON.parse(JSON.stringify(row))
      // 教师id和课程id，获取 该课程参数
      this.request.get("/course_weights/findAll/"+row.teacherId+"/"+row.courseId).then(res => {
        this.form = res.data
      })
      this.dialogFormVisible = true
    },
    del(id) {
      this.request.delete("/course_schedule/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
      this.request.post("/course_schedule/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    reset() {
      this.courseId = ""
      this.teacherId = ""
      this.clazzId = ""
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
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>
