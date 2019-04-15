<template>
  <div id="app">
    <Header></Header>
      <div>
        <panel :list="list1" :type="type1" style="text-decoration: none;"></panel>
        <panel :list="list2" :type="type2" style="text-decoration: none;margin-top: 10px"></panel>
        <panel :list="list3" :type="type3"></panel>
      </div>
    <Bottom></Bottom>
  </div>
</template>
<script>
import Header from '../Common/Header'
import Bottom from '../Common/Bottom'
import { Panel } from 'vux'

export default {
  data () {
    return {
      type1: '1',
      type2: '3',
      type3: '3',
      list1: [{
        src: require('@/assets/icon/me_avatar.png'),
        fallbackSrc: '',
        title: 'UserName',
        desc: '123'
        // url: '/component/cell'
      }],
      list2: [{
        src: require('@/assets/icon/payDetails_money.png'),
        fallbackSrc: '',
        title: 'Pay',
        url: '/PayDetails'
      }],
      list3: [{
        src: require('@/assets/icon/me_resetPassword.png'),
        fallbackSrc: '',
        title: 'Reset Password',
        url: '/ResetPassword'
      }]
    }
  },
  components: {
    Header,
    Bottom,
    Panel
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2, h4 {
  font-weight: normal;
  text-decoration: none;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
  text-decoration: none;
}
.out_container{
  min-width: 100%;
  min-height: 100%;
}
</style>
