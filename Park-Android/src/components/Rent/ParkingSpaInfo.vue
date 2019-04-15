<template>
    <div class="mycontainer">
        <Header></Header>
        <div style="margin-top:40px">
            <group title="Parking Info" class="group">
              <cell class="cell" title="Number:" :value="equNumber" value-align='right'></cell>
              <cell class="cell" title="Status:" :value="equStatus"></cell>
              <cell class="cell" title="Rent:" :value="rent"></cell>
            </group>
            <br>
            <br>
            <x-button @click.native="info">Go to Rent!</x-button>
            <br>
            <x-button @click.native="scan">Back to Rescan</x-button>
         </div>
        <Bottom></Bottom>
    </div>
</template>
<script>
import Header from '../Common/Header'
import Bottom from '../Common/Bottom'
import { Group, Cell, XButton } from 'vux'
export default {
    data() {
        return {
            equNumber: 10001,
            equStatus: 'Locked',
            rent: '2¥/h'
        }
    },
    components: {
        Header,
        Bottom,
        Group,
        Cell,
        XButton
    },
    methods: {
      info() {
        this.$router.push({name: 'Rent', params: {equ: this.equNumber}})
      },
      scan() {
        this.$router.push({name: 'Scanner', params: {equ: this.equNumber}})
      }

    },
    mounted() {
       this.equNumber = 1002
       this.equStatus = 'Locked'
       this.rent = '2¥/h'
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

.vux-close {
    margin-top: 8px;
    margin-bottom: 8px;
    font-family: PingFangSC-Semibold, sans-serif;
}

.cell {
    text-align: left;
}

.group {
  font-size:20px;
}
</style>
