<template>
  <div>
    <Header></Header>
    <span>here is map</span>
    <Bottom></Bottom>
  </div>
</template>
<script>
import Header from '../Common/Header'
import Bottom from '../Common/Bottom'
export default {
  data1 () {
    return {
      center: {lng: 0, lat: 0},
      zoom: 3
    }
  },
  methods1: {
    handler ({BMap, map}) {
      console.log(BMap, map)
      this.center.lng = 116.404
      this.center.lat = 39.915
      this.zoom = 12
    }
  },
  name: 'Navigate',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      myinfo: {
        name: 'ur',
        age: 2,
        email: '333@qq.com'
      }
    }
  },
  components: {
    Header,
    Bottom
  },
  methods: {
    change () {
      this.$store.commit('getUserInfo', this.myinfo)
    },
    ch () {
      this.$store.commit('changecount')
    },
    rec () {
      console.log('asdsad')
    }
  },
  computed: {
    count () {
      return this.$store.state.count
    }
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
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
/* The container of BaiduMap must be set width & height. */
.map {
  width: 100%;
  height: 300px;
}
</style>
