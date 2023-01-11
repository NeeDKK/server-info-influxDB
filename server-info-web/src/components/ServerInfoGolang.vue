<template>
  <div>
    <div>
      <span><VueEcharts :option="optionCpu" style="width:1000px;height:400px"></VueEcharts></span>
      <span><VueEcharts :option="optionMemory" style="width:1000px;height:400px"></VueEcharts></span>
    </div>
  </div>
</template>

<script>
import api from '../utils/api'
import "echarts"

export default {
  name: 'ServerInfoGolang',
  data() {
    return {
      optionCpu: {},
      optionMemory: {},
      timer: ''
    }
  },
  methods: {
    refreshData() {
      this.timer = window.setInterval(() => {
        this.getInfo()
      }, 5000)
    },
    getInfo() {
      this.$axios.post(api.GET_INFO_GO.url).then(res => {
        if (res.data.code === 0) {
          //取20个点
          let count = res.data.data.times.length
          let times = []
          let cpu = []
          let memory = []
          for (let i = 0; i < count; i++) {
            if (i % 20 === 0) {
              times.push(res.data.data.times[i])
              cpu.push(res.data.data.cpu[i])
              memory.push(res.data.data.mem[i])
            }
          }
          this.optionCpu = {
            tooltip: {
              trigger: 'axis'
            },
            title: {
              text: 'cpu使用率'
            },
            xAxis: {
              type: 'category',
              data: times
            },
            yAxis: {
              type: 'value',
              axisLabel: {
                formatter: '{value} %'
              }
            },
            series: [
              {
                data: cpu,
                type: 'line',
                smooth: true
              }
            ]
          }
          this.optionMemory = {
            tooltip: {
              trigger: 'axis'
            },
            title: {
              text: '内存使用率'
            },
            xAxis: {
              type: 'category',
              data: times
            },
            yAxis: {
              type: 'value',
              axisLabel: {
                formatter: '{value} %'
              }
            },
            series: [
              {
                data: memory,
                type: 'line',
                smooth: true
              }
            ]
          }
        }
      })
    },
  },
  created() {
    this.getInfo()
    this.refreshData()
  },
  beforeDestroy() {
    clearInterval(this.timer)
  },
}
</script>

<style scoped>
</style>
