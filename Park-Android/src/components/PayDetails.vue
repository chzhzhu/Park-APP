<template>
    <div class="A">
        <Header> title="a"></Header>
        <div class="bottom">
            bottom
        </div>
    </div>
</template>

<script>
import Header from './Header'
export default {
  data () {
    return {
    }
  },
  components: {
    Header
  }
}
</script>

<style scoped>
.A {
  width: 100%;
  height: 100%;
  background-color: white;
  position: fixed;
}
.bottom {
  width: 100%;
  height: 50px;
  background-color: white;
  position: fixed;
  bottom: 0px;
}
</style>
