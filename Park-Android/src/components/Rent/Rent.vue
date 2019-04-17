<template>
  <div>
    <Header></Header>
    <loading v-model="isLoading"></loading>
    <div v-if="user.equStatus == 'Unlocked'">
      <group id="group">
        <cell :title="username">{{user.username}}</cell>
        <cell :title="order">{{user.orderId}}</cell>
        <cell :title="parkingTime">{{user.startTime}}</cell>
        <cell :title="parkingProt">{{user.parkingProtId}}</cell>
        <cell :title="parkingNum">{{user.equId}}</cell>
        <cell :title="rent">2¥/h</cell>
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
        <div style="text-align: center;margin-top: 70px;"><img src="../../assets/icon/rentInfo_hint.png" style="width: 60px;height: 60px;"></div>
         <br><br>
        <p class="noneMsg">
          You don't have any parking info here.
        </p>
        <br>
        <p class="noneMsg">
          Please rent a parking space first.
        </p>
        <br>
      </div>
      <x-button class="btn1" plain @click.native="goRent">Scan to rent</x-button>
    </div>
    <Bottom></Bottom>
  </div>
</template>

<script>
import Header from '../Common/Header'
import Bottom from '../Common/Bottom'
import { Cell, Group, Loading, XButton, Confirm, Alert } from 'vux'
export default {
  name: 'Rent',
  components: {XButton, Cell, Group, Loading, Confirm, Alert, Header, Bottom},
  data () {
    return {
      user: {
        username: '',
        startTime: '',
        equId: '',
        orderId: '',
        equStatus: '',
        parkingProtId: ''
      },
      title: 'Rent Info',
      username: 'Username:',
      parkingTime: 'Parking Time:',
      parkingNum: 'Parking Num:',
      rent: 'Rent:',
      order: 'Order',
      parkingProt: 'ParkingPort',
      confirmShow: false,
      returnContent: 'Are you sure return the parking port?',
      alertShow: false,
      isLoading: true
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
      this.returnParking()
    },
    onShow () {
      console.log('on show')
    },
    onHide () {
      console.log('on hide')
    },
    onAlertHide () {
      const self = this
      self.$router.push({path: '/Map'})
    },
    returnParking () {
      const self = this
      const url = 'http://localhost:8083/parkingApp/rest/collection/lock'
      const params = {username: self.GLOBAL.username, parkingProtId: self.user.parkingProtId, orderId: self.user.orderId, equStatus: 'Locked'}
      self.axios.post(url, JSON.stringify(params), {headers: {'Content-Type': 'application/json;charset=UTF-8'}}).then(res => {
        if (res.data.status === 200) {
          console.log('success')
        } else {
          console.log('fail')
        }
      }).catch(function (error) {
        console.log(error)
      })
    },
    getUserParkingInfo () {
      const self = this
      const url = 'http://localhost:8083/parkingApp/rest/order/openOrder'
      const param = {username: self.GLOBAL.username}
      self.axios.get(url, {params: param}, {headers: {'Content-Type': 'application/json;charset=UTF-8'}}).then(res => {
        if (res.data.status === 200) {
          console.log(res.data)
          self.isLoading = false
          self.user = res.data
          console.log('success')
        } else {
          console.log('fail')
        }
      }).catch(function (error) {
        console.log(error)
      })
    }

  },
  created () {
    this.getUserParkingInfo()
  }

}
</script>

<style scoped>
  * {
    text-align: left;
    -webkit-align-content: initial;
  }
  .btn1 {
    width: 85%;
    margin-top: 30px;
    text-align: center;
  }
  .btn2 {
    width: 50%;
    text-align: center;
  }
  .noneMsg {
    text-align: center;
    color: red;
    font-size: 20px;
  }
</style>
