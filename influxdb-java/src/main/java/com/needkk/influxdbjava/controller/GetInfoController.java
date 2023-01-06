package com.needkk.influxdbjava.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Wu Zijie
 * @Date: 2023-01-06 17:29
 * @Description:
 */
@RestController
public class GetInfoController {
    @Value("${spring.influx.url}")
    private String influxDBUrl;
    @Value("${spring.influx.username}")
    private String userName;

    @Value("${spring.influx.password}")
    private String password;

    @Value("${spring.influx.database}")
    private String database;


    @GetMapping("/getInfo")
    public String getInfo() {
        return influxDBUrl + " " + userName + " " + password + " " + database;
    }
}
