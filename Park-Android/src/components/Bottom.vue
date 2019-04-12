<template>
<div>
  <tabbar class="vux-demo-tabbar" icon-class="vux-center" slot="bottom">
      <tabbar-item selected link="/" @on-item-click="clickLink">
        <span class="demo-icon-22 vux-demo-tabbar-icon-home" slot="icon" style="position:relative;top: -2px;">&#xe637;</span>
        <span slot="label">Map</span>
      </tabbar-item>
      <tabbar-item selected link="/Details"  @on-item-click="clickLink">
         <span class="demo-icon-22" slot="icon">&#xe633;</span>
         <span slot="label">Me</span>
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
