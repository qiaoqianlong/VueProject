<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <h3 style="margin-bottom: 10px">第一部分 基本信息</h3>

      <el-form-item label="您所在企业的名称是？" prop="enterpriseName" >
        <el-input v-model="formData.enterprisename" placeholder="请输入企业名称" clearable :style="{width: '30%'}">
        </el-input>
      </el-form-item>

      <el-form-item label="1.贵单位的企业性质。" prop="enterpriseNature">
        <el-radio-group v-model="formData.enterprisenature" size="medium">
          <el-radio v-for="(item, index) in enterpriseNatureOptions" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="2.贵单位的企业类型。" prop="enterpriseType">
        <el-radio-group v-model="formData.enterprisetype" size="medium">
          <el-radio v-for="(item, index) in enterpriseTypeOptions" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>

      <h3 style="margin-bottom: 10px">第二部分 培养目标</h3>

      <h4 style="margin-top: 30px;">1. 下面是本专业的培养目标，请您对本专业的培养目标进行评价。 </h4>
      <div style="text-align: center">软件工程专业培养目标</div>
      <div>本专业培养德智体美全面发展，适应国家经济和社会发展需要，能够在互联网、软件与信息服务、金融、制造、教育等行业，从事软件工程相关的研究、设计、开发、测试、运维和管理等方面工作的应用型工程技术人才。</div>
      <div>本专业学生毕业5年左右具备的素养和能力具体如下：</div>
      <div>目标1：人文素养：具有良好的人文素养、职业道德和社会责任感，能够在工程实践中自觉遵守法律法规和职业道德规范；</div>
      <div>目标2：专业知识：能够运用专业知识和技术，对软件工程领域的复杂工程问题设计解决方案，并予以成功实现，达到工程师水平；</div>
      <div>目标3：工程能力：能够在企业与社会环境下，承担软件工程领域的科学研究、产品设计、系统开发、应用服务等方面的技术和管理工作；</div>
      <div>目标4：社会能力：适应独立和团队工作环境，具备与同事和公众沟通交流的能力，能够在软件工程项目中完成协调、组织和管理工作；</div>
      <div>目标5：终身学习：能够跟踪专业领域的新技术，通过多种渠道更新知识，具有终身学习能力，适应国内外形势和环境的变化，实现能力和技术水平的提升。</div>

      <el-form-item label="您对专业培养目标合理性的总体认可度为" prop="title1">
        <el-radio-group v-model="formData.title1" size="medium">
          <el-radio v-for="(item, index) in titleMatrix1_4Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="专业培养目标符合国家与地区经济发展与变化的需求" prop="title2">
        <el-radio-group v-model="formData.title2" size="medium">
          <el-radio v-for="(item, index) in titleMatrix1_4Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="专业培养目标符合工程技术全球化发展趋势的需求" prop="title3">
        <el-radio-group v-model="formData.title3" size="medium">
          <el-radio v-for="(item, index) in titleMatrix1_4Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="专业培养目标符合产业发展和变化的需求" prop="title4">
        <el-radio-group v-model="formData.title4" size="medium">
          <el-radio v-for="(item, index) in titleMatrix1_4Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>

      <h4 style="margin-top: 30px;">2. 请从培养目标的几个方面，对贵单位工作的毕业生表现进行评价。</h4>

      <el-form-item label="人文素养：具有良好的人文素养、职业道德和社会责任感，能够在工程实践中自觉遵守法律法规和职业道德规范" prop="title5">
        <el-radio-group v-model="formData.title5" size="medium">
          <el-radio v-for="(item, index) in titleMatrix5_9Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="专业知识：能够运用专业知识和技术，对软件工程领域的复杂工程问题设计解决方案，并予以成功实现，达到工程师水平" prop="title6">
        <el-radio-group v-model="formData.title6" size="medium">
          <el-radio v-for="(item, index) in titleMatrix5_9Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="工程能力：能够在企业与社会环境下，承担软件工程领域的科学研究、产品设计、系统开发、应用服务等方面的技术和管理工作；具有一定的创新意识，提出一些创新的理念、想法等" prop="title7">
        <el-radio-group v-model="formData.title7" size="medium">
          <el-radio v-for="(item, index) in titleMatrix5_9Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="社会能力：适应独立和团队工作环境，具备与同事和公众沟通交流的能力，能够在软件工程项目中完成协调、组织和管理工作" prop="title8">
        <el-radio-group v-model="formData.title8" size="medium">
          <el-radio v-for="(item, index) in titleMatrix5_9Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="终身学习：能够跟踪专业领域的新技术，通过多种渠道更新知识，具有终身学习能力，适应国内外形势和环境的变化，实现能力和技术水平的提升" prop="title9">
        <el-radio-group v-model="formData.title9" size="medium">
          <el-radio v-for="(item, index) in titleMatrix5_9Options" :key="index" :label="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>


      <h4 style="margin-top: 30px; margin-bottom: 20px">3. 您认为大学期间，学生应加强哪些方面的学习和提高才能更好支持毕业后的发展和满足企业需求。</h4>

<!--      <el-form-item  prop="title10">
      <el-checkbox-group v-model="formData.items">
        <el-checkbox v-for="(item, index) in allItems" :key="index" :label="item.id">{{item.label}}</el-checkbox>
      </el-checkbox-group>
      </el-form-item>-->

     <!-- <el-form-item prop="title10_1">
        <el-radio-group v-model="formData.title10_1">
          <el-radio v-for="(item, index) in title10_1Option" :key="index" :label="item.value"
                    :disabled="item.disable">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>-->

      <el-form-item prop="title10_1">
        <el-checkbox-group v-model="formData.title10_1">
          <el-checkbox label="A.责任感和职业道德" style="margin-left: 20px"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>

      <el-form-item prop="title10_2">
        <el-checkbox-group v-model="formData.title10_2">
          <el-checkbox label="B.专业知识" style="margin-left: 20px"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>


      <el-form-item prop="title10_3">
        <el-checkbox-group v-model="formData.title10_3">
          <el-checkbox label="C.工程设计实践能力" style="margin-left: 20px"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>

      <el-form-item prop="title10_4">
        <el-checkbox-group v-model="formData.title10_4">
          <el-checkbox label="D.团队合作能力" style="margin-left: 20px"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>

      <el-form-item prop="title10_5">
        <el-checkbox-group v-model="formData.title10_5">
          <el-checkbox label="E.国际视野" style="margin-left: 20px"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>

      <el-form-item prop="title10_6">
        <el-checkbox-group v-model="formData.title10_6">
          <el-checkbox label="F.沟通能力" style="margin-left: 20px"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>


      <h4 style="margin-top: 30px;">4. 反馈意见</h4>
      <el-form-item label="对培养目标的改进建议和意见" prop="title11">
        <el-input type="textarea" v-model="formData.title11" placeholder="请写下您的建议（若无则不填）" clearable :row="8">
        </el-input>
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
  components: {},
  props: [],
  data() {
    return {



      formData: {
        enterprisename: "",
        enterprisenature: "",
        enterprisetype: "",
        title1: "",
        title2: "",
        title3: "",
        title4: "",
        title5: "",
        title6: "",
        title7: "",
        title8: "",
        title9: "",
        title10_1: "",
        title10_2: "",
        title10_3: "",
        title10_4: "",
        title10_5: "",
        title10_6: "",
        title11: "",
        //items: [],
       //title10: undefined,
      },

      rules: {
        enterprisename:[{required: true, message: '该选择不能为空', trigger: 'change'}],
        enterprisenature: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        enterprisetype: [{required: true, message: '该选择不能为空', trigger: 'change'}],
        title1:[{required: true, message: '该选择不能为空', trigger: 'change'}],
        title2:[{required: true, message: '该选择不能为空', trigger: 'change'}],
        title3:[{required: true, message: '该选择不能为空', trigger: 'change'}],
        title4:[{required: true, message: '该选择不能为空', trigger: 'change'}],
        title5:[{required: true, message: '该选择不能为空', trigger: 'change'}],
        title6:[{required: true, message: '该选择不能为空', trigger: 'change'}],
        title7:[{required: true, message: '该选择不能为空', trigger: 'change'}],
        title8:[{required: true, message: '该选择不能为空', trigger: 'change'}],
        title9:[{required: true, message: '该选择不能为空', trigger: 'change'}],
        //title10_1:[{required: false, message: '该选择不能为空', trigger: 'change'}],
      },

      enterpriseNatureOptions: [
        {"label": "行政机关", "value":1},
        {"label": "事业单位", "value":2},
        {"label": "国有企业", "value":3},
        {"label": "私企（民企）", "value":4},
        {"label": "三资企业", "value":5},
        {"label": "其他", "value":6}
      ],

      enterpriseTypeOptions: [
        {"label": "产品研发企业", "value":1},
        {"label": "生产制造企业", "value":2},
        {"label": "技术服务企业", "value":3},
        {"label": "农业生产企业", "value":4},
        {"label": "政府事业单位", "value":5},
        {"label": "其它", "value":6},
      ],

      titleMatrix1_4Options: [
        {"label": "非常认同", "value":1},
        {"label": "比较认同", "value":2},
        {"label": "一般", "value":3},
        {"label": "不太认同", "value":4},
        {"label": "不认同", "value":5},
      ],

      titleMatrix5_9Options: [
        {"label": "优秀", "value":1},
        {"label": "良好", "value":2},
        {"label": "一般", "value":3},
        {"label": "合格", "value":4},
        {"label": "不合格", "value":5},
      ],

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
        //console.log(this.items)
        // TODO 提交表单
        this.request.post("/unit_questionnaire_count",this.formData).then(res=>{
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
