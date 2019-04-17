<template>
 <div>
    <div style="text-align: center;margin-top: 95px;font-size: 25px;color: #13227a;"><h1>Login</h1></div>
    <div style="text-align: center;"><img class="image" src="../../assets/icon/login.png"></div>
    <div class="name">
      <group>
         <x-input title="Username" name="mobile" placeholder="Phone number" keyboard="number"
         mask="999 9999 9999" v-model="username" :max="13" is-type="china-mobile"></x-input>
         <x-input title="Password" type="password" placeholder="Password" v-model="password" :min="6" :max="18" @on-change="change"></x-input>
      </group>
    </div>
    <div class="button">
      <x-button plain @click.native="login" style="order-radius:5px; width: 85%;margin-top: 50px;">Login</x-button>
    </div>
 </div>
</template>
<script>
import { XInput,Group,XButton } from 'vux'
    export default {
        data () {
            return {
                username: '',
                password: '',
                apiUrl: 'http://localhost:8083/parkingApp/rest/hello/login',
                payload: {'username':'', 'password':''}
            }
        },
        components: {
                Group,
                XButton,
                XInput
        },
        methods: {
              login() {
                console.log(this.username + this.password)
                this.payload.username = this.username.replace(/\s+/g,"");
                this.payload.password = this.password
                var payloadStr = JSON.stringify(this.payload)
                console.log(this.apiUrl + payloadStr)
                this.axios.post(this.apiUrl, payloadStr,{headers: {'Content-Type': 'application/json;charset=UTF-8'}})
                  .then(res=>{
                    console.log(res)
                    var resDate = res.data
                    console.log(resDate)
                    if (resDate=='login success'){
                        this.$router.push({name: 'Map', params: {username:this.payload.username}})
                      }
                      else{
                        alert('Login Failed')
                      }
                  })
                  .catch(function (error) {
                    console.log(error)
                  })
              },
              change (val) {
                    console.log('on change', val)
              }
        }
    }
</script>
<style scoped>
body,p,ul,li{
    margin: 0px;
    padding:0px;
}

*{
    text-decoration: none;
    list-style: none;
}
.image {
  margin-top:30px;
  height: 80px;
  width: 80px;
}
.name{
margin-top: 10px;
}
</style>
