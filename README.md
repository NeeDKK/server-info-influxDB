<h1 style="font-weight:normal">
  <a href="http://server-info.needkk.com">
    <img src=./server-info-web/src/assets/logo.png alt="needkk" width=35>
  </a>
  &nbsp;server-info-influxdb&nbsp;
  <a href="http://server-info.needkk.com"><img src="https://img.shields.io/static/v1?label=server-info-influxDb&color=green&message=start now"/></a>
  <a href="https://github.com/NeeDKK/server-info-influxDB"><img src="https://img.shields.io/static/v1?label=release&color=blue&message=v1.0"/></a>
  
</h1>

��̬չʾ������cpu,�ڴ��ռ�����
<br/>
demo: http://server-info.needkk.com
<br>
<p align="center">
  <img alt="server-info" src="https://cdn.jsdelivr.net/gh/NeeDKK/cloudimg@master/data/server-info-compress.gif">
</p>

## 1.Acknowledgements

- [influxdb](https://www.influxdata.com/)

## 2.ʹ��˵��
> - node�汾 > v13
> - golang�汾 >= v1.16
> - java�汾 > jdk1.8
> - influxdb�汾 1.x

2.1.influxdb��װ

<a href="https://github.com/NeeDKK/docker-install-base/tree/master/influxDB"><img src="https://img.shields.io/static/v1?label=influxdb-install&color=yellow&message=docker-install-base"/></a>

2.2.influxdb-java��װ
```bash
cd influxdb-java
mvn clean 
mvn install
```
2.3.influxdb-go��װ
```bash
cd influxdb-go
go env -w GO111MODULE=on
go env -w GOPROXY=https://goproxy.cn,direct
go env -w CGO_ENABLED=0
go mod tidy
go build -o server .
```
2.4.server-info-web��װ
```bash
cd server-info-web
npm install
npm run serve
```

## 3.����ѡ��
- golang:�� Gin ���ٴ����restful���API,ͨ��������Я��ÿ5s����������Ϣ����influxdb
- java:ͨ��springboot�restful���API,ͨ����ʱ������̳߳��첽����������Ϣ����influxdb
- web:ͨ��vue2+elementui+echarts�����򵥵�ͼ��ҳ��

## 4.����
golang��ȡ��������Ϣ������Ϣ   ===>
<a href="https://gitee.com/wu_zi_jie/server-info"><img src="https://img.shields.io/static/v1?label=gitee&color=red&message=server-info"/></a>

��������
```markdown
������� 
goos: ����ϵͳ 
numCpu: CPU�߼���������
compiler: GCC������
goVersion: golang�汾
numGoroutine: ��ǰgoroutine����
cpus: ��CPU����ʹ�����
cores: CPU�ں�
ram:�ڴ�
    usedMb: ʹ���ڴ���MB
    totalMb: ���ڴ��СMB
    usedPercent: �ڴ�ʹ��ռ��
disk
    usedMb: ��ǰ����ʹ��MB
    usedGb: ��ǰ����ʹ��GB
    totalMb: ��ǰ������MB
    totalGb: ��ǰ������GB
    usedPercent: ��ǰ����ʹ��ռ��
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