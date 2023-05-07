<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-form-item label="单选" prop="file1">
        <el-radio-group v-model="formData.file1" size="medium">
          <el-radio v-for="(item, index) in file1Options" :key="index" :label="item.value"
            :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="多选" prop="field114">
        <el-checkbox-group v-model="formData.field114" size="medium">
          <el-checkbox v-for="(item, index) in field114Options" :key="index" :label="item.value"
            :disabled="item.disabled">{{item.label}}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="填空" prop="field115">
        <el-input v-model="formData.field115" placeholder="请输入填空" clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>
      <el-form-item label="选择" prop="field116">
        <el-input v-model="formData.field116" type="textarea" placeholder="请输入选择"
          :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  name: "Form",
  components: {},
  props: [],
  data() {
    return {
      formData: {
        file1: undefined,
        field114: [],
        field115: undefined,
        field116: undefined,
      },
      rules: {
        file1: [{
          required: true,
          message: '单选不能为空',
          trigger: 'change'
        }],
        field114: [{
          required: true,
          type: 'array',
          message: '请至少选择一个多选',
          trigger: 'change'
        }],
        field115: [{
          required: true,
          message: '请输入填空',
          trigger: 'blur'
        }],
        field116: [{
          required: true,
          message: '请输入选择',
          trigger: 'blur'
        }],
      },
      file1Options: [{
        "label": "选项一",
        "value": 1
      }, {
        "label": "选项二",
        "value": 2
      }],
      field114Options: [{
        "label": "选项一",
        "value": 1
      }, {
        "label": "选项二",
        "value": 2
      }],
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
        console.log(JSON.stringify(this.formData))
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}

</script>
<style>
</style>
