<template>
    <div>
      <Header></Header>
        <div style="margin-top: 15px; text-align: left;">
          <div id="hint"><span>You need to login again after you reset your password.</span></div>
            <x-input class="resetInput" title="UserName" :readonly="true" style="margin-top: 15px;" v-model="user"></x-input>
            <x-input class="resetInput" title="Old Password" type="password" placeholder="old password" :min="6" :max="18" v-model="oldpassword"></x-input>
            <x-input class="resetInput" title="New Password" type="password" placeholder="new password" :min="6" :max="18" v-model="newpassword"></x-input>
            <x-input class="resetInput" title="Confirm Password" type="password" placeholder="new password" :min="6" :max="18" v-model="newpassword2" :equal-with="newpassword"></x-input>
            <div>
              <img src="../../assets/icon/rentInfo_hint.png" style="width: 20px;height: 20px;margin-top: 10px;margin-left: 5px;">
              <span style="font-size: 15px;">Passwords must be at least 6-18 characters long</span></div>
            <x-button plain style="margin-top: 50px;width: 85%;" @click.native="onClickCommit">Commit</x-button>

            <div v-transfer-dom>
              <confirm
                v-model="showConfirm"
                :show-cancel-button="true"
                confirm-text="Confirm"
                cancel-text="Cancel"
                @on-confirm="onClickConfirm">
                <p style="text-align:center;font-size: 20px;">Please login again</p>
              </confirm>
            </div>
            <toast v-model="showPositionValue" type="text" :time="800" is-show-mask :text="toastText" position="middle"></toast>
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
import { XInput, XButton, Confirm, TransferDomDirective as TransferDom, XSwitch, Toast, Loading } from 'vux'

export default {
  data () {
    return {
      showConfirm: false,
      confirmLink: '',
      oldpassword: '',
      newpassword: '',
      newpassword2: '',
      user: this.GLOBAL.USERNAME,
      showPositionValue: false,
      toastText: '',
      isshowLoading: false,
      loadingText: '',
      user1: {'username':'','password':''},
      user2: {'username':'','password':''},
      payload: {},
      resetPasswordUrl: this.GLOBAL.DOMAIN + 'hello/resetpasswd'
    }
  },
  directives: {
    TransferDom
  },
  components: {
    Header,
    Bottom,
    XInput,
    XButton,
    Confirm,
    Toast,
    Loading
  },
  methods: {
    showToast (val) {
      this.toastText = val
      this.showPositionValue = true
    },
    showLoading () {
      this.loadingText = 'Loading...'
      this.isshowLoading = true
    },
    onClickConfirm () {
      self.$router.push({
        path: '/Signin'
      })
    },
    onClickCommit () {
      if(this.oldpassword==''||this.newpassword==''||this.newpassword2==''){
        this.showToast('Can not be empty')
      }else if(this.newpassword!=this.newpassword2){
        this.showToast('Password mismatch')
      }else{
        this.showLoading()
        //reset password
        this.user1.username = this.user
        this.user2.username = this.user
        this.user1.password = this.oldpassword
        this.user2.password = this.newpassword
        var user1Str = JSON.stringify(this.user1)
        var user2Str = JSON.stringify(this.user2)
        var payloadStr = '{'+user1Str+','+user2Str+'}'
        console.log(this.resetPasswordUrl + payloadStr)
        this.axios.get(this.resetPasswordUrl,payloadStr,{headers: {'Content-Type': 'application/json;charset=UTF-8'}}).then(res=>{
          console.log(res)
          var resDate = res.data
          console.log(resDate.account)
          if(resDate==''){
            this.showConfirm = true
          }else{
            this.showToast('Failed to reset password')
          }
        }).catch(function(error){
          console.log(error)
        }) 
      }
    }
    
  }
}
</script>

<style scoped>
#hint {
  margin-top: 20px;
  margin-left: 15px;
  font-size: 18px;
}
.resetInput {
  background-color: #FFFFFF;
  font-size: 16px;
}
</style>
