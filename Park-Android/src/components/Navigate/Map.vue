<template>
    <div class="mycontainer">
        <baidu-map class="map" :center="center" :zoom="zoom" @ready="handler">
            <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
            <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
            <bm-marker :position=points[0] :dragging="true" @click="infoWindowOpen(0)"></bm-marker>
            <bm-marker :position=points[1] :dragging="true" @click="infoWindowOpen(1)"></bm-marker>
            <bm-marker :position=points[2] :dragging="true" @click="infoWindowOpen(2)"></bm-marker>
            <bm-marker :position=points[3] :dragging="true" @click="infoWindowOpen(3)"></bm-marker>
            <bm-marker :position=points[4] :dragging="true" @click="infoWindowOpen(4)"></bm-marker>
            <bm-marker :position=points[5] :dragging="true" @click="infoWindowOpen(5)"></bm-marker>
            <bm-marker :position=points[6] :dragging="true" @click="infoWindowOpen(6)"></bm-marker>
            <bm-marker :position=points[7] :dragging="true" @click="infoWindowOpen(7)"></bm-marker>
            <bm-marker :position=points[8] :dragging="true" @click="infoWindowOpen(8)"></bm-marker>
            <bm-marker :position=points[9] :dragging="true" @click="infoWindowOpen(9)"></bm-marker>

            <div v-transfer-dom>
              <popup v-model="show" height="280px" is-transparent>
                <div style="width: 95%;background-color:#fff;height:250px;margin:0 auto;border-radius:5px;padding-top:10px;">
                  <div style="text-align: center;"><span style="font-size: 20px;">{{parkinfo}}</span></div>
                 <group>
                  <cell title="Total parking spaces:">{{AllEqus}}</cell>
                  <cell title="Avaliable parking spaves:">{{LeftEqus}}</cell>
                  <cell title="Pay:" value="2 CNY/H"></cell>
                 </group>
                 <div style="padding:20px 15px;">
                  <x-button style="background-color: #FF7F00;color: #FFFFFF;" @click.native="navigation()">Let's go</x-button>
                 </div>
                </div>
              </popup>
            </div>
        </baidu-map>
        <Bottom></Bottom>
    </div>
</template>
<script>
import Header from '../Common/Header'
import Bottom from '../Common/Bottom'
import { XDialog, Group, XButton, Popup, Cell, TransferDomDirective as TransferDom } from 'vux'
export default {
    directives: {
        TransferDom
      },
    data() {
        return {
            center: { lng: 0, lat: 0 },
            zoom: 3,
            show: false,
            parkinfo: '',
            points: [],
            n:0,
            AllEqus:'',
            LeftEqus:'',
            username: '',
            apiUrl: 'http://localhost:8083/parkingApp/rest/parkport/parkport',
            myinfo: {
                name: 'ur',
                age: 2,
                email: '333@qq.com'
            }
        }
    },
    components: {
        Header,
        Bottom,
        XDialog,
        Group,
        XButton,
        Popup,
        Cell
    },
    methods: {
        change() {
            this.$store.commit('getUserInfo', this.myinfo)
        },
        ch() {
            this.$store.commit('changecount')
        },
        infoWindowClose() {
            this.show = false
        },
        infoWindowOpen(n) {
            this.show = true
            this.parkinfo = 'Parking Lot ' + n
            this.n = n
            this.axios.get(this.apiUrl, {
                       params: {
                         parkPortId: n
                       }
                     })
                     .then(response=>{
                       console.log(response);
                       this.AllEqus = response.data.equNum
                       this.LeftEqus = response.data.unlockedEquNum
                     })
                     .catch(function (error) {
                       console.log(error);
                     });

        },
        handler({ BMap, map }) {
            console.log(BMap, map)
            this.center.lng = 118.829
            this.center.lat = 31.942
            this.zoom = 12
        },
        navigation() {
            this.show = false
            this.$router.push({name: 'Navigation', params: {point: this.points[this.n], center: this.center}})
        }
    },
    mounted () {
       var position1 = { lng: 118.796, lat: 32.024 }
       var position2 = { lng: 118.864, lat: 32.033 }
       var position3 = { lng: 118.899, lat: 31.900 }
       var position4 = { lng: 119.057, lat: 32.027 }
       var position5 = { lng: 118.588, lat: 32.017 }
       var position6 = { lng: 118.694, lat: 31.799 }
       var position7 = { lng: 119.127, lat: 31.795 }
       var position8 = { lng: 118.878, lat: 31.736 }
       var position9 = { lng: 119.116, lat: 31.903 }
       var position10 = { lng: 118.416, lat: 31.918 }
       this.points.push(position1)
       this.points.push(position2)
       this.points.push(position3)
       this.points.push(position4)
       this.points.push(position5)
       this.points.push(position6)
       this.points.push(position7)
       this.points.push(position8)
       this.points.push(position9)
       this.points.push(position10)
       this.username = this.$route.params.username
       this.$store.commit('setLoginAccount', this.username)
    }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
    font-weight: normal;
}

ul {
    list-style-type: none;
    padding: 0;
}

li {
    display: inline-block;
    margin: 0 10px;
}

a {
    color: #42b983;
}

.mycontainer {
    position: fixed;
    width: 100%;
    height: 100%;
}

.vux-close {
    margin-top: 8px;
    margin-bottom: 8px;
    font-family: PingFangSC-Semibold, sans-serif;
}

.img-box {
    height: 350px;
    overflow: hidden;
    background-image: linear-gradient(120deg, #fdfbfb 0%, #ebedee 100%);
}

/* The container of BaiduMap must be set width & height. */
.map {
    position: fixed;
    bottom: 50px;
    top: 0px;
    width: 100%;
}
</style>
