package controller

import (
	"encoding/json"
	"fmt"
	"github.com/NeeDKK/influxdb-go/config"
	"github.com/NeeDKK/influxdb-go/entity"
	"github.com/gin-gonic/gin"
	client "github.com/influxdata/influxdb1-client/v2"
)

func GetInfo(c *gin.Context) {
	//查询最近一小时的数据
	cmd := "SELECT *::field from serverInfo_go where time>=now()-1h"
	query := client.Query{
		Command:  cmd,
		Database: config.SERVERINFODATABASE,
	}
	response, err := config.InfluxdbCli.Query(query)
	if err != nil {
		fmt.Println("query failed, err:", err)
		entity.FailWithDetailed(err.Error(), "查询失败", c)
		return
	}
	values := response.Results[0].Series[0].Values
	var Res entity.ServerInfoVO
	var times []string
	var cpu []json.Number
	var mem []json.Number
	for _, v := range values {
		times = append(times, v[0].(string))
		cpu = append(cpu, v[1].(json.Number))
		mem = append(mem, v[2].(json.Number))
	}
	Res.Times = times
	Res.Cpu = cpu
	Res.Mem = mem
	entity.OkWithData(Res, c)
}
