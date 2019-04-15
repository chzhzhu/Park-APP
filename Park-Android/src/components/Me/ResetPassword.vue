<template>
    <div>
      <Header></Header>
        <div style="margin-top: 15px; text-align: left;">
          <div id="hint"><span>You need to login again after you reset your password.</span></div>
            <x-input class="resetInput" title="UserName" :readonly="true" style="margin-top: 15px;" value="123"></x-input>
            <x-input class="resetInput" title="Old Password" type="password" placeholder="old password" :min="2" :max="18"></x-input>
            <x-input class="resetInput" title="New Password" type="password" placeholder="new password" :min="2" :max="18"></x-input>
            <x-input class="resetInput" title="Confirm Password" type="password" placeholder="new password" :min="2" :max="18"></x-input>
            <x-button plain style="margin-top: 50px;width: 300px;" @click.native="onClick">Commit</x-button>
            <div v-transfer-dom>
              <confirm
                v-model="show6"
                :show-cancel-button="false"
                confirm-text="Confirm"
                title="HINT"
                @on-confirm="onConfirm"
                @on-show="onShow"
                @on-hide="onHide">
                <p style="text-align:center;">PLEASE LOGIN AGAIN</p>
              </confirm>
            </div>
        </div>
      <Bottom></Bottom>
    </div>
</template>

<script>
import Header from '../Common/Header'
import Bottom from '../Common/Bottom'
import { XInput, XButton, Confirm, TransferDomDirective as TransferDom, XSwitch, Toast } from 'vux'

export default {
  data () {
    return {
      show6: false,
      confirmLink: ''
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
    Toast
  },
  methods: {
    onHide () {
      console.log('on hide')
    },
    onShow () {
      console.log('on show')
    },
    onCancel () {
      console.log('on cancel')
    },
    onConfirm () {
      const self = this
      console.log('on confirm')
      self.$router.push({
        path: '/Navigate'
      })
    },
    onClick () {
      const self = this
      self.show6 = true
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
