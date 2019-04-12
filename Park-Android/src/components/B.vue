<template>
    <div class="B">
        <Header title="b"></Header>
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
.B {
  width: 100%;
  height: 100%;
  background-color: white;
  position: fixed;
}
</style>
