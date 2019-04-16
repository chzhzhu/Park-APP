<template>
  <div id="footer">
    <tabbar>
        <tabbar-item selected link="/Map" style="text-decoration: none;">
          <img slot="icon" src="../../assets/icon/bottom_map.png">
          <span slot="label" style="font-size: 12px;color: #999999;">Map</span>
        </tabbar-item>
        <tabbar-item selected link="/Rent" style="text-decoration: none;">
          <img slot="icon" src="../../assets/icon/bottom_rentInfo.png">
          <span slot="label" style="font-size: 12px;color: #999999;">Rent Info</span>
        </tabbar-item>
        <tabbar-item selected link="/Me" style="text-decoration: none;">
          <img slot="icon" src="../../assets/icon/bottom_me.png">
          <span slot="label" style="font-size: 12px;color: #999999;">Me</span>
        </tabbar-item>
      </tabbar>
  </div>
</template>

<script>
import { Tabbar, TabbarItem } from 'vux'

export default {
  data () {
    return {
    }
  },
  components: {
    Tabbar,
    TabbarItem
  },
  methods: {
    clickLink () {
      this.$store.commit('setTransition', 'turn-on')
    }
  },
  mounted () {
    var _this = this
    window.addEventListener(
      'popstate',
      function (e) {
        _this.$store.commit('setTransition', 'turn-off')
      },
      false
    )
  }
}
</script>

<style>
#footer{
height: 50px;
width: 100%;
position: fixed;
bottom: 0;
}
</style>
