package config

import (
	"fmt"
	"github.com/NeeDKK/influxdb-go/entity"
	"github.com/spf13/viper"
)

var GlobalConfig *entity.Config

func InitViper() {
	// 设置配置文件的名字
	viper.SetConfigFile("config.yaml")
	// 设置配置文件类型
	viper.SetConfigType("yaml")
	// 读取配置文件
	err := viper.ReadInConfig()
	if err != nil {
		fmt.Println("读取配置文件失败:", err.Error())
		panic(err)
	}
	err = viper.Unmarshal(&GlobalConfig)
	if err != nil {
		fmt.Println("读取配置文件失败:", err.Error())
		panic(err)
	}
}
