<template>
  <div id="app">
    <img src="./assets/logo.png">
    <transition :name="$store.state.states">
    <router-view/>
    </transition>
    <div>Index Page</div>
  <tabbar class="vux-demo-tabbar" icon-class="vux-center" slot="bottom">
      <tabbar-item selected link="/" @on-item-click="clickLink">
        <span class="demo-icon-22 vux-demo-tabbar-icon-home" slot="icon" style="position:relative;top: -2px;">&#xe637;</span>
        <span slot="label">Home</span>
      </tabbar-item>
      <tabbar-item selected link="/Details"  @on-item-click="clickLink" badge="9">
         <span class="demo-icon-22" slot="icon">&#xe633;</span>
         <span slot="label">Demos</span>
      </tabbar-item>
  </tabbar>
  </div>
</template>

<script>
import { ButtonTab, ButtonTabItem, Divider, Tabbar, TabbarItem, Cell } from 'vux'

export default {
  name: 'App',
  data () {
    return {
    }
  },
  components: {
    ButtonTab,
    ButtonTabItem,
    Divider,
    Tabbar,
    TabbarItem,
    Cell
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
* {
  margin: 0;
  padding: 0;
}
.btn {
  width: 50%;
}
html,
body,
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.turn-on-enter {
  transform: translate3d(100%, 0, 0);
}
.turn-on-leave-to {
  /* transform: translate3d(-20%, 0, 0); */
}
.turn-on-enter-active,
.turn-on-leave-active {
  transition: transform 0.4s ease;
}
.turn-off-enter {
  /* transform: translate3d(-20%, 0, 0); */
}
.turn-off-leave-to {
  transform: translate3d(100%, 0, 0);
}
.turn-off-enter-active,
.turn-off-leave-active {
  transition: transform 0.4s ease;
}
.turn-off-leave-active {
  z-index: 2;
}
.bottom {
  width: 100%;
  height: 50px;
  background-color: white;
  position: fixed;
  bottom: 0px;
}
.demo-icon-22 {
  font-family: 'vux-demo';
  font-size: 22px;
  color: #888;
}
.weui-tabbar.vux-demo-tabbar {
  /** backdrop-filter: blur(10px);
  background-color: none;
  background: rgba(247, 247, 250, 0.5);**/
}
.vux-demo-tabbar .weui-bar__item_on .demo-icon-22 {
  color: #F70968;
}
.vux-demo-tabbar .weui-tabbar_item.weui-bar__item_on .vux-demo-tabbar-icon-home {
  color: rgb(53, 73, 94);
}
.demo-icon-22:before {
  content: attr(icon);
}
.vux-demo-tabbar-component {
  background-color: #F70968;
  color: #fff;
  border-radius: 7px;
  padding: 0 4px;
  line-height: 14px;
}
.weui-tabbar__icon + .weui-tabbar__label {
  margin-top: 0!important;
}
.vux-demo-header-box {
  z-index: 100;
  position: absolute;
  width: 100%;
  left: 0;
  top: 0;
}
</style>
