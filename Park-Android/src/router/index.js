import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import MMT from '@/components/mmt'
import PayDetails from '@/components/PayDetails'
import B from '@/components/B'
import Details from '@/components/Details'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/mmt',
      name: 'MMT',
      component: MMT
    },
    {
      path: '/PayDetails',
      name: 'PayDetails',
      component: PayDetails
    }
  ]
})
