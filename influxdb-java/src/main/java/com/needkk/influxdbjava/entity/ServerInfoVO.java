package com.needkk.influxdbjava.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: Wu Zijie
 * @Date: 2023-01-08 23:49
 * @Description:
 */
@Data
public class ServerInfoVO {
    private List<String> times;
    private List<Double> cpu;
    private List<Double> mem;
}
