<template>
  <div style="color: #666;font-size: 14px;">
    <div style="padding-bottom: 20px">
      <b>欢迎你！{{ user.nickname }}</b>
    </div>
    <el-card>
      工程教育认证系统包括培养效果评价和教学数据管理两个部分内容，本课题的设计内容围绕培养效果评价展开。主要内容包括：（1）培养方案评价体系：构建专业培养目标、毕业要求和课程等支撑数据，构建课程支撑毕业要求权重矩阵。（2）课程目标达成度评价体系：课前配置课程的评价参数，专业负责人审核后，教师录入成绩，系统计算课程目标达成度，查询统计结果，预览并导出分析报告，编辑持续改进建议。（3）毕业要求指标达成度评价体系：计算毕业要求指标达成度，查询统计结果，预览并导出分析报告，编辑持续改进建议。（4）培养目标反馈评价体系：设计调查问卷，统计调查结果，预览并导出分析报告，编辑持续改进建议。
    </el-card>
    <div style="height: 1px; background: #ddd; margin: 20px 0"/>

    <el-row :gutter="20">
      <el-col :span="10">
        <div style="padding: 20px 0; font-size: 20px">技术栈</div>
        <el-row>
          <el-col :span="12" style="line-height: 30px">
            <div><b>后端</b></div>
            <div>SpringBoot2</div>
            <div>Hutool</div>
            <div>Poi</div>
            <div>Lombok</div>
            <div>Mybatis/Mybatis-plus</div>
          </el-col>
          <el-col :span="10" style="line-height: 30px">
            <b>前端</b>
            <div>Vue2</div>
            <div>Vue-Router</div>
            <div>VueX</div>
            <div>ElementUI</div>
            <div>Axios</div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  }
}
</script>
