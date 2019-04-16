<template>
    <div>
      <div id="tabContainer">
        <!--<tab bar-position="top">
            <tab-item selected @on-item-click="changePolicyDis">Distance First</tab-item>
            <tab-item @on-item-click="changePolicyTime">Time First</tab-item>
            <tab-item @on-item-click="changePolicyAvoH">No Highways</tab-item>
         </tab>-->
        <button-tab class="top">
          <button-tab-item class="tab" @on-item-click="changePolicyDis">Distance First</button-tab-item>
          <button-tab-item class="tab" :selected="true" @on-item-click="changePolicyTime">Time First</button-tab-item>
          <button-tab-item class="tab" @on-item-click="changePolicyAvoH">No Highways</button-tab-item>
        </button-tab>
      </div>
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
      <div @click="rent">
        <img src="../../assets/icon/navigation_go.png" class ="image"></img>
      </div>
    
      <Bottom></Bottom>
    </div>
</template>

<script>
import { Tab, TabItem, XButton, ButtonTab, ButtonTabItem } from 'vux'
import Bottom from '../Common/Bottom'
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
    XButton,
    ButtonTabItem,
    ButtonTab,
    Bottom
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
  height: 100%;
  width: 100%;
  bottom: 50px;
}
.map {
    width: 100%;
    height: 100%;
    position: absolute;
}/*
.map {
    position: fixed;
    width: 100%;
    height: 96%;
    margin-top:44px;
}
.view {
    position: fixed;
    width: 100%;
    height: 96%;
}
.top {
  width: 100%;
  height: 44px;
  background-color: white;
  position: fixed;
  top: 0px;
}*/
#tabContainer {
  width: 100%;
  height: 45px;
  z-index: 1000;
  margin-top: 30px;
  position: absolute;
}
.top {
  margin: 0 auto;
  width: 85%;
}
.tab {
  text-decoration: none;
  border: 1px solid #04BE02;
  background-color: #F0F0F0;
  color: #13227a;
  font-size: 15px;
}
.image {
 width: 60px;
 height: 60px;
 bottom: 90px;
 right: 25px;
 position: absolute;
}
</style>
