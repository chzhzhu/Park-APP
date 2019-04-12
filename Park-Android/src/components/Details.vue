<template>
    <div class="D">
        <group label-width="4.5em" label-margin-right="2em" label-align="right">
                <cell is-link title="PayDetails" link="/PayDetails" @click.native="clickLink"></cell>
                <cell is-link title="B" link="/B" @click.native="clickLink"></cell>
        </group>
          <tabbar class="vux-demo-tabbar" icon-class="vux-center" slot="bottom">
              <tabbar-item selected link="/" @on-item-click="clickLink">
                <span class="demo-icon-22 vux-demo-tabbar-icon-home" slot="icon" style="position:relative;top: -2px;">&#xe637;</span>
                <span slot="label">Home</span>
              </tabbar-item>
              <tabbar-item selected link="/Details"  @on-item-click="clickLink">
                 <span class="demo-icon-22" slot="icon">&#xe633;</span>
                 <span slot="label">Demos</span>
              </tabbar-item>
          </tabbar>
    </div>
</template>

<script>
import { Cell, CellBox, CellFormPreview, Group, Badge, Tabbar, TabbarItem } from 'vux'

export default {
  data () {
    return {
    }
  },
  components: {
    Group,
    Cell,
    CellFormPreview,
    CellBox,
    Tabbar,
    TabbarItem,
    Badge
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

<style scoped>
.sub-item {
  color: #888;
}
.slide {
  padding: 0 20px;
  overflow: hidden;
  max-height: 0;
  transition: max-height .5s cubic-bezier(0, 1, 0, 1) -.1s;
}
.animate {
  max-height: 9999px;
  transition-timing-function: cubic-bezier(0.5, 0, 1, 0);
  transition-delay: 0s;
}
.D {
  width: 100%;
  height: 100%;
  position: fixed;
}
</style>
