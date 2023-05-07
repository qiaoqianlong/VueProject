<template>
  <!--根据教师id，搜索教师的所有课程
          根据课程id，添加并显示所有的课程目标
          根据课程目标id,
            根据大纲设置 添加并显示大纲考核项
            根据教师自己 添加并显示教师考核项
          建立教师考核项和指标点之间的关系-->
  <div>
    <!--选择配置课程-->
    <div style="margin: 10px 0">
      <span style="margin: 0 10px 0 0 ">
        课程信息
      </span><br/>
      配置课程：{{this.courseScheduleData.course}}<br/>
      授课教师：{{this.courseScheduleData.teacher}}<br/>
      授课班级：{{this.courseScheduleData.clazz}}<br/>
    </div>

    <!--设置课程目标参数 -->
    <div style="margin: 10px 0">
      <div style="margin: 10px 0">
        <span style="margin: 0 10px 0 0 ">
          设置课程目标参数
        </span>
        <el-input style="width: 200px" placeholder="请输入课程目标名称" v-model="courseGoal.name"></el-input>
        <el-input style="width: 200px" placeholder="请输入课程目标参数" v-model="courseGoal.weight" class="ml-5"></el-input>
        <el-button type="primary" @click="addCourseGoal" class="ml-5">新增 <i class="el-icon-circle-plus-outline" ></i></el-button>
      </div>
      <el-table :data="courseGoalsTableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
        <!--        <el-table-column type="selection" width="55"></el-table-column>-->
        <el-table-column prop="id" label="ID" width="80"></el-table-column>
        <el-table-column prop="name" label="课程目标名称"></el-table-column>
        <el-table-column prop="weight" label="课程目标参数"></el-table-column>
        <el-table-column label="操作"  width="200" align="center">
          <template slot-scope="scope">
            <el-button type="success" @click="handleCourseGoalEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
            <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想'
                           icon="el-icon-info" icon-color="red" title="您确定删除吗？"
                           @confirm="delCourseGoal(scope.row.id)">
              <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--课程目标参数配置 弹窗-->
    <el-dialog title="课程目标参数配置" :visible.sync="dialogFormCourseGoalVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="名称">
          <el-input v-model="courseGoalForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="参数">
          <el-input v-model="courseGoalForm.weight" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormCourseGoalVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveCourseGoal">确 定</el-button>
      </div>
    </el-dialog>

    <!--设置大纲考核项参数-->
    <div style="margin: 10px 0">
      <div style="margin: 10px 0">
        <span style="margin: 0 10px 0 0 ">
          设置大纲考核项参数
        </span>
        <el-input style="width: 200px" placeholder="请输入大纲考核项名称" v-model="checkOutline.name"></el-input>
        <el-input style="width: 200px" placeholder="请输入大纲考核项参数" v-model="checkOutline.weight" class="ml-5"></el-input>
        <el-button type="primary" @click="addCheckOutline" class="ml-5">新增 <i class="el-icon-circle-plus-outline" ></i></el-button>
      </div>
      <el-table :data="checkOutlineTableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
        <!--        <el-table-column type="selection" width="55"></el-table-column>-->
        <el-table-column prop="id" label="ID" width="80"></el-table-column>
        <el-table-column prop="name"   label="大纲考核项名称"></el-table-column>
        <el-table-column prop="weight" label="大纲考核项参数"></el-table-column>
        <el-table-column label="操作"  width="200" align="center">
          <template slot-scope="scope">
            <el-button type="success" @click="handleCheckOutlineEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
            <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想'
                           icon="el-icon-info" icon-color="red" title="您确定删除吗？"
                           @confirm="delCheckOutline(scope.row.id)">
              <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--大纲考核项参数配置 弹窗-->
    <el-dialog title="大纲考核项参数配置" :visible.sync="dialogFormCheckOutlineVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="名称">
          <el-input v-model="checkOutlineForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="参数">
          <el-input v-model="checkOutlineForm.weight" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormCheckOutlineVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveCheckOutline">确 定</el-button>
      </div>
    </el-dialog>

    <!--设置教师考核项参数-->
    <div style="margin: 10px 0">
      <div style="margin: 10px 0">
        <span style="margin: 0 10px 0 0 ">
          设置教师考核项参数
        </span>
        <el-input style="width: 200px" placeholder="请输入教师考核项名称" v-model="checkTeacher.name"></el-input>
        <el-input style="width: 200px" placeholder="请输入教师考核项参数" v-model="checkTeacher.weight" class="ml-5"></el-input>
        <el-button type="primary" @click="addCheckTeacher" class="ml-5">新增 <i class="el-icon-circle-plus-outline" ></i></el-button>
      </div>
      <el-table :data="checkTeacherTableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
        <!--        <el-table-column type="selection" width="55"></el-table-column>-->
        <el-table-column prop="id" label="ID" width="80"></el-table-column>
        <el-table-column prop="name"   label="教师考核项名称"></el-table-column>
        <el-table-column prop="weight" label="教师考核项参数"></el-table-column>
        <el-table-column label="操作"  width="200" align="center">
          <template slot-scope="scope">
            <el-button type="success" @click="handleCheckTeacherEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
            <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想'
                           icon="el-icon-info" icon-color="red" title="您确定删除吗？"
                           @confirm="delCheckTeacher(scope.row.id)">
              <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--教师考核项参数配置 弹窗-->
    <el-dialog title="教师考核项参数配置" :visible.sync="dialogFormCheckTeacherVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="名称">
          <el-input v-model="checkTeacherForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="参数">
          <el-input v-model="checkTeacherForm.weight" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormCheckTeacherVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveCheckTeacher">确 定</el-button>
      </div>
    </el-dialog>


    <!--设置课程参数-->
    <div style="margin: 10px 0">
        <span style="margin: 0 10px 0 0 ">
          配置课程参数
        </span>
      <el-select clearable placeholder="请选择指标点" v-model="courseWeightsForm.pointId" >
        <el-option v-for="item in pointsData" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>
      <el-select clearable placeholder="请选择课程目标" v-model="courseWeightsForm.courseGoalId" >
        <el-option v-for="item in courseGoalsTableData" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>
      <el-select clearable placeholder="请选择大纲考核项" v-model="courseWeightsForm.checkOutlineId" >
        <el-option v-for="item in checkOutlineTableData" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>
      <el-select clearable placeholder="请选择教师考核项" v-model="courseWeightsForm.checkTeacherId" >
        <el-option v-for="item in checkTeacherTableData" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>
      <el-button type="success" @click="saveOrUpdateCourseWeights" class="ml-5">保存 <i class="el-icon-circle-plus-outline" ></i></el-button>
      <el-button type="primary" @click="saveOrUpdateCourseWeights" class="ml-5">提交审核 <i class="el-icon-circle-plus-outline" ></i></el-button>
    </div>

    <!--课程参数 表格-->
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <!--      <el-table-column type="selection" width="55"></el-table-column>-->
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="teacher" label="教师"></el-table-column>
      <el-table-column prop="course" label="课程"></el-table-column>
      <el-table-column prop="pointName" label="指标点"></el-table-column>
      <el-table-column prop="nameCourseGoal" label="课程目标名称"></el-table-column>
      <el-table-column prop="weightCourseGoal" label="课程目标参数"></el-table-column>
      <el-table-column prop="nameCheckOutline" label="大纲考核项名称"></el-table-column>
      <el-table-column prop="weightCheckOutline" label="大纲考核项参数"></el-table-column>
      <el-table-column prop="nameCheckTeacher" label="教师考核项名称"></el-table-column>
      <el-table-column prop="weightCheckTeacher" label="教师考核项参数"></el-table-column>
      <!--      <el-table-column prop="status" label="审核状态"></el-table-column>-->
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

    <!--    弹窗-->
    <el-dialog title="课程参数配置" :visible.sync="dialogFormVisible" width="40%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="课程目标">
          <el-select clearable placeholder="请选择课程目标" v-model="courseWeightsForm.courseGoalId" >
            <el-option v-for="item in courseGoalsTableData" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="大纲考核项">
          <el-select clearable placeholder="请选择大纲考核项" v-model="courseWeightsForm.checkOutlineId" >
            <el-option v-for="item in checkOutlineTableData" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="教师考核项">
          <el-select clearable placeholder="请选择教师考核项" v-model="courseWeightsForm.checkTeacherId" >
            <el-option v-for="item in checkTeacherTableData" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdateCourseWeights">确 定</el-button>
      </div>
    </el-dialog>
    <div>
      <el-row style="margin: 20px 0" justify="center" type="flex">
        <el-button type="primary" icon="el-icon-arrow-left" @click="back">返回</el-button>
      </el-row>
    </div>
  </div>
</template>

<script>
import {serverIp} from "../../public/config";

export default {
  name: "CourseWeights",
  data() {
    return {
      serverIp: serverIp,
      tableData: [],
      courseGoalsTableData: [],
      checkOutlineTableData: [],
      checkTeacherTableData: [],
      pointsData: [],
      form: {},    // 课程参数 弹窗对象 添加编辑弹窗对象 通过this.form = JSON.parse(JSON.stringify(row)),通过table表格中 handleEdit(scope.row)获取
      courseGoalForm: {},
      checkOutlineForm: {},
      checkTeacherForm: {},
      courseGoal: {},
      checkOutline: {},
      checkTeacher: {},
      courseWeightsForm: {},
      courseId: undefined,
      dialogFormVisible: false,
      dialogFormCourseGoalVisible: false,
      dialogFormCheckOutlineVisible: false,
      dialogFormCheckTeacherVisible: false,
      multipleSelection: [],
      course_weights: [],
      courseStatus: undefined,
      courseScheduleData:{},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    // console.log(JSON.parse(window.sessionStorage.getItem('course_schedule_data')))
    // {id: 1, courseId: 1, teacherId: 1, clazzId: 1, status: 0}
    Object.assign(this.courseScheduleData,JSON.parse(window.sessionStorage.getItem('course_schedule_data')))

  },
  mounted() {  // 页面元素渲染之后再触发
    this.load(this.courseScheduleData.courseId);
  },
  methods: {
    // 选择课程，将其所有信息加载出来，包括课程目标、大纲考核项，教师考核项
    load(courseId){
      this.courseId = courseId
      this.loadCourseGoal()
      this.loadCheckOutline()
      this.loadCheckTeacher()
      this.loadWeights()
      this.loadPoints()
    },
    // 获取课程配置参数
    loadWeights() {
      console.log(this.user.id)
      console.log(this.courseId)
      this.request.get("/course_weights/findAll/"+this.user.id +"/"+this.courseId).then(res => {
        this.tableData = res.data
        console.log(res.data)
      })
    },
    //根据教师id，获取 其教授的所有课程计划安排.包括课程参数审核状态
    loadTeacherCourses(){
      this.request.get("/course_schedule/page/"+ this.user.id).then(res => {
        this.course_weights = res.data
      })
    },
    // 根据课程id，获取 课程目标
    loadCourseGoal(){
      this.request.get("/course_goal_weight/findAll/"+this.user.id +"/"+this.courseId).then(res => {
        this.courseGoalsTableData = res.data
        //console.log(this.courseGoalsTableData)
      })
    },
    // 根据课程id，获取 大纲考核项
    loadCheckOutline(){
      this.request.get("/check_outline_weight/findAll/"+this.user.id +"/"+this.courseId).then(res => {
        this.checkOutlineTableData = res.data
        // console.log(this.checkOutlineTableData)
      })
    },
    // 根据课程id，获取 教师考核项
    loadCheckTeacher(){
      this.request.get("/check_teacher_weight/findAll/"+this.user.id +"/"+this.courseId).then(res => {
        this.checkTeacherTableData = res.data
        // console.log(this.checkOutlineTableData)
      })
    },
    // 获取所有指标点
    loadPoints() {
      this.request.get("/point").then(res => {
        this.pointsData = res.data
        // console.log(res.data)
      })
    },
    back(){
      this.$router.push('/course_weights')
    },
    // 添加或编辑 对话框中添加确认
    save() {
      console.log(this.form)
      this.request.post("/course_weights", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.loadWeights()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    // 添加 配置课程参数
    saveOrUpdateCourseWeights(){
      this.courseWeightsForm.teacherId = this.user.id
      this.courseWeightsForm.courseId = this.courseId
      console.log(this.courseWeightsForm)
      this.request.post("/course_weights", this.courseWeightsForm).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.loadWeights()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    // // 添加 打开对话框
    // handleAdd() {
    //   this.dialogFormVisible = true
    //   this.courseWeightsForm = {}
    // },
    // 编辑 打开对话框
    handleEdit(row) {
      this.courseWeightsForm = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    // 删除 根据form.row.id
    del(id) {
      this.request.delete("/course_weights/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.loadWeights()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    // 多选 对象数组
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    // 批量删除
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
      this.request.post("/course_weights/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功")
          this.loadWeights()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    // 重置
    reset() {
      this.name = ""
      this.loadWeights()
    },
    // 分页
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.loadWeights()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.loadWeights()
    },
    // 添加 课程目标
    addCourseGoal(){
      this.courseGoal.courseId = this.courseId
      this.courseGoal.teacherId = this.user.id
      this.request.post("/course_goal_weight",this.courseGoal).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          // this.courseGoal = []
          this.loadCourseGoal()
          // this.loadTeacherCourses()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    // 编辑 打开对话框 课程目标
    handleCourseGoalEdit(row) {
      this.courseGoalForm = JSON.parse(JSON.stringify(row))
      this.dialogFormCourseGoalVisible = true
    },
    // 修改 课程目标
    saveCourseGoal(){
      this.request.post("/course_goal_weight",this.courseGoalForm).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormCourseGoalVisible = false
          this.loadCourseGoal()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    // 删除 课程目标
    delCourseGoal(id){
      this.request.delete("/course_goal_weight/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.loadCourseGoal()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    // 添加 大纲考核项
    addCheckOutline(){
      this.checkOutline.courseId = this.courseId
      this.checkOutline.teacherId = this.user.id
      this.request.post("/check_outline_weight",this.checkOutline).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          // this.checkOutline = []
          this.loadCheckOutline()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    // 编辑 打开对话框  大纲考核项
    handleCheckOutlineEdit(row) {
      this.checkOutlineForm = JSON.parse(JSON.stringify(row))
      this.dialogFormCheckOutlineVisible = true
    },
    // 修改课程目标
    saveCheckOutline(){
      this.request.post("/check_outline_weight",this.checkOutlineForm).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormCheckOutlineVisible = false
          this.loadCheckOutline()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    // 删除 大纲考核项
    delCheckOutline(id){
      this.request.delete("/check_outline_weight/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.loadCheckOutline()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    // 添加 教师考核项
    addCheckTeacher(){
      this.checkTeacher.courseId = this.courseId
      this.checkTeacher.teacherId = this.user.id
      this.request.post("/check_teacher_weight",this.checkTeacher).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          // this.checkTeacher = []
          this.loadCheckTeacher()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    // 编辑 打开对话框  教师考核项
    handleCheckTeacherEdit(row) {
      this.checkTeacherForm = JSON.parse(JSON.stringify(row))
      this.dialogFormCheckTeacherVisible = true
    },
    // 修改 教师考核项
    saveCheckTeacher(){
      this.request.post("/check_teacher_weight",this.checkTeacherForm).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormCheckTeacherVisible = false
          this.loadCheckTeacher()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    // 删除 教师考核项
    delCheckTeacher(id){
      this.request.delete("/check_teacher_weight/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.loadCheckTeacher()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
  }
}
</script>


<style>
.headerBg {
  background: #eee!important;
}
</style>
