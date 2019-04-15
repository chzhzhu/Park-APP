<template>
 <div style="text-align: center;">
    <img src="../../assets/icon/car.png" class="img">
    <br>
    <br>
    <b class="title">Welcome to Parking APP</b>
      <br>
      <br>
      <br>
      <x-button @click.native="signup" style="border-radius:5px; width: 300px;">Create Account</x-button>
      <br>
      <x-button @click.native="signin" style="border-radius:5px; width: 300px;">Sign in</x-button>
 </div>
</template>
<script>
import { XButton } from 'vux'
export default {
    data() {
        return {
        }
    },
    components: {
        XButton
    },
    methods: {
      signup() {
        this.$router.push({path: '/Signup'})
      },
      signin() {
        this.$router.push({path: '/Signin'})
      }

    }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.mycontainer {
    position: fixed;
    width: 100%;
    height: 100%;
}
.img {
    margin-top:30%;
    width: 90px;
    height: 96px;
}
.title {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  font-size: 28px;
}

</style>

