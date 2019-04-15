<template>
    <div>
        <x-header :left-options="{backText:'Back'}" :right-options="{showMore: true}"
        @on-click-more="popupOpen">{{title}}</x-header>
        <div v-transfer-dom>
              <popup v-model="show1">
                <group gutter="0">
                  <cell title="Logout" @click.native="logout"></cell>
                  <cell title="Feedback" @click.native="feedback"></cell>
                </group>
              </popup>
        </div>
    </div>
</template>

<script>
import { XHeader,Cell,Popup,Group } from 'vux'
export default {
  data () {
    return {
        showMenus: false,
        show1: false
    }
  },
  props: ['title'],
  methods: {
    back () {
      this.$store.commit('setTransition', 'turn-off')
      this.$router.back(-1)
    },
    logout() {
      this.$router.push({path: '/Signin'})
    },
    feedback() {
      this.$router.push({path: '/Signin'})
    },
    popupOpen() {
      this.show1 = true
    }
  },
  components: {
    XHeader,
    Cell,
    Popup,
    Group
  }
}
</script>

<style scoped>
.out_container{
  min-width: 100%;
  min-height: 100%;
}
.clearfix {
  overflow: auto;
}
.left {
  position: fixed;
  left: 0px;
  width: 60px;
}
.center {
  left: 50%;
  position: fixed;
}

</style>
