package goroutine

import (
	"fmt"
	"github.com/NeeDKK/influxdb-go/config"
	"github.com/NeeDKK/influxdb-go/utils"
	"time"
)

func ServerInfoRoutine() {
	for {
		//每过5秒将服务器状态信息写入influxdb
		var s utils.Server
		//获取服务器信息
		s.Cpu, _ = utils.InitCPU()
		s.Rrm, _ = utils.InitRAM()
		//jsonByte, _ := json.Marshal(s)
		//jsonStr := string(jsonByte)
		//fmt.Printf("%v", jsonStr)
		tags := map[string]string{
			"host": "server",
		}
		//获取cpu的使用率

		fields := map[string]interface{}{
			"cpu": s.Cpu.UsedPercent,
			"ram": s.Rrm.UsedPercent,
		}
		err := config.WritePoints(tags, fields)
		if err != nil {
			fmt.Println("write points failed, err:", err)
		}
		time.Sleep(5 * time.Second)
	}

}
