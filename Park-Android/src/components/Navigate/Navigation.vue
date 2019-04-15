<template>
    <div>
    <baidu-map class="map" :center="center" :zoom="zoom" @ready="handler">
        <bm-view class="view">
        </bm-view>
        <bm-driving
          :start="start"
          :end="end"
          :auto-viewport="true"
          :policy="driverpolicy"
          :panel="false">
        </bm-driving>
    </baidu-map>
    <div class="button" @click="rent">
      <img src="../../assets/icon/go.png" class ="img"></img>
    </div>
    <div class="top">
    <tab bar-position="top">
          <tab-item selected @on-item-click="changePolicyDis">Distance First</tab-item>
          <tab-item @on-item-click="changePolicyTime">Time First</tab-item>
          <tab-item @on-item-click="changePolicyAvoH">No Highways</tab-item>
       </tab>
    </div>
    </div>
</template>

<script>
import { Tab, TabItem, XButton } from 'vux'
export default {
  data () {
    return {
    center: { lng: 0, lat: 0 },
    zoom: 3,
    start: { lng: 0, lat: 0 },
    end: { lng: 0, lat: 0 },
    driverpolicy: "BMAP_DRIVING_POLICY_LEAST_DISTANCE"
    }
  },
  components: {
    Tab,
    TabItem,
    XButton
  },
  methods: {
    handler({ BMap, map }) {
      console.log(BMap, map)
      this.center.lng = this.$route.params.center.lng
      this.center.lat = this.$route.params.center.lat
      this.zoom = 12
    },
    changePolicyDis() {
      this.driverpolicy = "BMAP_DRIVING_POLICY_LEAST_DISTANCE"
    },
    changePolicyTime() {
      this.driverpolicy = "BMAP_DRIVING_POLICY_LEAST_TIME"
    },
    changePolicyAvoH() {
      this.driverpolicy = "BMAP_DRIVING_POLICY_AVOID_HIGHWAY"
    },
    rent() {
    this.$router.push({name: 'Rent', params: {point: this.start, center: this.center}})
    }
  },
   mounted() {
   this.end = this.$route.params.point
   this.center = this.$route.params.center
   this.start = this.center
   }
}
</script>

<style scoped>
.view {
    position: fixed;
    width: 100%;
    height: 96%;
}
.map {
    position: fixed;
    width: 100%;
    height: 96%;
    margin-top:44px;
}
.top {
  width: 100%;
  height: 44px;
  background-color: white;
  position: fixed;
  top: 0px;
}
.button {
  position: absolute;
  width: 40px;
  height: 40px;
  bottom: 80px;
  border-radius: 20px;
  right:20px;
}
.img {
 width: 40px;
 height: 40px;
}
</style>
