<template>
    <div id="app">
        <Header></Header>
        <div>
            <img src="../../assets/icon/payDetails_money.png" id="money">
            <span id="myPurse">My Purse</span>
            <span id="account">1000.00</span>
            <x-button plain style="margin-top: 50px;width: 300px;" @click.native="onClickCharge">charge</x-button>
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
                    <x-input v-model="inputValue" type="number" placeholder="¥ 0.00  CNY" style="padding: 10px 5px;font-size: 20px;" :show-clear="true"  @on-click-clear-icon="onClickClearIcon" @on-focus="onFocus"></x-input>
                  </group>
                  <br>
                  <x-button plain style="margin-top: 10px;margin-bottom: 15px;width: 90%" @click.native="onClickCommit">Commit</x-button>
                </div>
              </x-dialog>
            </div>
    </div>
</template>

<script>
import Header from '../Common/Header'
import Bottom from '../Common/Bottom'
import { XButton, XDialog, Icon, XInput, Group, TransferDomDirective as TransferDom } from 'vux'

export default {
  directives: {
    TransferDom
  },
  data () {
    return {
      show: false,
      inputValue: ''
    }
  },
  components: {
    Header,
    Bottom,
    XButton,
    XDialog,
    Icon,
    XInput,
    Group
  },
  methods: {
    onClickCharge () {
      const self = this
      self.show = true
    },
    onClickClearIcon () {
      console.log('click clear icon')
      self.inputValue = ''
    },
    onClickCommit () {

    },
    onFocus () {
      console.log('on Focus')
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
  font-size: 20px;
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
