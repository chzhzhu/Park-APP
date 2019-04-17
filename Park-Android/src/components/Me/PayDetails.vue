<template>
    <div id="app">
        <Header></Header>
        <div>
            <img src="../../assets/icon/payDetails_money.png" id="money">
            <span id="myPurse">My Purse</span>
            <span id="account">{{this.$route.params.account}}</span>
            <x-button plain style="margin-top: 50px;width: 85%;" @click.native="onClickCharge">Charge</x-button>
        </div>
        <Bottom></Bottom>
        <div v-transfer-dom>
          <x-dialog v-model="show">
            <div style="height: auto;width: 100%;text-align: left;margin:0 0;padding: 0;">
              <div @click="show=false" class="closeIcon">
                  <icon type="cancel"></icon>
                  </div>
                  <div class="dialogTitle">
                    <span>Charge</span>
                  </div>
                  <br>
                  <br>
                  <br>
                  <div style="margin-left: 15px;"><span style="font-size: 18px;">Please enter the recharge amount:</span></div>
                  <group style="margin:8px 18px;">
                    <x-input v-model="inputValue" type="number" placeholder="¥ 0.00  CNY" style="padding: 10px 5px;font-size: 20px;" :show-clear="true" required></x-input>
                  </group>
                  <br>
                  <x-button plain style="margin-top: 10px;margin-bottom: 15px;width: 90%" @click.native="onClickCommit">Commit</x-button>
                </div>
              </x-dialog>
            </div>      
        <toast v-model="showPositionValue" type="text" :time="800" is-show-mask :text="toastText" position="middle"></toast>
    </div>
</template>

<script>
import Header from '../Common/Header'
import Bottom from '../Common/Bottom'
import { XButton, XDialog, Icon, XInput, Group, Toast, TransferDomDirective as TransferDom } from 'vux'
export default {
  directives: {
    TransferDom
  },
  data () {
    return {
      show: false,
      inputValue: '',
      account: this.$route.params.account,
      username: this.GLOBAL.USERNAME.replace(/\s+/g,""),
      chargeUrl: this.GLOBAL.DOMAIN + 'account/addmoney',
      payload: {'username': '','account': ''},
      showPositionValue: false,
      toastText: ''
    }
  },
  components: {
    Header,
    Bottom,
    XButton,
    XDialog,
    Icon,
    XInput,
    Group,
    Toast
  },
  methods: {
    showToast (val) {
      this.toastText = val
      this.showPositionValue = true
    },
    onClickCharge () {
      console.log(this.account)
      this.show = true
    },
    onClickCommit () {
      if(this.inputValue=='' || parseInt(this.inputValue)<0){
        this.showToast('Error Input')
      }else{
        this.payload.username = this.username
        this.payload.account = this.inputValue
        var payloadStr = JSON.stringify(this.payload)
        console.log(this.chargeUrl + payloadStr)
        this.axios.post(this.chargeUrl,payloadStr,{headers: {'Content-Type': 'application/json;charset=UTF-8'}}).then(res=>{
          console.log(res)
          var resDate = res.data
          console.log(resDate)
          if(resDate=='Set account successfully'){
            this.showToast('Charge successfully')
            this.show = false
          }else{
            this.showToast('Failed to charge')
            this.show = false
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
#money {
  height: 60px;
  width: 60px;
  display: flex;
  margin: 0 auto;
  margin-top: 50px;
}
#myPurse {
  display: block;
  text-align: center;
  margin-top: 30px;
  font-weight: bold;
  font-size: 23px;
}
#account {
  display: block;
  text-align: center;
  margin-top: 20px;
  font-size: 40px
}
.closeIcon {
  text-align: right;
  margin-top: 5px;
  color: #9D9D9D;
}
.dialogTitle {
  color: #35495e;
  text-align: left;
  position: absolute;
  top: 10px;
  left: 15px;
  font-size: 30px;
  font-weight: bold;
}
</style>
