import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Header from '@/components/Header.vue'
import Navmenu from '@/components/NavMenu.vue'
import StoryMapManager from '@/views/StoryMapManager.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      redirect: '/storymap-manager',
      children: [
        {
          path: '/stroymap-manager',
          components: {
            default: StoryMapManager,
            Header: Header,
            aside: Navmenu
          },
          leaf: true, // 只有一个节点
          iconCls: 'iconfont icon-home', // 图标样式class
          menuShow: true
        }
      ]
    }
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (about.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    // }
  ]
})
