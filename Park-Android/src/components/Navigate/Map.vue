<template>
    <div class="mycontainer">
        <baidu-map class="map" :center="center" :zoom="zoom" @ready="handler">
            <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
            <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
            <bm-marker :position="markerPoint" :dragging="true" @click="infoWindowOpen(0)"></bm-marker>
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
            <x-dialog v-model="show" class="dialog-demo">
               <div class="img-box">{{parkinfo}}</div>
               <div @click="navigation()">
                <span class="vux-close">Let's go!</span>
               </div>
               </x-dialog>
        </baidu-map>
        <Bottom></Bottom>
    </div>
</template>
<script>
import Header from '../Common/Header'
import Bottom from '../Common/Bottom'
import { XDialog } from 'vux'
export default {
    data() {
        return {
            center: { lng: 0, lat: 0 },
            markerPoint: { lng: 120.63, lat: 31.32 },
            zoom: 3,
            show: false,
            parkinfo: '',
            points: [],
            n:0,
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
        XDialog
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
            this.parkinfo = n + '停车场'
            this.n = n
        },
        handler({ BMap, map }) {
            console.log(BMap, map)
            this.center.lng = 120.62
            this.center.lat = 31.32
            this.zoom = 12
        },
        navigation() {
            this.show = false
            this.$router.push({name: 'Navigation', params: {point: this.points[this.n], center: this.center}})
        }
    },
    mounted () {
        for (var i = 0; i < 10; i++) {
           const position = { lng: Math.random() * 0.1 + 120.6, lat: Math.random() * 0.1 + 31.3 }
           this.points.push(position)
        }
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
    width: 100%;
    height: 91.5%;
}
</style>
