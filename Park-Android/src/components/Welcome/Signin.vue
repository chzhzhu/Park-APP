<template>
 <div>
     <h1 style="margin-top:10%">Parking System</h1>
    <div class="name">
    <group>
       <x-input title="Username" name="mobile" placeholder="Please input phone number" keyboard="number"
       mask="999 9999 9999" v-model="username" :max="13" is-type="china-mobile"></x-input>
    </group>
    </div>
    <br>
    <div class="pwd">
    <group>
       <x-input title="Password" type="password" placeholder="Please enter your password" v-model="password" :min="6" :max="12" @on-change="change"></x-input>
    </group>
    </div>
    <div class="button">
      <x-button @click.native="login" style="order-radius:5px; width: 300px;">Login</x-button>
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
                        this.$router.push({path: '/Map'})
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
              },
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

.name{
margin-top:10%;
}

.pwd{
margin-top:10px;
}

.button{
margin-top:10%;
}
</style>
