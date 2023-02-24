package com.needkk.influxdbjava.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.needkk.influxdbjava.config.InfluxDBTemplate;
import com.needkk.influxdbjava.entity.ServerInfoDTO;
import com.needkk.influxdbjava.entity.ServerInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wu Zijie
 * @Date: 2023-01-08 23:52
 * @Description:
 */
@Service
public class ServerInfoServiceImpl implements ServerInfoService {

    private final InfluxDBTemplate influxDBTemplate;

    public ServerInfoServiceImpl(InfluxDBTemplate influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }

    @Override
    public ServerInfoVO getLastOneHourInfo() {
        // 查询最近一小时的数据
        List<ServerInfoDTO> serverInfoDTOList = influxDBTemplate.queryByType("SELECT *::field from serverInfo_java where time>=now()-1h", ServerInfoDTO.class);
        ServerInfoVO serverInfoVO = new ServerInfoVO();
        List<String> times = new ArrayList<>();
        List<Double> cpu = new ArrayList<>();
        List<Double> mem = new ArrayList<>();
        serverInfoDTOList.forEach(dto -> {
            times.add(LocalDateTimeUtil.format(Instant.ofEpochMilli(dto.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime(), "yyyy-MM-dd HH:mm:ss"));
            cpu.add(Double.parseDouble(dto.getFields().get("cpu").toString()));
            mem.add(Double.parseDouble(dto.getFields().get("ram").toString()));
        });
        serverInfoVO.setTimes(times);
        serverInfoVO.setCpu(cpu);
        serverInfoVO.setMem(mem);
        return serverInfoVO;
    }
}
