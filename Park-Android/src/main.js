// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import BaiduMap from 'vue-baidu-map'
import vuex from 'vuex'
Vue.use(vuex)
Vue.use(BaiduMap, {
  /* Visit http://lbsyun.baidu.com/apiconsole/key for details about app key. */
  ak: 'pD4xiU2LuyNUavXUAcDixhdMfHzm13Rh'
})
Vue.config.productionTip = false
var store = new vuex.Store({
  state: {
    states: 'turn-on'
  },
  mutations: {
    setTransition (state, states) {
      state.states = states
    }
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
