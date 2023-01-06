package config

import (
	"fmt"
	client "github.com/influxdata/influxdb1-client/v2"
	"time"
)

var (
	InfluxdbCli client.Client
)

func InitInfluxDB() (err error) {
	InfluxdbCli, err = client.NewHTTPClient(client.HTTPConfig{
		Addr:     GlobalConfig.Influxdb.Url,
		Username: GlobalConfig.Influxdb.Username,
		Password: GlobalConfig.Influxdb.Password,
	})
	return
}

func WritePoints(tags map[string]string, fields map[string]interface{}) (err error) {
	//创建一个批量写入的对象
	points, err := client.NewBatchPoints(client.BatchPointsConfig{
		Database:  SERVERINFODATABASE,
		Precision: "s",
	})
	if err != nil {
		fmt.Println("new batch points failed, err:", err)
		return err
	}
	//创建一个数据点
	pt, err := client.NewPoint(SERVERINFOMEASUREMENT, tags, fields, time.Now())
	if err != nil {
		fmt.Println("new point failed, err:", err)
		return err
	}
	points.AddPoint(pt)
	err = InfluxdbCli.Write(points)
	if err != nil {
		fmt.Println("write failed, err:", err)
		return err
	}
	return nil
}
