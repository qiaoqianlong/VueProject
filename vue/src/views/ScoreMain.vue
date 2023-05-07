<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入学生姓名" suffix-icon="el-icon-s-custom" v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="请输入课程名" suffix-icon="el-icon-message" class="ml-5" v-model="courseName"></el-input>
      <el-input style="width: 200px" placeholder="请输入教师考核项名称" suffix-icon="el-icon-position" class="ml-5" v-model="teacherCheckName"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
      <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想'
                     icon="el-icon-info" icon-color="red" title="您确定批量删除这些数据吗？"
                     @confirm="delBatch">
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>
    <!--成绩信息 表格-->
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
<!--      <el-table-column prop="id" label="ID" width="80"></el-table-column>-->
      <el-table-column prop="studentId" label="学生学号"></el-table-column>
      <el-table-column prop="studentName" label="学生姓名"></el-table-column>
      <el-table-column prop="courseName" label="课程"></el-table-column>
      <el-table-column prop="checkTeacherName" label="教师考核项名称"></el-table-column>
      <el-table-column prop="score" label="成绩" width="80"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
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
    <!--弹窗-->
    <el-dialog title="成绩信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="学号">
          <el-input v-model="form.studentId" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form.studentName" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="成绩">
          <el-input v-model="form.score" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
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
      //studentName: "",
      courseName: "",
      teacherCheckName: "",
      // name: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      courseWeightsTableData: [],
      teacher_courses: [],
      courseId: undefined,
      teacherId:undefined,
      checkTeacherId:undefined,
      courseWeightsObj:{},
      rowCourseWeights: {},
      scoreRowData: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    // {id: 1, teacherId: 1, pointId: 26, courseId: 1, courseGoalId: 1}
    Object.assign(this.scoreRowData,JSON.parse(window.sessionStorage.getItem('scoreRowData')))
    this.teacherId = this.user.id
    this.courseId = this.scoreRowData.courseId
    this.checkTeacherId = this.scoreRowData.checkTeacherId
    this.load()
  },
  methods: {
    load() {
      this.request.get("/score/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          courseName: this.courseName,
          teacherCheckName: this.teacherCheckName,
          teacherId: this.teacherId,
          courseId:  this.courseId,
          checkTeacherId:  this.checkTeacherId,
        }
      }).then(res => {
        this.tableData = res.data.records
        // console.log(res.data.records)
        this.total = parseInt(res.data.total)
      })
    },
    save() {
      this.request.post("/score", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      console.log(this.form)
      this.dialogFormVisible = true
    },
    del(id) {
      this.request.delete("/score/" + id).then(res => {
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
      this.request.post("/score/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    reset() {
      this.name = ""
      this.courseName = ""
      this.teacherCheckName = ""
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
    }
  }
}
</script>
<style>
.headerBg {
  background: #eee!important;
}
</style>
