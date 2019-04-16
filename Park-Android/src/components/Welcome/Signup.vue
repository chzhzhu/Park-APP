<template>
 <div>
    <h1 style="margin-top:10%">Sign UP</h1>
    <div class="mycontainer">
        <group>
           <x-input title="Username" name="mobile" placeholder="Please enter phone number " keyboard="number"
           mask="999 9999 9999" v-model="username" :max="13" is-type="china-mobile"></x-input>
        </group>
        <br>
        <group>
           <x-input title="Password" type="password" placeholder="Please input your password" v-model="password" :min="6" :max="12" @on-change="change"></x-input>
        </group>
        <br>
        <group>
          <x-input title="Confirm" v-model="password2" type="password" placeholder="Please confirm your password" :equal-with="password"></x-input>
        </group>
    </div>
    <div class="botton">
      <br>
      <x-button @click.native="signa" style="border-radius:5px; width: 300px;">SignUp</x-button>
    </div>
 </div>
</template>
<script>
import { XInput,Group,XButton } from 'vux'
export default {
  data() {
      return {
        password: '',
        password2: '',
        username: '',
        apiUrl: 'http://localhost:8083/parkingApp/rest/hello/signup',
        payload: {'username':'', 'password':''}
      }
  },
  components: {
     Group,
     XButton,
     XInput
  },
  methods: {
    signa() {
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
                    if (resDate=='Sign Up Successfully'){
                        this.$router.push({path: '/Signin'})
                    }
                    else{
                        alert('Signup Failed')
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

.mycontainer {
    position: fixed;
    width: 100%;
    height: 100%;
    margin-top: 10%
}

.botton {
    margin-top: 80%
}
</style>
