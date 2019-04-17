<template>
    <div class="mycontainer">
        <Header></Header>
        <loading v-model="isLoading"></loading>
        <div style="margin-top:40px">
            <group title="Parking Info" class="group">
              <cell class="cell" title="Number:" :value="equ.equId" value-align='right'></cell>
              <cell class="cell" title="Status:" :value="equ.equName"></cell>
              <cell class="cell" title="Rent:" :value="rent"></cell>
            </group>
            <br>
            <br>
            <x-button @click.native="info">Go to Rent!</x-button>
            <br>
            <x-button @click.native="scan">Back to Rescan</x-button>
         </div>
        <Bottom></Bottom>
    </div>
</template>
<script>
import Header from '../Common/Header'
import Bottom from '../Common/Bottom'
import { Group, Cell, XButton, Loading } from 'vux'
export default {
    data() {
        return {
            equNumber: 10001,
            equStatus: 'Locked',
            rent: '2¥/h',
          equ: {
              equId: '',
              equStatus: '',
              equName: ''
          },
          isLoading: true
        }
    },
    components: {
      Loading,
        Header,
        Bottom,
        Group,
        Cell,
        XButton
    },
    methods: {
      info() {
        this.goToRent()
        this.$router.push({name: 'Rent', params: {equ: this.equ.equId}})
      },
      scan() {
        this.$router.push({name: 'Scanner'})
      },
      getEquInfo () {
        const self = this
        const url = 'http://localhost:8083/parkingApp/rest/parkport/equipment'
        const param = {equId: '1001'}
        this.axios.get(url, {params: param}).then(res => {
             console.log(res.data)
             self.isLoading = false
             const result = res.data
             self.equ.equId = result.equId
             self.equ.equName = result.equName
             self.equ.equStatus = result.equStatus
        }).catch(function (error) {
          console.log(error)
        })
      },
      goToRent () {
        const self = this
        const url = 'http://localhost:8083/parkingApp/rest/collection/unlock'
        const params = {username: self.GLOBAL.username, parkingProtId: self.equ.equId.substring(0, 1), equId: self.equ.equId, equStatus: 'Unlocked'}
        this.axios.post(url, JSON.stringify(params)).then(res => {
          if (res.data.status === 200) {
            console.log('success')
          } else {
            console.log('fail')
          }
        }).catch(function (error) {
          console.log(error)
        })
      },
      getDetailInfo () {
        // this.isLoading = false
        this.getEquInfo()
      }
    },
    created () {
      this.getDetailInfo()
    }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.mycontainer {
    position: fixed;
    width: 100%;
    height: 100%;
}

.vux-close {
    margin-top: 8px;
    margin-bottom: 8px;
    font-family: PingFangSC-Semibold, sans-serif;
}

.cell {
    text-align: left;
}

.group {
  font-size:20px;
}
</style>
