import Vue from 'vue'
import Router from 'vue-router'
import Map from '@/components/Navigate/Map'
import Navigation from '@/components/Navigate/Navigation'
import ParkingSpaInfo from '@/components/Rent/ParkingSpaInfo'
import Signin from '@/components/Welcome/Signin'
import Signup from '@/components//Welcome/Signup'
import Welcome from '@/components//Welcome/Welcome'
import Rent from '@/components/Rent/Rent'
import QRCodeScanner from '@/components/Rent/QRCodeScanner'
import Scanner from '@/components/Rent/Scanner'
import Me from '@/components/Me/Me'
import PayDetails from '@/components/Me/PayDetails'
import ResetPassword from '@/components/Me/ResetPassword'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Welcome',
      component: Welcome
    },
    {
      path: '/ParkingSpaInfo',
      name: 'ParkingSpaInfo',
      component: ParkingSpaInfo
    },
    {
      path: '/Navigation',
      name: 'Navigation',
      component: Navigation
    },
    {
      path: '/Map',
      name: 'Map',
      component: Map
    },
    {
      path: '/Signin',
      name: 'Signin',
      component: Signin
    },
    {
      path: '/Signup',
      name: 'Signup',
      component: Signup
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
