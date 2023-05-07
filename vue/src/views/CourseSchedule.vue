<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入课程名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-select clearable placeholder="请选择课程" class="ml-5" v-model="courseId">
        <el-option v-for="item in courses" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>
      <el-select clearable placeholder="请选择教师" class="ml-5" v-model="teacherId">
        <el-option v-for="item in users" :key="item.id" :label="item.nickname" :value="item.id"></el-option>
      </el-select>
      <el-select clearable placeholder="请选择班级" class="ml-5" v-model="clazzId">
        <el-option v-for="item in clazzs" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>
<!--      <el-input style="width: 200px"  placeholder="请输入学期" suffix-icon="el-icon-search" class="ml-5"  v-model="term"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch">
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-courseId="'headerBg'" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="course" label="课程" ></el-table-column>
      <el-table-column prop="teacher" label="教师"></el-table-column>
      <el-table-column prop="clazz" label="班级" ></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="150"></el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
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
    <el-dialog title="学期课程计划安排" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="课程">
          <el-select clearable v-model="form.courseId" placeholder="请选择专业">
            <!--label:显示的名字  value:存的值-->
            <el-option v-for="item in courses" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="教师">
          <el-select clearable v-model="form.teacherId" placeholder="请选择专业">
            <!--label:显示的名字  value:存的值-->
            <el-option v-for="item in users" :key="item.id" :label="item.nickname" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="班级">
          <el-select clearable v-model="form.clazzId" placeholder="请选择专业">
            <!--label:显示的名字  value:存的值-->
            <el-option v-for="item in clazzs" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
<!--       <el-form-item label="审核状态">-->
<!--          <el-input v-model="form.status" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="warning" @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
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
      prop:"",
      order:"",
      name:"",
      // courseId: 0,
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      courses:[],
      users:[],
      clazzs:[],
      courseId:undefined,
      teacherId:undefined,
      clazzId:undefined
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
          // courseId: this.courseId,
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
    save() {
      this.request.post("/course_schedule", this.form).then(res => {
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
      this.name = ""
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
