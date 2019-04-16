<template>
  <div>
    <Header></Header>
    <div class="scan">
      <div id="bcid">
        <div style="height:40%"></div>
        <p class="tip">...loading...</p>
      </div>
      <!--<footer>
        <button @click="startRecognize">1.创建控件</button>
        <button @click="startScan">2.开始扫描</button>
        <button @click="cancelScan">3.结束扫描</button>
        <button @click="closeScan">4.关闭控件</button>
      </footer>-->
    </div>
  </div>
</template>

<script type='text/ecmascript-6'>
import Header from '../common/Header'
let scan = null

export default {
  name: 'Scanner',
  components: {Header},
  data () {
    return {
      codeUrl: ''
    }
  },
  methods: {
    // 创建扫描控件
    startRecognize () {
      let that = this
      if (!window.plus) return
      scan = new window.plus.barcode.Barcode('bcid')
      scan.onmarked = onmarked

      function onmarked (type, result, file) {
        switch (type) {
          case window.plus.barcode.QR:
            type = 'QR'
            break
          case window.plus.barcode.EAN13:
            type = 'EAN13'
            break
          case window.plus.barcode.EAN8:
            type = 'EAN8'
            break
          default:
            type = '其它' + type
            break
        }
        result = result.replace(/\n/g, '')
        that.codeUrl = result
        alert(result)
      }
      that.startScan()
      that.goback()
    },
    // 开始扫描
    startScan () {
      if (!window.plus) return
      scan.start()
    },
    // 关闭扫描
    cancelScan () {
      if (!window.plus) return
      scan.cancel()
    },
    // 关闭条码识别控件
    closeScan () {
      if (!window.plus) return
      scan.close()
    },
    // 返回
    goback () { // 返回
      this.closeScan()
      if (this.codeUrl !== null) {
        // user.locked
      }
      this.$router.push({ path: '/rent/', params: this.codeUrl })
    }
  },
  created () {
    this.startRecognize()
    this.startScan()
  },
  mounted () {
    this.startRecognize()
    this.startScan()
  }
}
</script>
<style lang="less">
  .scan {
    height: 100%;

    #bcid {
      width: 100%;
      position: absolute;
      left: 0;
      right: 0;
      top: 0;
      bottom: 3rem;
      text-align: center;
      color: #fff;
      background: #ccc;
    }

    footer {
      position: absolute;
      left: 0;
      bottom: 1rem;
      height: 2rem;
      line-height: 2rem;
      z-index: 2;
    }
  }
</style>
