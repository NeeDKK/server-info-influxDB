package com.needkk.influxdbjava.entity;

import lombok.Data;

import java.util.Map;

/**
 * @Author: Wu Zijie
 * @Date: 2023-01-09 00:02
 * @Description:
 */
@Data
public class ServerInfoDTO {
    public Long time;
    public Map<String, String> tags;
    public Map<String, Object> fields;
}
