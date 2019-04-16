<template>
  <div>
    <loading></loading>
    <div v-if="user.locked == true">
      <group id="group">
        <cell :title="username">{{user.name}}</cell>
        <cell :title="parkingTime">{{user.parkingTime}}</cell>
        <cell :title="parkingNum">{{user.parkingNum}}</cell>
        <cell :title="rent">{{user.rent}}</cell>
      </group>
      <br>
      <x-button class="btn2" @click.native="goReturnParking">Return Parking</x-button>
      <div>
        <confirm v-model="confirmShow" title="Return Parking" :content="returnContent" confirm-text="confirm" cancel-text="cancel"
                 @on-cancel="onCancel"
                 @on-confirm="onConfirm"
                 @on-show="onShow"
                 @on-hide="onHide">
        </confirm>
      </div>
      <div>
        <Alert v-model="alertShow" button-text="ok" @on-show="onShow" @on-hide="onAlertHide">Return parking port successfully!</Alert>
      </div>
    </div>
    <div v-else>
      <div>
        <br>
        <br>
        <p class="noneMsg">
          You don't have any Parking Info here.
        </p>
        <br>
        <p class="noneMsg">
          Please go renting Firstly!
        </p>
        <br>
      </div>
      <x-button class="btn1" @click.native="goRent">Rent</x-button>
    </div>
  </div>
</template>

<script>
import { Cell, Group, Loading, XButton, Confirm, Alert } from 'vux'
export default {
  name: 'Rent',
  components: {XButton, Cell, Group, Loading, Confirm, Alert},
  data () {
    return {
      user: {
        name: 'xxx',
        parkingTime: '2019-04-09',
        parkingNum: '007',
        rent: '2',
        locked: false
      },
      title: 'Rent Info',
      username: 'Username:',
      parkingTime: 'Parking Time:',
      parkingNum: 'Parking Num:',
      rent: 'Rent:',
      confirmShow: false,
      returnContent: 'Are you sure return the parking port?',
      alertShow: false
    }
  },
  methods: {
    goRent () {
      const self = this
      self.$router.push({path: '/Scanner'})
    },
    goReturnParking () {
      const self = this
      self.confirmShow = true
    },
    onCancel () {
      console.log('on cancel')
      const self = this
      self.confirmShow = false
    },
    onConfirm () {
      const self = this
      console.log('on confirm')
      // 与后台交互，更改车位信息
      self.alertShow = true
    },
    onShow () {
      console.log('on show')
    },
    onHide () {
      console.log('on hide')
    },
    onAlertHide () {
      const self = this
      self.$router.push({path: '/HelloWorld'})
    }

  }

}
</script>

<style scoped>
  * {
    text-align: left;
    -webkit-align-content: initial;
  }
  .btn1 {
    width: 30%;
  }
  .btn2 {
    width: 50%;
  }
  .noneMsg {
    text-align: center;
    color: red;
  }
</style>
