package com.needkk.influxdbjava.service;

import com.needkk.influxdbjava.entity.ServerInfoVO;

/**
 * @Author: Wu Zijie
 * @Date: 2023-01-08 23:48
 * @Description:
 */
public interface ServerInfoService {

    /**
     * 获取最近一小时服务器状态
     * @return
     */
    ServerInfoVO getLastOneHourInfo();
}
