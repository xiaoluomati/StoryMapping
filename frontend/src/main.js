import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import routers from './router'
import './plugins/element.js'
import '@/assets/css/story-style.css'

Vue.config.productionTip = false

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  routes: routers
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
