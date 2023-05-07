<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入课程名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-select clearable placeholder="请选择课程类型" class="ml-5" v-model="courseType">
        <el-option v-for="item in courseTypes" :key="item" :label="item" :value="item"></el-option>
      </el-select>
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
      <el-upload :action="'http://' + serverIp + ':9090/course/import'" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
<!--        <el-select clearable v-model="majorId" placeholder="请选择专业">-->
<!--          &lt;!&ndash;label:显示的名字  value:存的值&ndash;&gt;-->
<!--          <el-option v-for="item in majors" :key="item.id" :label="item.name" :value="item.id"></el-option>-->
<!--        </el-select>-->
        <el-button type="primary" @click="saveMajorId" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" @click="exp" class="ml-5">导出 <i class="el-icon-top"></i></el-button>
    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="name" label="名称" width="150"></el-table-column>
      <el-table-column prop="type" label="类型" width="150"></el-table-column>
      <el-table-column prop="properties" label="性质" width="120"></el-table-column>
      <el-table-column prop="major" label="专业"></el-table-column>
      <el-table-column prop="term" label="课程学期"></el-table-column>
      <el-table-column prop="credit" label="学分"></el-table-column>
      <el-table-column prop="theoryHour" label="理论学时"></el-table-column>
      <el-table-column prop="expHour" label="实践学时"></el-table-column>
      <el-table-column prop="totalHour" label="总学时"></el-table-column>
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

    <el-dialog title="指标点信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="专业">
          <el-select clearable v-model="form.majorId" placeholder="请选择专业">
            <!--label:显示的名字  value:存的值-->
            <el-option v-for="item in majors" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类型">
          <el-input v-model="form.type" autocomplete="off"></el-input>
        </el-form-item>
      <el-form-item label="性质">
          <el-input v-model="form.properties" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="开课学期">
          <el-input v-model="form.term" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学分">
          <el-input v-model="form.credit" autocomplete="off"></el-input>
        </el-form-item>
      <el-form-item label="理论学时">
          <el-input v-model="form.theoryHour" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="实践学时">
          <el-input v-model="form.expHour" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="总学时">
          <el-input v-model="form.totalHour" autocomplete="off"></el-input>
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
  name: "Course",
  data() {
    return {
      serverIp: serverIp,
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      majors: [],
      majorId: undefined,
      courseType: "",
      courseTypes:[],
    }
  },
  created() {
    this.load()

  },
  methods: {
    load() {
      this.request.get("/course/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          courseType: this.courseType,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = parseInt(res.data.total)
      })
      this.request.get("/major").then(res => {
        this.majors = res.data
      })
      this.request.get("/course/course_types").then(res => {
        this.courseTypes = res.data
      })
    },
    save() {
      // console.log(this.form)
      this.request.post("/course", this.form).then(res => {
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
    saveMajorId(){
      this.request.post("/course/saveMajorId/"+this.majorId).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
        }else{
          this.$message.error("保存失败")
        }
      })
    },
    del(id){
      this.request.delete("/course/" + id).then(res => {
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
      this.request.post("/course/del/batch", ids).then(res => {
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
      this.courseType = ""
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
    exp() {
      window.open(`http://${serverIp}:9090/course/export`)
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
