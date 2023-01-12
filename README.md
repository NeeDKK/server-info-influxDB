<h1 style="font-weight:normal">
  <a href="http://server-info.needkk.com">
    <img src=./server-info-web/src/assets/logo.png alt="needkk" width=35>
  </a>
  &nbsp;server-info-influxdb&nbsp;
  <a href="http://server-info.needkk.com"><img src="https://img.shields.io/static/v1?label=server-info-influxDb&color=green&message=start now"/></a>
  <a href="https://github.com/NeeDKK/server-info-influxDB"><img src="https://img.shields.io/static/v1?label=release&color=blue&message=v1.0"/></a>
  
</h1>

动态展示服务器cpu,内存等占用情况
<br/>
demo: http://server-info.needkk.com
<br>
<p align="center">
  <img alt="server-info" src="https://cdn.jsdelivr.net/gh/NeeDKK/cloudimg@master/data/server-info-compress.gif">
</p>

## 1.Acknowledgements

- [influxdb](https://www.influxdata.com/)

## 2.使用说明
> - node版本 > v13
> - golang版本 >= v1.16
> - java版本 > jdk1.8
> - influxdb版本 1.x

2.1.influxdb安装

<a href="https://github.com/NeeDKK/docker-install-base/tree/master/influxDB"><img src="https://img.shields.io/static/v1?label=influxdb-install&color=yellow&message=docker-install-base"/></a>

2.2.influxdb-java安装
```bash
cd influxdb-java
mvn clean 
mvn install
```
2.3.influxdb-go安装
```bash
cd influxdb-go
go env -w GO111MODULE=on
go env -w GOPROXY=https://goproxy.cn,direct
go env -w CGO_ENABLED=0
go mod tidy
go build -o server .
```
2.4.server-info-web安装
```bash
cd server-info-web
npm install
npm run serve
```

## 3.技术选型
- golang:用 Gin 快速搭建基础restful风格API,通过单独的携程每5s将服务器信息存入influxdb
- java:通过springboot搭建restful风格API,通过定时任务和线程池异步将服务器信息存入influxdb
- web:通过vue2+elementui+echarts构建简单的图表页面

## 4.完善
golang读取服务器信息详情信息   ===>
<a href="https://gitee.com/wu_zi_jie/server-info"><img src="https://img.shields.io/static/v1?label=gitee&color=red&message=server-info"/></a>

完整参数
```markdown
参数解读 
goos: 操作系统 
numCpu: CPU逻辑处理器数
compiler: GCC编译器
goVersion: golang版本
numGoroutine: 当前goroutine数量
cpus: 各CPU核心使用情况
cores: CPU内核
ram:内存
    usedMb: 使用内存数MB
    totalMb: 总内存大小MB
    usedPercent: 内存使用占比
disk
    usedMb: 当前磁盘使用MB
    usedGb: 当前磁盘使用GB
    totalMb: 当前磁盘总MB
    totalGb: 当前磁盘总GB
    usedPercent: 当前磁盘使用占比
```
```json
{
  "os": {
    "goos": "windows",
    "numCpu": 16,
    "compiler": "gc",
    "goVersion": "go1.16.2",
    "numGoroutine": 1
  },
  "cpu": {
    "cpus": [
      0,
      0,
      7.142857142857142,
      0,
      21.428571428571427,
      0,
      21.428571428571427,
      0,
      0,
      0,
      0,
      7.142857142857142,
      0,
      0,
      7.142857142857142,
      7.142857142857142
    ],
    "cores": 8
  },
  "ram": {
    "usedMb": 10514,
    "totalMb": 32678,
    "usedPercent": 32
  },
  "disk": {
    "usedMb": 188519,
    "usedGb": 184,
    "totalMb": 339465,
    "totalGb": 331,
    "usedPercent": 55
  }
}
```