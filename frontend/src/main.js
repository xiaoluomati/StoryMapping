import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './plugins/iconfont.css'
import '@/assets/css/story-style.css'

Vue.config.productionTip = false
export const eventBus = new Vue()

Vue.component('remote-script', {
  render: function (createElement) {
    let self = this
    return createElement('script', {
      attrs: {
        type: 'text/javascript',
        src: this.src
      },
      on: {
        load: function (event) {
          self.$emit('load', event)
        },
        error: function (event) {
          self.$emit('error', event)
        },
        readystatechange: function (event) {
          if (this.readyState === 'complete') {
            self.$emit('load', event)
          }
        }
      }
    })
  },

  props: {
    src: {
      type: String,
      required: true
    }
  }
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
