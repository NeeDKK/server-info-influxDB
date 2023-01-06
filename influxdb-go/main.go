package main

import (
	"fmt"
	"github.com/NeeDKK/influxdb-go/config"
	"github.com/NeeDKK/influxdb-go/controller"
	"github.com/NeeDKK/influxdb-go/goroutine"
	"github.com/gin-gonic/gin"
	"strconv"
)

func init() {
	// 初始化配置文件
	config.InitViper()
	// 初始化influxdb
	err := config.InitInfluxDB()
	if err != nil {
		fmt.Println("influxdb init failed, err:", err)
	}
}

func main() {
	//创建路由
	gin.SetMode(gin.ReleaseMode)
	engine := gin.Default()
	//配置跨域
	engine.Use(config.Cors())
	//设置信任请求地址
	engine.SetTrustedProxies([]string{"127.0.0.1"})
	engine.POST("/getInfo", controller.GetInfo)
	go goroutine.ServerInfoRoutine()
	//启动gin服务
	engine.Run(":" + strconv.Itoa(config.GlobalConfig.Server.Port))
}
