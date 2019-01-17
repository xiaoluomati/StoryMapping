import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './plugins/iconfont.css'
import '@/assets/css/story-style.css'

Vue.config.productionTip = false
export const eventBus = new Vue()
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
