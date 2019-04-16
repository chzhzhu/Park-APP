<template>
 <div style="text-align: center;">
    <img src="../../assets/icon/welcome_parking.png" class="image">
    <br>
    <br>
    <b class="title">Parking APP</b>
      <x-button @click.native="signup" style="border-radius:5px; width: 85%;margin-top: 90px;font-size: 23px;">Create Account</x-button>
      <br>
      <x-button @click.native="signin" style="border-radius:5px; width: 85%;font-size: 23px;">Sign In</x-button>
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
.image {
    margin-top:90px;
    width: 110px;
    height: 96px;
}
.title {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  font-size: 40px;
}

</style>

