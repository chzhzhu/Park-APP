<template>
  <div class="erwm">
    <Header></Header>
    <div class="scan">
      <div id="bcid">
        <div style="height:40%; color: #000;"></div>
        <p class="tip">...载入中...</p>
      </div>
      <footer>
        <p @click.native="startRecognize"></p>
        <p @click.native="startScan"></p>
        <p @click.native="cancelScan"></p>
        <p @click.native="closeScan"></p>
      </footer>
    </div>
  </div>
</template>

<script type='text/ecmascript-6'>
import Header from '../Common/Header'
var scan = null
var styles = {frameColor: '#2e5dea', scanbarColor: '#2e5dea'} // 边框属性，中间线属性，背景色
var filter// 扫码格式 空为全类型

export default {
  name: 'Scanner',
  components: {
    Header
  },
  data () {
    return {
      codeUrl: '',
      isShow: true,
      userId: null
    }
  },
  created () {
    // 进入页面就调取扫一扫
    this.startRecognize()
    this.startScan()
    alert('创建')
  },
  mounted () {
    this.startRecognize()
    this.startScan()
    this.isShow = true
  },
  updated () {
    this.startRecognize()
    this.startScan()
    this.isShow = true
    alert('更新')
  },
  destroyed () {
    console.log('aaa')
  },
  methods: {
  // 创建扫描控件
    startRecognize () { // alert(123);
      let that = this
      if (!window.plus) return
      scan = new window.plus.barcode.Barcode('bcid', filter, styles)
      console.log(scan)
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
    goback () { // 返回
      this.closeScan()
      this.$router.push({ path: '/rent/', params: this.userId })// this.isShow = false;
    }
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
      top: 40px;
      bottom:0;
      text-align: center;
      color: green;
      background: #fff;
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
