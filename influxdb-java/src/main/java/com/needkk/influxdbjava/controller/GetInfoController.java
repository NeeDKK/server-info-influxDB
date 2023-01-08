package com.needkk.influxdbjava.controller;

import com.needkk.influxdbjava.common.Result;
import com.needkk.influxdbjava.service.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Wu Zijie
 * @Date: 2023-01-06 17:29
 * @Description:
 */
@RestController
public class GetInfoController {


    @Autowired
    private ServerInfoService serverInfoService;

    @PostMapping("/getInfo")
    public Result getInfo() {
        return Result.success(serverInfoService.getLastOneHourInfo());
    }
}
