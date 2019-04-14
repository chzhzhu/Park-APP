import Vue from 'vue'
import Router from 'vue-router'
import Rent from '@/components/Rent'
import QRCodeScanner from '@/components/QRCodeScanner'
import Scanner from '@/components/Scanner'
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
    },
    {
      path: '/ResetPassword',
      name: 'ResetPassword',
      component: ResetPassword
    }
  ]
})
