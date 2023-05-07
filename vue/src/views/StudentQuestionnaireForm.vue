<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <h3 style="margin-bottom: 10px">第一部分 毕业生基本情况</h3>
      <el-form-item label="1. 您是软件工程专业哪一级学生（入学年份）？ [单选题] *" prop="grade">
        <el-radio-group v-model="formData.grade" size="medium">
          <el-radio v-for="(item, index) in gradeOptions" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="2. 您的姓名" prop="name">
        <el-input v-model="formData.name" placeholder="请输入姓名" clearable :style="{width: '30%'}">
        </el-input>
      </el-form-item>
      <el-form-item label="3. 请您提供联系电话： [填空题] *" prop="phone">
        <el-input v-model="formData.phone" placeholder="请输入联系电话" clearable :style="{width: '30%'}">
        </el-input>
      </el-form-item>

      <h3 style="margin-bottom: 10px">第二部分 就业基本情况</h3>
      <el-form-item label="1. 您的工作或学习单位全称/具体部门是 [填空题] *" prop="title1">
        <el-input v-model="formData.title1" placeholder="请输入工作单位" clearable :style="{width: '30%'}">
        </el-input>
      </el-form-item>
      <el-form-item label="2. 您工作的单位所在城市 [填空题] *" prop="title2">
        <el-input v-model="formData.title2" placeholder="请输入工作城市" clearable :style="{width: '30%'}">
        </el-input>
      </el-form-item>
      <el-form-item label="3. 您的单位属于哪种性质？ [单选题] *" prop="title3">
        <el-radio-group v-model="formData.title3" size="medium">
          <el-radio v-for="(item, index) in title3Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="4. 您的目前从事的主要工作是 [单选题] *" prop="title4">
        <el-radio-group v-model="formData.title4" size="medium">
          <el-radio v-for="(item, index) in title4Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="5. 您目前从事的领域是 [单选题] *" prop="title5">
        <el-radio-group v-model="formData.title5" size="medium">
          <el-radio v-for="(item, index) in title5Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="6. 您目前的岗位是 [单选题] *" prop="title6">
        <el-radio-group v-model="formData.title6" size="medium">
          <el-radio v-for="(item, index) in title6Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="7. 您目前的职位是/相当于是 [单选题] *" prop="title7">
        <el-radio-group v-model="formData.title7" size="medium">
          <el-radio v-for="(item, index) in title7Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="8. 您目前的职称是/相当于是 [单选题] *" prop="title8">
        <el-radio-group v-model="formData.title8" size="medium">
          <el-radio v-for="(item, index) in title8Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="9. 您能在单位工作薪酬大概在怎样的位置？(税后月薪) [单选题] *" prop="title9">
        <el-radio-group v-model="formData.title9" size="medium">
          <el-radio v-for="(item, index) in title9Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>

      <h3 style="margin-bottom: 10px">第三部分 请评价您目前所具备的能力和素养</h3>
      <el-form-item class="el-form-item-label" prop="title10"
                    label="10. 您已具有良好的人文素养、职业道德和社会责任感，能够在工程实践中自觉遵守法律法规和职业道德规范。 [单选题]" >
        <el-radio-group v-model="formData.title10" size="medium">
          <el-radio v-for="(item, index) in titleMatrix10_17Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item class="el-form-item-label" prop="title11"
                    label="11.您已能够运用专业知识和技术，对软件工程领域的复杂工程问题设计解决方案，并予以成功实现，达到工程师水平； [单选题] *" >
        <el-radio-group v-model="formData.title11" size="medium">
          <el-radio v-for="(item, index) in titleMatrix10_17Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item class="el-form-item-label" prop="title13"
                    label="13.您已能够在企业与社会环境下，承担软件工程领域的科学研究、产品设计、系统开发、应用服务等方面的技术和管理工作 [单选题] *" >
        <el-radio-group v-model="formData.title13" size="medium">
          <el-radio v-for="(item, index) in titleMatrix10_17Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item class="el-form-item-label" prop="title14"
                    label="14.您已适应独立和团队工作环境，具备与同事和公众沟通交流的能力，能够在软件工程项目中完成协调、组织和管理工作。 [单选题] *" >
        <el-radio-group v-model="formData.title14" size="medium">
          <el-radio v-for="(item, index) in titleMatrix10_17Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item class="el-form-item-label" prop="title15"
                    label="15.您已能够跟踪专业领域的新技术，通过多种渠道更新知识，具有终身学习能力，适应国内外形势和环境的变化，实现能力和技术水平的提升。 [单选题] *" >
        <el-radio-group v-model="formData.title15" size="medium">
          <el-radio v-for="(item, index) in titleMatrix10_17Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item class="el-form-item-label" prop="title16"
                    label="16.您对本专业的培养目标与社会需求的契合度评价。 [单选题] *" >
        <el-radio-group v-model="formData.title16" size="medium">
          <el-radio v-for="(item, index) in titleMatrix10_17Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item class="el-form-item-label" prop="title17"
                    label="17.企业重视本专业毕业生的程度评价。 [单选题] *" >
        <el-radio-group v-model="formData.title17" size="medium">
          <el-radio v-for="(item, index) in titleMatrix10_17Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-row>


         <el-form-item class="el-form-item-label" prop="title18"
               label="18. 请你对目前所您具有的下面几个方面能力进行评价[矩阵单选题] *" >
     <el-form-item label="理论基础" prop="title18_1">
       <el-radio-group v-model="formData.title18_1" size="medium">
         <el-radio v-for="(item, index) in titleMatrix18Options" :key="index" :label="item.value"
                   :disabled="item.disabled">{{item.label}}</el-radio>
       </el-radio-group>
     </el-form-item>
     <el-form-item label="专业知识" prop="title18_2">
       <el-radio-group v-model="formData.title18_2" size="medium">
         <el-radio v-for="(item, index) in titleMatrix18Options" :key="index" :label="item.value"
                   :disabled="item.disabled">{{item.label}}</el-radio>
       </el-radio-group>
     </el-form-item>
     <el-form-item label="软件设计" prop="title18_3">
       <el-radio-group v-model="formData.title18_3" size="medium">
         <el-radio v-for="(item, index) in titleMatrix18Options" :key="index" :label="item.value"
                   :disabled="item.disabled">{{item.label}}</el-radio>
       </el-radio-group>
     </el-form-item>
     <el-form-item label="外语能力" prop="title18_4">
       <el-radio-group v-model="formData.title18_4" size="medium">
         <el-radio v-for="(item, index) in titleMatrix18Options" :key="index" :label="item.value"
                   :disabled="item.disabled">{{item.label}}</el-radio>
       </el-radio-group>
     </el-form-item>
     <el-form-item label="软件开发" prop="title18_5">
       <el-radio-group v-model="formData.title18_5" size="medium">
         <el-radio v-for="(item, index) in titleMatrix18Options" :key="index" :label="item.value"
                   :disabled="item.disabled">{{item.label}}</el-radio>
       </el-radio-group>
     </el-form-item>
     <el-form-item label="团队合作" prop="title18_6">
       <el-radio-group v-model="formData.title18_6" size="medium">
         <el-radio v-for="(item, index) in titleMatrix18Options" :key="index" :label="item.value"
                   :disabled="item.disabled">{{item.label}}</el-radio>
       </el-radio-group>
     </el-form-item>
     <el-form-item label="组织能力" prop="title18_7">
       <el-radio-group v-model="formData.title18_7" size="medium">
         <el-radio v-for="(item, index) in titleMatrix18Options" :key="index" :label="item.value"
                   :disabled="item.disabled">{{item.label}}</el-radio>
       </el-radio-group>
     </el-form-item>
     <el-form-item label="学习能力" prop="title18_8">
       <el-radio-group v-model="formData.title18_8" size="medium">
         <el-radio v-for="(item, index) in titleMatrix18Options" :key="index" :label="item.value"
                   :disabled="item.disabled">{{item.label}}</el-radio>
       </el-radio-group>
     </el-form-item>
     <el-form-item label="人际交往" prop="title18_9">
       <el-radio-group v-model="formData.title18_9" size="medium">
         <el-radio v-for="(item, index) in titleMatrix18Options" :key="index" :label="item.value"
                   :disabled="item.disabled">{{item.label}}</el-radio>
       </el-radio-group>
     </el-form-item>
     <el-form-item label="创新能力" prop="title18_10">
       <el-radio-group v-model="formData.title18_10" size="medium">
         <el-radio v-for="(item, index) in titleMatrix18Options" :key="index" :label="item.value"
                   :disabled="item.disabled">{{item.label}}</el-radio>
       </el-radio-group>
     </el-form-item>
 </el-form-item>
<!--        <h3 style="margin-bottom: 10px">第四部分 课程体系合理性</h3>-->
        

      </el-row>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>
<script>
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        grade: undefined,
        name: undefined,
        phone: undefined,
        title1: undefined,
        title2: undefined,
        title3: "",
        title4: "",
        title5: "",
        title6: "",
        title7: "",
        title8: "",
        title9: "",
        title10: undefined,
        title11: undefined,
        title13: undefined,
        title14: undefined,
        title15: undefined,
        title16: undefined,
        title17: undefined,
        title18_1: undefined,
        title18_2: undefined,
        title18_3: undefined,
        title18_4: undefined,
        title18_5: undefined,
        title18_6: undefined,
        title18_7: undefined,
        title18_8: undefined,
        title18_9: undefined,
        title18_10: undefined,
      },

      rules: {
        grade: [{required: true,message: '该选择不能为空',trigger: 'change'}],
        name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        phone: [{required: true, message: '请输入联系电话', trigger: 'blur'}],
        title1: [{required: true, message: '请输入工作单位', trigger: 'blur'}],
        title2: [{required: true, message: '请输入工作城市', trigger: 'blur'}],
        title3: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title4: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title5: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title6: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title7: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title8: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title9: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title10: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title11: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        // title12: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title13: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title14: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title15: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title16: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title17: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title18_1:  [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title18_2:  [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title18_3:  [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title18_4:  [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title18_5:  [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title18_6:  [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title18_7:  [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title18_8:  [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title18_9:  [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title18_10: [{required: true, message: '该选择不能为空', trigger: 'change'}],
      },
      gradeOptions: [
          {"label": "2012级", "value": 2012},
          {"label": "2013级", "value": 2013},
          {"label": "2014级", "value": 2014},
          {"label": "2015级", "value": 2015},
          {"label": "2016级", "value": 2016}],
      title3Options: [
          {"label": "政府机关", "value": 1},
          {"label": "事业单位", "value": 2},
          {"label": "国有企业", "value": 3},
          {"label": "私有企业", "value": 4},
          {"label": "其他", "value": 5}],
      title4Options: [
          {"label": "软件开发", "value": 1},
          {"label": "软件测试", "value": 2},
          {"label": "系统集成", "value": 3},
          {"label": "软件构架", "value": 4},
          {"label": "系统运维", "value": 5},
          {"label": "软件售前/后", "value": 6},
          {"label": "其他", "value": 7}],
      title5Options: [
          {"label": "研发", "value": 1},
          {"label": "生产", "value": 2},
          {"label": "教育", "value": 3},
          {"label": "服务业", "value": 4},
          {"label": "金融", "value": 5},
          {"label": "其他", "value": 6}],
      title6Options: [
          {"label": "管理岗", "value": 1},
          {"label": "行政岗", "value": 2},
          {"label": "技术岗", "value": 3},
          {"label": "生产岗", "value": 4},
          {"label": "质检岗", "value": 5},
          {"label": "运维岗", "value": 6},
          {"label": "售前/后岗", "value": 7},
          {"label": "自主创业", "value": 8},
          {"label": "其他", "value": 9}],
      title7Options: [
          {"label": "技术员", "value": 1},
          {"label": "股级/小组长/项目参与者", "value": 2},
          {"label": "科级/项目主研人", "value": 3},
          {"label": "处级/项目主持者", "value": 4},
          {"label": "中层以上", "value": 5},
          {"label": "其他", "value": 6}],
      title8Options: [
          {"label": "助理工程师", "value": 1},
          {"label": "工程师", "value": 2},
          {"label": "高级工程师", "value": 3},
          {"label": "教授级工程师", "value": 4},
          {"label": "其他", "value": 6}],
      title9Options: [
          {"label": "5K以下", "value": 1},
          {"label": "5K-10K", "value": 2},
          {"label": "10K-15K", "value": 3},
          {"label": "15K-20K", "value": 4},
          {"label": "20K-30K", "value": 5},
          {"label": "30K以上", "value": 6}],
      titleMatrix10_17Options: [
        {"label": "非常符合", "value": 1},
        {"label": "比较符合", "value": 2},
        {"label": "一般符合", "value": 3},
        {"label": "基本不符合", "value": 4},
        {"label": "非常不符合", "value": 5}],
      titleMatrix18Options: [
          {"label": "精通", "value": 1},
          {"label": "熟练", "value": 2},
          {"label": "满意", "value": 3},
          {"label": "一般", "value": 4},
          {"label": "较差", "value": 5}],
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        console.log(this.formData)
        // TODO 提交表单
        this.request.post("/questionnaire_count",this.formData).then(res=>{
          console.log('success')
        }).catch(()=>{
          console.log('error')
        })
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}

</script>
<style>
.el-form-item__label{
  text-align:left !important;
  width: 100% !important;
  float: none !important; /*取消浮动*/
  word-break: break-word !important; /*支持截断换行*/
}
.el-form-item__content{
  text-align: left !important;
  margin-left: 30px !important;
}
</style>
