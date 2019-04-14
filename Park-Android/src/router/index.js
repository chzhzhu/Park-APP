import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import MMT from '@/components/mmt'
import PayDetails from '@/components/PayDetails'
import B from '@/components/B'
import Details from '@/components/Details'
import Rent from '@/components/Rent'
import QRCodeScanner from '@/components/QRCodeScanner'
import Scanner from '@/components/Scanner'

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
    },
    {
      path: '/rent',
      name: 'Rent',
      component: Rent
    },
    {
      path: '/QRcodeScanner',
      name: 'QRCodeScanner',
      component: QRCodeScanner
    },
    {
      path: '/Scanner',
      name: 'Scanner',
      component: Scanner
    }
  ]
})
