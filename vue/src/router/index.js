import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

// 注册 登录 404 是固定的，直接放在这里
const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/404',
    name: '404',
    component: () => import('../views/404.vue')
  },
  {
    path: '/showStudent',
    name: 'ShowStudent',
    component: () =>import('../views/ShowStudent.vue')
  },
  {
    path: '/showClazz',
    name: 'ShowClazz',
    component: ()=>import('../views/ShowClazz')
  },
  {
    path: '/showMajor',
    name: 'ShowMajor',
    component: ()=>import('../views/ShowMajor')
  },
  {
    path: '/showStudentGradRequireScore',
    name: 'ShowStudentGradRequireScore',
    component: () => import('../views/ShowStudentGradRequireScore')
  },
  {
    path: '/showClazzGradRequireScore',
    name: '/ShowClazzGradRequireScore',
    component: () => import('../views/ShowClazzGradRequireScore')
  },
  {
    path: '/unitQuestionnaireForm',
    name: '/UnitQuestionnaireForm',
    component:() => import('../views/UnitQuestionnaireForm')
  },
  {
    path: '/studentQuestionnaireForm',
    name: '/StudentQuestionnaireForm',
    component:() => import('../views/StudentQuestionnaireForm')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 提供一个重置路由的方法
// 在store的index.js的logout方法中调用该方法
export const resetRouter = () => {
  router.matcher = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
  })
}

// 注意：刷新页面会导致页面路由重置
// 该方法在Login中使用
export const setRoutes = () => {
  // 从前端获取menus
  const storeMenus = localStorage.getItem("menus");
  if (storeMenus) {
    // 获取当前的路由对象名称数组，当不包含Manage路由时，再添加路由
    const currentRouteNames = router.getRoutes().map(v => v.name)
    if (!currentRouteNames.includes('Manage')) {
      // 拼装动态路由
      const manageRoute = { path: '/', name: 'Manage', component: () => import('../views/Manage.vue'), redirect: "/home", children: [
          { path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
          { path: 'course_weights_main', name: '课程评价参数配置', component: () => import('../views/CourseWeightsMain.vue')},
          { path: 'score_main', name: '课程教学成绩管理', component: () => import('../views/ScoreMain.vue')},
          { path: 'password', name: '修改密码', component: () => import('../views/Password.vue')}
        ] }
      const menus = JSON.parse(storeMenus)
      menus.forEach(item => {
        if (item.path) {  // 一级菜单且有path 才去设置路由
          // 注意这里路径有'/',需要去掉
          let itemMenu = { path: item.path.replace("/", ""), name: item.name, component: () => import('../views/' + item.pagePath + '.vue')}
          manageRoute.children.push(itemMenu)
        } else if(item.children.length) {   // 一级菜单无path且有子路由 才去设置路由
          item.children.forEach(item => {
            if (item.path) {
              let itemMenu = { path: item.path.replace("/", ""), name: item.name, component: () => import('../views/' + item.pagePath + '.vue')}
              manageRoute.children.push(itemMenu)
            }
          })
        }
      })
      // 动态添加到现在的路由对象中去
      router.addRoute(manageRoute)
    }
  }
}

// 重置就再set一次路由
setRoutes()


router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称
  store.commit("setPath")


  // 处理第一次登录404的情况
  // 未找到路由的情况：有菜单（404） 无菜单（login）
  if (!to.matched.length) {
    const storeMenus = localStorage.getItem("menus")
    if (storeMenus) {
      next("/404")
    } else {
      // 跳回登录页面
      next("/login")
    }
  }
  // 其他的情况（有路由）都放行
  next()

})

export default router
