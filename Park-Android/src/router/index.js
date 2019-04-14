import Vue from 'vue'
import Router from 'vue-router'
import Navigate from '@/components/Navigate/Navigate'
import Me from '@/components/Me/Me'
import PayDetails from '@/components/Me/PayDetails'
import ResetPassword from '@/components/Me/ResetPassword'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/Navigate',
      name: 'Navigate',
      component: Navigate
    },
    {
      path: '/Me',
      name: 'Me',
      component: Me
    },
    {
      path: '/PayDetails',
      name: 'PayDetails',
      component: PayDetails
    },
    {
      path: '/ResetPassword',
      name: 'ResetPassword',
      component: ResetPassword
    }
  ]
})
