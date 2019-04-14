<template>
    <div>
        <x-header :left-options="{backText:'Back'}">{{title}}</x-header>
    </div>
</template>

<script>
import { XHeader } from 'vux'
export default {
  data () {
    return {}
  },
  props: ['title'],
  methods: {
    back () {
      this.$store.commit('setTransition', 'turn-off')
      this.$router.back(-1)
    }
  },
  components: {
    XHeader
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
