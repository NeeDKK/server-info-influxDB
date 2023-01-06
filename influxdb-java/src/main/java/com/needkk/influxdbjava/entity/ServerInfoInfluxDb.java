package com.needkk.influxdbjava.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wu Zijie
 * @Date: 2023-01-07 00:32
 * @Description:
 */
@Data
public class ServerInfoInfluxDb {
    private Long time;
    private Map<String,String> tags = new HashMap<>();
    private Map<String,Object> fields = new HashMap<>();
}
