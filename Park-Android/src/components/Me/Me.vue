<template>
  <div id="app">
    <Header></Header>
      <div>
        <panel :list="list1" :type="type1" style="text-decoration: none; text-align: left;height: 100px;"></panel>
        <panel :list="list2" :type="type2" style="text-decoration: none; margin-top: 20px; text-align: left" @on-click-item="getAccount"></panel>
        <panel :list="list3" :type="type3" style="text-decoration: none; text-align: left;margin-top: 5px;"></panel>
      </div>
      <div>
        <loading :show="isshowLoading" :text="loadingText"></loading>
      </div>
    <Bottom></Bottom>
  </div>
</template>
<script>
import Header from '../Common/Header'
import Bottom from '../Common/Bottom'
import { Panel, Loading} from 'vux'

export default {
  data () {
    return {
      type1: '1',
      type2: '3',
      type3: '3',
      list1: [{
        src: require('@/assets/icon/me_avatar.png'),
        fallbackSrc: '',
        title: 'User Name',
        desc: this.GLOBAL.USERNAME
      }],
      list2: [{
        src: require('@/assets/icon/me_payDetails.png'),
        fallbackSrc: '',
        title: 'Pay'
      }],
      list3: [{
        src: require('@/assets/icon/me_resetPassword.png'),
        fallbackSrc: '',
        title: 'Reset Password',
        url: '/ResetPassword'
      }],
      username: this.GLOBAL.USERNAME,
      payload: {'username': ''},
      getMoneyUrl: this.GLOBAL.DOMAIN + 'account/money',
      isshowLoading: false,
      loadingText: ''
    }
  },
  components: {
    Header,
    Bottom,
    Panel,
    Loading
  },
  methods: {
    showLoading () {
      this.loadingText = 'Loading...'
      this.isshowLoading = true
    },
    getAccount () {
      this.showLoading()
      this.payload.username = this.username
      var payloadStr = JSON.stringify(this.payload)
      console.log(this.getMoneyUrl + payloadStr)
      this.axios.get(this.getMoneyUrl,payloadStr,{headers: {'Content-Type': 'application/json;charset=UTF-8'}}).then(res=>{
        console.log(res)
        var resDate = res.data
        console.log(resDate.account)
        if(resDate!=null){
          this.$router.push({name: 'PayDetails', params: {account: resDate.account}})
        }else{
          alert('Get account failed')
        }
      }).catch(function(error){
        console.log(error)
      })
    }

  }

}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2, h4{
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
</style>
