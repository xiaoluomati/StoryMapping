import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Header from '@/components/Header.vue'
import MapHeader from '@/components/MapHeader.vue'
import Navmenu from '@/components/NavMenu.vue'
import StoryMapManager from '@/views/StoryMapManager.vue'
import CreateMap from '@/views/CreateMap.vue'
import StoryMap from '@/views/StoryMap.vue'
import Cards from '@/views/Cards.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      type: 'home',
      name: 'home',
      redirect: '/storymap-manager',
      component: Home,
      children: [
        {
          path: '/storymap-manager',
          components: {
            default: StoryMapManager,
            navheader: Header,
            aside: Navmenu
          }
          // leaf: true, // 只有一个节点
          // iconCls: 'iconfont icon-home', // 图标样式class
          // menuShow: true
        },
        {
          path: '/create',
          components: {
            default: CreateMap,
            navheader: Header,
            aside: Navmenu
          }
        }
      ]
    },
    {
      path: '/',
      type: 'storymap',
      name: 'storymap',
      redirect: '/storymap',
      component: StoryMap,
      children: [
        {
          path: '/storymap',
          components: {
            navheader: MapHeader,
            cards: Cards
          }
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
