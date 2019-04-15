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
                payload: {'username':this.username, 'password':this.password}
            }
        },
        components: {
                Group,
                XButton,
                XInput
        },
        methods: {
              login() {
                console.log(this.apiUrl + this.payload)
                this.$http.post(this.apiUrl, this.payload)
                                   .then(function(res){
                                      var res1 = res.data
                                      if (res1 == 'login success')
                                      {
                                        this.$router.push({path: '/Map'})
                                      }
                                      else
                                      {
                                        alert(res1)
                                      }
                                   },function(res){
                                      alert('Signin failed!')
                                   }
                                   )
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
