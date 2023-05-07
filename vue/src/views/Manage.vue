<template>
  <el-container style="min-height: 100vh">

    <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgba(0,21,41,0.35); height: 100%">
        <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow" />
    </el-aside>

    <el-container>
      <el-header style="border-bottom: 1px solid #ccc;">
        <Header :collapseBtnClass="collapseBtnClass" @asideCollapse="collapse" :user="user" />
      </el-header>

      <el-main >
          <!--表示当前页面的子路由会在 <router-view /> 里面展示-->
          <router-view @refreshUser="getUser" />
      </el-main>

    </el-container>
  </el-container>
</template>

<script>

import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'Home',
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      user: {}
    }
  },
  components: {
    Aside,
    Header
  },
  created() {
    // 从后台获取最新的User数据
    this.getUser()
  },
  methods: {
    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    getUser() {
      let username = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username : ""
      if (username) {
        // 从后台获取User数据
        this.request.get("/user/username/" + username).then(res => {
          // 重新赋值后台的最新User数据
          this.user = res.data
        })
      }
    }
  }
}
</script>
<style>
/*.el-aside {*/
/*  display: block;*/
/*  position: absolute;*/
/*  left: 0;*/
/*  top: 60px;*/
/*  bottom: 0;*/
/*}*/
/*.el-header {*/
/*  position: relative;*/
/*  width: 100%;*/
/*  height: 60px;*/
/*}*/
/*.el-main {*/
/*  position: absolute;*/
/*  left: 200px;*/
/*  right: 0;*/
/*  top: 60px;*/
/*  bottom: 0;*/
/*  overflow-y: scroll;*/
/*}*/
</style>
